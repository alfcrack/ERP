package Controlador;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Clases.Candidatos;
import Clases.Otras_Titulaciones;
import Clases.Titulos_oficiales;
import Repositorios.CandidatosRepository;
import Repositorios.Otras_TitulacionesRepository;

@Controller
@RequestMapping(path="/otra")
public class Otras_TitulacionesControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);

	@Autowired
	private Otras_TitulacionesRepository otras_TitulacionesRepository;
	
	@Autowired 
	private CandidatosRepository candidatosRepository;
	
	@CrossOrigin
	@PostMapping(path="/add/{canId}")
	public @ResponseBody Otras_Titulaciones add(@RequestBody Otras_Titulaciones otras_Titulaciones,
			@PathVariable(name="canId")Integer canId) {
		try {
			Candidatos can = candidatosRepository.findById(canId).get();
			Otras_Titulaciones tit=new Otras_Titulaciones();
			tit.setCandidatos(can);
			tit.setCentro(otras_Titulaciones.getCentro());
			tit.setDocumento(otras_Titulaciones.getDocumento());
			tit.setFecha_obtencion(otras_Titulaciones.getFecha_obtencion());
			tit.setNombre(otras_Titulaciones.getNombre());
			otras_TitulacionesRepository.save(tit);
			logger.info("Otras Titulaciones Guardadas");
			return tit;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@CrossOrigin
	@GetMapping(path="/read")
	public @ResponseBody Iterable<Otras_Titulaciones>add(){
		return otras_TitulacionesRepository.findAll();
	}
	@CrossOrigin
	@GetMapping(path="/read/{Id}")
	public @ResponseBody Otras_Titulaciones readId(@PathVariable(name="Id")Integer id) {
		try {
		return otras_TitulacionesRepository.findById(id).get();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@CrossOrigin
	@DeleteMapping(path="/supr/{Id}")
	public @ResponseBody String suprId(@PathVariable(name="Id")Integer id) {
		try {
			 otras_TitulacionesRepository.deleteById(id);
			 logger.info("Otras Titulaciones borrada");
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return null;
	}
	@CrossOrigin
	@PostMapping(path="/addImg" , produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody Object addImg(@RequestParam (name="img")MultipartFile file,
			@RequestParam(name="id") Integer id) {
		try {
			Otras_Titulaciones titOfi=otras_TitulacionesRepository.findById(id).get();
			titOfi.setDocumento(file.getBytes());
			otras_TitulacionesRepository.save(titOfi);
			return titOfi.getDocumento();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping(path="/readImg/{Id}")
	public @ResponseBody Object readImgId(@PathVariable(name="Id")Integer id) {
		try {
			Otras_Titulaciones ti=otras_TitulacionesRepository.findById(id).get();
		byte[] image = ti.getDocumento();
		HttpHeaders responseHeaders = new HttpHeaders();
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(image));
		responseHeaders.set("Content-Type", URLConnection.guessContentTypeFromStream(is));;
		return new ResponseEntity<byte[]>(image, responseHeaders, HttpStatus.CREATED);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}

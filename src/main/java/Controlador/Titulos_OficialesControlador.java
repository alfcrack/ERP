package Controlador;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Optional;

import javax.persistence.Access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Clases.Candidatos;
import Clases.Titulos_oficiales;
import Repositorios.CandidatosRepository;
import Repositorios.Titulos_OficialesRepository;

@Controller
@RequestMapping(path="/tituofi")
public class Titulos_OficialesControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);
	
	@Autowired
	private Titulos_OficialesRepository titulos_OficialesRepository;
	
	@Autowired
	private CandidatosRepository candidatosRepository;
	
	@CrossOrigin
	@PostMapping(path="/add/{canId}")
	public @ResponseBody Titulos_oficiales add(@RequestBody Titulos_oficiales titulos_oficiales,
			@PathVariable(name="canId")Integer canId) {
		try {
			Candidatos can = candidatosRepository.findById(canId).get();
			Titulos_oficiales tit=new Titulos_oficiales();
			tit.setCandidatos(can);
			tit.setCentro(titulos_oficiales.getCentro());
			tit.setFecha_obtencion(titulos_oficiales.getFecha_obtencion());
			tit.setNivel_Educativo(titulos_oficiales.getNivel_Educativo());
			tit.setNombre(titulos_oficiales.getNombre());
			titulos_OficialesRepository.save(tit);
			logger.info("Titulo Oficial guardado");
			return tit;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@CrossOrigin(allowedHeaders="*")
	@PostMapping(path="/addImg" , produces=MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody Object addImg(@RequestParam (name="img")MultipartFile file,
			@RequestParam(name="id") Integer id) {
		try {
			Titulos_oficiales titOfi=titulos_OficialesRepository.findById(id).get();
			titOfi.setDocumento(file.getBytes());
			titulos_OficialesRepository.save(titOfi);
			return titOfi.getDocumento();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@CrossOrigin
	@GetMapping(path="/read")
	public @ResponseBody Iterable<Titulos_oficiales>add(){
		return titulos_OficialesRepository.findAll();
	}
	@CrossOrigin
	@GetMapping(path="/read/{Id}")
	public @ResponseBody Titulos_oficiales readId(@PathVariable(name="Id")Integer id) {
		try {
		return titulos_OficialesRepository.findById(id).get();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@CrossOrigin
	@GetMapping(path="/readImg/{Id}")
	public @ResponseBody Object readImgId(@PathVariable(name="Id")Integer id) {
		try {
		Titulos_oficiales ti=titulos_OficialesRepository.findById(id).get();
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
	@CrossOrigin
	@DeleteMapping(path="/supr/{Id}")
	public @ResponseBody String suprId(@PathVariable(name="Id")Integer id) {
		try {
			titulos_OficialesRepository.deleteById(id);
			logger.info("Titulo Oficial Borrado");
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "No se han podido borrar";
	}
	
	
}

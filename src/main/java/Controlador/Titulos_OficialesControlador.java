package Controlador;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping(path="/add/{canId}")
	public @ResponseBody Titulos_oficiales add(@RequestBody Titulos_oficiales titulos_oficiales,
			@PathVariable(name="canId")Integer canId) {
		try {
			Candidatos can = candidatosRepository.findById(canId).get();
			Titulos_oficiales tit=new Titulos_oficiales();
			tit.setCandidatos(can);
			tit.setCentro(titulos_oficiales.getCentro());
			tit.setDocumento(titulos_oficiales.getDocumento());
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
	
	@GetMapping(path="/read")
	public @ResponseBody Iterable<Titulos_oficiales>add(){
		return titulos_OficialesRepository.findAll();
	}
	
	@GetMapping(path="/read/{Id}")
	public @ResponseBody Titulos_oficiales readId(@PathVariable(name="Id")Integer id) {
		try {
		return titulos_OficialesRepository.findById(id).get();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@DeleteMapping(path="/supr/{Id}")
	public String suprId(@PathVariable(name="Id")Integer id) {
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

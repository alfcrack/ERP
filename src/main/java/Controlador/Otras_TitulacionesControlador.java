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
import Clases.Otras_Titulaciones;
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
	
	@GetMapping(path="/read")
	public @ResponseBody Iterable<Otras_Titulaciones>add(){
		return otras_TitulacionesRepository.findAll();
	}
	
	@GetMapping(path="/read/{Id}")
	public @ResponseBody Otras_Titulaciones readId(@PathVariable(name="Id")Integer id) {
		try {
		return otras_TitulacionesRepository.findById(id).get();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
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
	
}

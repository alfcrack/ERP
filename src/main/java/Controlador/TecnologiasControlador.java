package Controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Clases.Tecnologias;
import Repositorios.TecnologiasRepository;

@Controller
@RequestMapping(path="/tec")
public class TecnologiasControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);
	
	@Autowired
	private TecnologiasRepository tecnologiasrepository;
	@CrossOrigin
	@PostMapping(path="/add",  consumes = "application/json", produces="application/json")
	public @ResponseBody Tecnologias addNewTecnologia(@RequestBody Tecnologias tecnologia) {
		try {
			Tecnologias tecnologiaN=new Tecnologias();
			tecnologiaN.setNombre(tecnologia.getNombre());
			tecnologiaN.setDescripcion(tecnologia.getDescripcion());
			tecnologiasrepository.save(tecnologiaN);
			logger.info("Tecnologia guardada");
			return tecnologiaN;
		}catch(Exception e) {
		}
		return null;
	}
	@CrossOrigin
	@GetMapping(path="/read")
	public @ResponseBody Iterable<Tecnologias> ReadA() {
		try {
		return tecnologiasrepository.findAll();
	}catch (Exception e) {
		logger.error(e.getMessage());
		return null;
	}
	}
	@CrossOrigin
	@GetMapping(path="read/{tecId}")
	public @ResponseBody Tecnologias readId(@PathVariable(name="tecId")Integer id) {
		try {
			return tecnologiasrepository.findById(id).get();	
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@CrossOrigin
	@DeleteMapping(path="/supr/{Id}")
	public @ResponseBody String suprId(@PathVariable(name="Id")Integer id) {
		try {
			tecnologiasrepository.deleteById(id);
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "No se han podido borrar";
	}
	@CrossOrigin
	@PutMapping(path="/update/{id}",  consumes = "application/json", produces="application/json")
	public @ResponseBody Tecnologias updateTecnologia(@RequestBody Tecnologias tecnologia,
			@PathVariable(name="id")Integer id) {
		try {
			Tecnologias tecnologiaN=tecnologiasrepository.findById(id).get();
			tecnologiaN.setNombre(tecnologia.getNombre());
			tecnologiaN.setDescripcion(tecnologia.getDescripcion());
			tecnologiasrepository.save(tecnologiaN);
			logger.info("Tecnologia actualizada");
			return tecnologiaN;
		}catch(Exception e) {
		}
		return null;
	}
}

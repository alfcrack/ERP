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

import Clases.*;
import Repositorios.Candidatos_TecnologiaRepository;
import Repositorios.*;

@Controller
@RequestMapping(path="/canTec")
public class CandidatosTecnologiasControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);
	
	@Autowired
	private Candidatos_TecnologiaRepository candidatos_TecnologiaRepository;
	
	@Autowired
	private CandidatosRepository candidatosrepository;
	
	@Autowired
	private TecnologiasRepository tecnologiasrepository;
	
	@PostMapping(path="/addCT/{candiId}/{tecnoId}",  consumes = "application/json", produces="application/json")
	public @ResponseBody Candidatos_Tecnologia addNewCanTec(@PathVariable (value = "candiId") Integer candiId,
			@PathVariable (value = "tecnoId") Integer tecnoId,
			@RequestBody Candidatos_Tecnologia candidatos_Tecnologia) {
		try {	
			Candidatos_Tecnologia candidatos_TecnologiaN=new Candidatos_Tecnologia();
			Candidatos cant=candidatosrepository.findById(candiId).get(); 
			Tecnologias tec=tecnologiasrepository.findById(tecnoId).get();
			candidatos_TecnologiaN.setTecnologia(tec);
			candidatos_TecnologiaN.setCandidatos(cant);
			candidatos_TecnologiaN.setExperiencia(candidatos_Tecnologia.getExperiencia());
			candidatos_TecnologiaRepository.save(candidatos_TecnologiaN);
			logger.info("Candidato_Tecnologia guardado");
			return candidatos_TecnologiaN;
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
		
	}
	@GetMapping(path="read")
	public @ResponseBody Iterable<Candidatos_Tecnologia> read() {
		return candidatos_TecnologiaRepository.findAll();
	}
	@GetMapping(path="read/{id}")
	public @ResponseBody Candidatos_Tecnologia readId(@PathVariable(name="id")Integer id) {
		return candidatos_TecnologiaRepository.findById(id).get();
	}
	
	@DeleteMapping(path="/supr/{Id}")
	public @ResponseBody String suprId(@PathVariable(name="Id")Integer id) {
		try {
			candidatos_TecnologiaRepository.deleteById(id);
			logger.info("Candidato_Tecnologia borrado");
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "No se han podido borrar";
	}
}

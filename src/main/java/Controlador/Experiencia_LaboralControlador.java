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
import Clases.Experiencia_Laboral;
import Repositorios.CandidatosRepository;
import Repositorios.Experiencia_LaboralRepository;

@Controller
@RequestMapping(path="/exp")
public class Experiencia_LaboralControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);
	
	@Autowired
	private Experiencia_LaboralRepository experiencia_LaboralRepository;
	
	@Autowired
	private CandidatosRepository candidatosrepository;
	
	
	@PostMapping(path="/add/{canId}")
	public @ResponseBody Experiencia_Laboral addExp(@RequestBody Experiencia_Laboral experiencia_Laboral,
			@PathVariable (name="canId")Integer canId) {
		try {
			Candidatos can=candidatosrepository.findById(canId).get();
			Experiencia_Laboral exp=new Experiencia_Laboral();
			exp.setCliente(experiencia_Laboral.getCliente());
			exp.setEmpresa(experiencia_Laboral.getEmpresa());
			exp.setFecha_fin(experiencia_Laboral.getFecha_fin());
			exp.setFecha_inicio(experiencia_Laboral.getFecha_inicio());
			exp.setFunciones(experiencia_Laboral.getFunciones());
			exp.setPuesto(experiencia_Laboral.getPuesto());
			exp.setTecnologias(experiencia_Laboral.getTecnologias());
			System.out.println(experiencia_Laboral.getCliente());
			exp.setCandidatos(can);
			experiencia_LaboralRepository.save(exp);
			logger.info("Experiencia_Laboral guardada");
			return exp;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@GetMapping(path="/read")
	public @ResponseBody Iterable<Experiencia_Laboral> readA() {
		return experiencia_LaboralRepository.findAll();
	}

	@GetMapping(path="read/{expId}")
	public @ResponseBody Experiencia_Laboral readId(@PathVariable(name="expId")Integer id) {
		try {
			return experiencia_LaboralRepository.findById(id).get();	
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
		
	}
	
	@DeleteMapping(path="/supr/{Id}")
	public String suprId(@PathVariable(name="Id")Integer id) {
		try {
			experiencia_LaboralRepository.deleteById(id);
			logger.info("Experiencia_Laboral borrada");
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "No se han podido borrar";
	}
}

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
import Repositorios.CandidatosRepository;

@Controller
@RequestMapping(path="/can")
public class CandidatosControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);

	@Autowired
	private CandidatosRepository candidatosRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody Candidatos add(@RequestBody Candidatos candidato) {
		try {
			Candidatos can=new Candidatos();
			can.setNombre(candidato.getNombre());
			can.setApellido1(candidato.getApellido1());
			can.setApellido2(candidato.getApellido2());
			can.setFecha_nacimiento(candidato.getFecha_nacimiento());
			can.setFecha_baja(candidato.getFecha_baja());
			can.setFecha_alta(candidato.getFecha_alta());
			can.setDNI(candidato.getDNI());
			can.setDireccion(candidato.getDireccion());
			can.setCodigoPostal(candidato.getCodigoPostal());
			can.setTelefono(candidato.getTelefono());
			can.setEmail(candidato.getEmail());
			can.setProvincia(candidato.getProvincia());
			can.setPoblacion(candidato.getPoblacion());
			candidatosRepository.save(can);
			logger.info("Candidato guardado");
			return can;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@GetMapping(path="read")
	public @ResponseBody Iterable<Candidatos> readA() {
		return candidatosRepository.findAll();
	}
	
	@GetMapping(path="read/{canId}")
	public @ResponseBody Candidatos readId(@PathVariable(name="canId")Integer id) {
		try {
			return candidatosRepository.findById(id).get();	
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
		
	}
	
	@DeleteMapping(path="/supr/{Id}")
	public String suprId(@PathVariable(name="Id")Integer id) {
		try {
			candidatosRepository.deleteById(id);
			logger.info("Candidato borrado");
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "No se han podido borrar";
	}
}

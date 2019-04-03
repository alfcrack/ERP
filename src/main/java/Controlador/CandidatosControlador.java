package Controlador;

import java.util.Iterator;

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

import Clases.Candidatos;
import Clases.Candidatos_Tecnologia;
import Clases.Experiencia_Laboral;
import Clases.Otras_Titulaciones;
import Clases.Titulos_oficiales;
import Repositorios.CandidatosRepository;
import Repositorios.Candidatos_TecnologiaRepository;
import Repositorios.Experiencia_LaboralRepository;
import Repositorios.Otras_TitulacionesRepository;
import Repositorios.Titulos_OficialesRepository;

@Controller
@RequestMapping(path="/can")
public class CandidatosControlador {
	
	private static Logger logger= LogManager.getLogger(CandidatosControlador.class);

	@Autowired
	private CandidatosRepository candidatosRepository;
	@Autowired
	private Titulos_OficialesRepository titulos_OficialesRepository;
	@Autowired
	private Candidatos_TecnologiaRepository candidatos_TecnologiaRepository;
	@Autowired
	private Experiencia_LaboralRepository experiencia_LaboralRepository;
	@Autowired
	private Otras_TitulacionesRepository otras_TitulacionesRepository;
	@CrossOrigin
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
	@CrossOrigin
	@GetMapping(path="read")
	public @ResponseBody Iterable<Candidatos> readA() {
		return candidatosRepository.findAll();
	}
	@CrossOrigin
	@GetMapping(path="read/{canId}")
	public @ResponseBody Candidatos readId(@PathVariable(name="canId")Integer id) {
		try {
			return candidatosRepository.findById(id).get();	
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
		
	}
	@CrossOrigin
	@DeleteMapping(path="/supr/{Id}")
	public @ResponseBody String suprId(@PathVariable(name="Id")Integer id) {
		try {
			candidatosRepository.deleteById(id);
			Iterator<Titulos_oficiales> to=titulos_OficialesRepository.findAll().iterator();
			while(to.hasNext()) { //Borrar Titulos Oficiales de ese candidato
				Titulos_oficiales too=to.next();
			if(too.getCandidatos().getId()==id) {
				titulos_OficialesRepository.deleteById(too.getId());
				}
			}
			Iterator<Candidatos_Tecnologia>ct=candidatos_TecnologiaRepository.findAll().iterator();
			while(ct.hasNext()) {
				Candidatos_Tecnologia ctO=ct.next();
				if(ctO.getCandidatos().getId()==id) {
					candidatos_TecnologiaRepository.deleteById(ctO.getId());
				}
			}
			Iterator<Experiencia_Laboral>ex=experiencia_LaboralRepository.findAll().iterator();
			while(ex.hasNext()) {
				Experiencia_Laboral exp=ex.next();
				if(exp.getCandidatos().getId()==id) {
					experiencia_LaboralRepository.deleteById(exp.getId());
				}
			}
			Iterator<Otras_Titulaciones>ot=otras_TitulacionesRepository.findAll().iterator();
			while(ot.hasNext()) {
				Otras_Titulaciones otO=ot.next();
				if(otO.getCandidatos().getId()==id) {
					otras_TitulacionesRepository.deleteById(otO.getId());
				}
			}
			logger.info("Candidato borrado");
			 return "Datos Borrados";
			}catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "No se han podido borrar";
	}
	@CrossOrigin
	@PutMapping(path="/update/{id}")
	public @ResponseBody Candidatos update(@RequestBody Candidatos candidato,
			@PathVariable(name="id")Integer id) {
		try {
			Candidatos can=candidatosRepository.findById(id).get();
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
			logger.info("Candidato Actualizado");
			return can;
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}

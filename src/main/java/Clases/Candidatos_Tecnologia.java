package Clases;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Candidatos_Tecnologia implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	
	 @ManyToOne(fetch=FetchType.LAZY,optional=false)
	 @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	 @JoinColumn(name="tecnologia_id")
	@NonNull
	private Tecnologias tecnologia;
	
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="candidato_id")
	@NonNull
	private Candidatos candidatos;
	
	@NonNull
	private String Experiencia="0";


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Tecnologias getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologias tecnologia) {
		this.tecnologia = tecnologia;
	}

	public Candidatos getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(Candidatos candidatos) {
		this.candidatos = candidatos;
	}

	public String getExperiencia() {
		return Experiencia;
	}

	public void setExperiencia(String experiencia) {
		Experiencia = experiencia;
	}


	
	
}

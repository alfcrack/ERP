package Clases;

import java.sql.Date;

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
public class Experiencia_Laboral {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	private Date fecha_inicio;
	
	@NonNull
	private Date fecha_fin;
	
	@NonNull
	private String Empresa;
	
	@NonNull
	private String Cliente;
	
	@NonNull
	private String Funciones;
	
	@NonNull
	private String Puesto;
	
	@NonNull
	private String Tecnologias;//Las tecnologias que ha usado el candidato

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="candidato_id")
	@NonNull
	private Candidatos candidatos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}

	public String getCliente() {
		return Cliente;
	}

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	public String getFunciones() {
		return Funciones;
	}

	public void setFunciones(String funciones) {
		Funciones = funciones;
	}

	public String getPuesto() {
		return Puesto;
	}

	public void setPuesto(String puesto) {
		Puesto = puesto;
	}

	public String getTecnologias() {
		return Tecnologias;
	}

	public void setTecnologias(String tecnologias) {
		Tecnologias = tecnologias;
	}

	public Candidatos getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(Candidatos candidatos) {
		this.candidatos = candidatos;
	}
	
}

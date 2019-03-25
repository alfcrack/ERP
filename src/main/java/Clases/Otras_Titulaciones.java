package Clases;

import java.net.URI;
import java.sql.Blob;
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
public class Otras_Titulaciones {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	private String Nombre;
	
	@NonNull
	private String centro;
	
	@NonNull
	private Date fecha_obtencion;
	
	private URI documento;
	

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

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public Date getFecha_obtencion() {
		return fecha_obtencion;
	}

	public void setFecha_obtencion(Date fecha_obtencion) {
		this.fecha_obtencion = fecha_obtencion;
	}

	public URI getDocumento() {
		return documento;
	}

	public void setDocumento(URI documento) {
		this.documento = documento;
	}
	
	public Candidatos getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(Candidatos candidatos) {
		this.candidatos = candidatos;
	}
	
	
}

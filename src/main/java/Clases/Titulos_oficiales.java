package Clases;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Titulos_oficiales {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	private String Nombre;
	
	@NonNull
	private String centro;
	
	@NonNull
	private Date fecha_obtencion;
	@Column(length=1622222)
	private byte[] documento;
	
	private String Nivel_Educativo;
	
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
	@JsonIgnore
	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getNivel_Educativo() {
		return Nivel_Educativo;
	}

	public void setNivel_Educativo(String nivel_Educativo) {
		Nivel_Educativo = nivel_Educativo;
	}

	public Candidatos getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(Candidatos candidatos) {
		this.candidatos = candidatos;
	}
	

}

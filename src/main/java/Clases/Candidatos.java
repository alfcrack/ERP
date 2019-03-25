package Clases;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity
public class Candidatos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	
	@NonNull
	private String Nombre;

	@NonNull
	private String Apellido1;
	
	private String Apellido2;
	
	@NonNull
	@Size(max=9)
	@Column(unique=true,name="DNI")
	private String DNI;
	
	@Email
	@NonNull
	@Column(unique=true,name="email")
	private String Email;
	
	@NonNull
	private String Poblacion;
	
	@NonNull
	private String direccion;
	
	@NonNull
	private String CodigoPostal;
	
	@NonNull
	private String Provincia;
	
	@NonNull
	@Column(unique=true,name="Telefono")
	private String Telefono;
	
	@NonNull
	@DateTimeFormat(pattern="dd/mm/yyyy")
	private Date Fecha_nacimiento;
	
	@DateTimeFormat(pattern="dd/mm/yyyy")
	private Date Fecha_alta;
	
	@DateTimeFormat(pattern="dd/mm/yyyy")
	private Date Fecha_baja;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido1() {
		return Apellido1;
	}

	public void setApellido1(String apellido1) {
		Apellido1 = apellido1;
	}

	public String getApellido2() {
		return Apellido2;
	}

	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPoblacion() {
		return Poblacion;
	}

	public void setPoblacion(String poblacion) {
		Poblacion = poblacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return CodigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public Date getFecha_nacimiento() {
		return Fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		Fecha_nacimiento = fecha_nacimiento;
	}

	public Date getFecha_alta() {
		return Fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		Fecha_alta = fecha_alta;
	}

	public Date getFecha_baja() {
		return Fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		Fecha_baja = fecha_baja;
	}
	
	
	
}

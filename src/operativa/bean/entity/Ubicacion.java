package operativa.bean.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.sun.istack.internal.NotNull;

@Entity
@Table(name="ubicacion")
public class Ubicacion {
	
	@Id
    @Column(name="id", length=8)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Basic
	@Column(name="tipo")
	@NotNull
	private String tipo;
	
	@Basic
	@Column(name="nombre")
	@NotNull
	private String nombre;
	
	@Basic
	@Column(name="localizacion")
	private String localizacion;
	
	@Basic
	@Column(name="unidades")
	@NotNull
	private Integer unidades;
	
	@Basic
	@Column(name="latitud")
	@NotNull
	private Float latitud;
	
	@Basic
	@Column(name="longitud")
	@NotNull
	private Float longitud;
	
	@RequiredStringValidator(message="La latitud es requerida")
	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	@RequiredStringValidator(message="La longitud es requerida")
	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@RequiredStringValidator(message="El nombre es requerido")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@RequiredStringValidator(message="La localización es requerida")
	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getUnidades() {
		return unidades;
	}
	
	@RequiredStringValidator(message="Debe especificar una cantidad")
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

}

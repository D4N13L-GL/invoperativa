package operativa.bean.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Fabrica")
public class Factory {
	
	@Id
    @Column(name="id", length=8)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Basic
	@Column(name="nombre")
	private String nombre;
	
	@Basic
	@Column(name="localizacion")
	private String localizacion;
	
	@Basic
	@Column(name="produccion")
	private Integer produccion;
	
	@Basic
	@Column(name="latitud")
	private Float latitud;
	
	@Basic
	@Column(name="longitud")
	private Float longitud;

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Integer getProduccion() {
		return produccion;
	}

	public void setProduccion(Integer produccion) {
		this.produccion = produccion;
	}

}

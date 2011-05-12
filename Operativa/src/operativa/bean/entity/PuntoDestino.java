package operativa.bean.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="punto_destino")
public class PuntoDestino {
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
	@Column(name="demanda")
	private Integer demanda;
	
	@Basic
	@Column(name="latitud")
	private Float latitud;
	
	@Basic
	@Column(name="longitud")
	private Float longitud;
	
//	@OneToMany (cascade = {CascadeType.ALL},
//			fetch = FetchType.EAGER)
//	@JoinColumn (name = "punto_destino_id")
//	@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//	private List<Costo> costos = new ArrayList<Costo>();

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

	public Integer getDemanda() {
		return demanda;
	}

	public void setDemanda(Integer demanda) {
		this.demanda = demanda;
	}

//	public List<Costo> getCostos() {
//		return costos;
//	}
//
//	public void setCostos(ArrayList<Costo> costos) {
//		this.costos = costos;
//	}
}

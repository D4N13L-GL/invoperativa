package operativa.bean.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Costo")
public class Costo {
	
	@Id
    @Column(name="id", length=8)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne (cascade = CascadeType.ALL)
//	@JoinColumn (name="fabrica_id", updatable = false, insertable = false)
    private Factory fabrica;
	
	@ManyToOne (cascade = CascadeType.ALL)
//	@JoinColumn (name="punto_destino_id", updatable = false, insertable = false)
    private PuntoDestino punto_destino;
	
	@Column(name="costo")
	private Float costo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getCosto() {
		return costo;
	}

	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Factory getFabrica() {
		return fabrica;
	}

	public void setFabrica(Factory fabrica) {
		this.fabrica = fabrica;
	}

	public PuntoDestino getDestino() {
		return punto_destino;
	}

	public void setDestino(PuntoDestino destino) {
		this.punto_destino = destino;
	}

}

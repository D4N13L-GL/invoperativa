package operativa.bean.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Costo")
public class Costo {
	
	@Id
    @Column(name="id", length=8)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
    @Column(name="fabrica_id")
    private Integer fabrica;
	
	@Column(name="punto_destino_id")
    private Integer destino;
	
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

	public Integer getFabrica() {
		return fabrica;
	}

	public void setFabrica(Integer fabrica) {
		this.fabrica = fabrica;
	}

	public Integer getDestino() {
		return destino;
	}

	public void setDestino(Integer destino) {
		this.destino = destino;
	}

}

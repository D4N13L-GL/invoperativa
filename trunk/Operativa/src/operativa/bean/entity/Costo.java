package operativa.bean.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "Costo")
public class Costo {
	
	@Id
    @Column(name="id", length=8)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@NotNull
    private Ubicacion desde;
	
	@ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@NotNull
    private Ubicacion hasta;
	
	@Column(name="costo")
	@NotNull
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

	public Ubicacion getDesde() {
		return desde;
	}

	public void setDesde(Ubicacion desde) {
		this.desde = desde;
	}

	public Ubicacion getHasta() {
		return hasta;
	}

	public void setHasta(Ubicacion hasta) {
		this.hasta = hasta;
	}


}

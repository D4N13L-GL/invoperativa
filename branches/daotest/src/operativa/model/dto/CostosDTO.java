package operativa.model.dto;

import operativa.bean.entity.Ubicacion;

public class CostosDTO {

	private String id;
	private Ubicacion origen;
	private Ubicacion destino;
	private float costo;
	public CostosDTO(Ubicacion destino2) {
		this.destino = destino2;
		this.id = destino.getId().toString();
	}
	public Ubicacion getDestino() {
		return destino;
	}
	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Ubicacion getOrigen() {
		return origen;
	}
	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}
	
}

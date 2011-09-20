package operativa.action.utils;

import operativa.bean.entity.Ubicacion;

public class UbicacionRecorrido{
	private Ubicacion ubicacion;
	private String elegida;
	
	public UbicacionRecorrido(Ubicacion u, String e){
		this.ubicacion = u;
		this.elegida = e;
	}
	
	public UbicacionRecorrido(Ubicacion f) {
		this.ubicacion = f;
		elegida = "false";
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getElegida() {
		return elegida;
	}
	
	public void setElegida(String elegida) {
		this.elegida = elegida;
	}
}
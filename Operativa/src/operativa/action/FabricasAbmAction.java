package operativa.action;

import operativa.model.dao.FabricaDAO;

public class FabricasAbmAction {
	
	private FabricaDAO fabricaDao = new FabricaDAO();
	
	private String localizacion;
	private String produccion;
	private String nombre;
	
	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getProduccion() {
		return produccion;
	}

	public void setProduccion(String produccion) {
		this.produccion = produccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	private String latitud;
	private String longitud;
	
	public String crearFabrica(){
		try{
			fabricaDao.crearFabrica(localizacion, nombre, produccion, latitud, longitud);
			return "exito";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
}

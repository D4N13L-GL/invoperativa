package operativa.action;

import operativa.model.dao.FabricaDAO;

public class FabricasAbmAction {
	
	private FabricaDAO fabricaDao = new FabricaDAO();
	
	private String localizacion;
	private String produccion;
	private String nombre;
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

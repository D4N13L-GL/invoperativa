package operativa.action;

import java.util.ArrayList;
import java.util.List;

import operativa.bean.entity.Ubicacion;
import operativa.model.dao.UbicacionDAO;

import com.opensymphony.xwork2.ActionSupport;

public class TravelerAction extends ActionSupport{
	
	private UbicacionDAO ubicacionDao = new UbicacionDAO();
	private static final long serialVersionUID = 1L;
	
	private Ubicacion origen;
	private List<Ubicacion> recorrido = new ArrayList<Ubicacion>();
	
	public String prepareViajante(){
		origen = ubicacionDao.findById(21, false);
		recorrido.add(origen);
		recorrido.add(ubicacionDao.findById(22, false));
		recorrido.add(ubicacionDao.findById(24, false));
		recorrido.add(ubicacionDao.findById(25, false));
		recorrido.add(ubicacionDao.findById(26, false));
		recorrido.add(ubicacionDao.findById(27, false));
		
		return "viajante";
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}

	public List<Ubicacion> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(List<Ubicacion> recorrido) {
		this.recorrido = recorrido;
	}
	
	public String getLocalizacionOrigen(){
		return this.origen.getLocalizacion();
	}
}

package operativa.action;

import java.util.ArrayList;
import java.util.List;

import operativa.bean.entity.Ubicacion;
import operativa.model.dao.UbicacionDAO;
import operativa.utils.Constantes;

import algorithm.TMViewRow;
import algorithm.TransportMatrix;
import algorithm.VogelAlgorithm;

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
		calculateModel();
		return "viajante";
	}
	
	private List<Ubicacion> factories;
	private List<Ubicacion> listDestinos;
	private TransportMatrix matrix;
		
	public void calculateModel(){
		
		
		VogelAlgorithm vogel = new VogelAlgorithm();
		UbicacionDAO dao = new UbicacionDAO();
		
		//Extraigo las fabricas y los puntos destino de la base
		factories = dao.getByTipoUbicacion(Constantes.TipoUbicacion.FABRICA.toString());
		listDestinos = dao.getByTipoUbicacion(Constantes.TipoUbicacion.DESTINO.toString());
		
		//Ejecuto el algoritmo con las fabricas y puntos de destino
		matrix = vogel.resolve(factories, listDestinos);
		
		/**
		 * TODO Aca se deberia acoplar el algoritmo para sacar la mejor solucion
		 */
		
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

	public UbicacionDAO getUbicacionDao() {
		return ubicacionDao;
	}

	public void setUbicacionDao(UbicacionDAO ubicacionDao) {
		this.ubicacionDao = ubicacionDao;
	}

	public List<Ubicacion> getFactories() {
		return factories;
	}

	public void setFactories(List<Ubicacion> factories) {
		this.factories = factories;
	}

	public TransportMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(TransportMatrix matrix) {
		this.matrix = matrix;
	}
	
	public List<TMViewRow> getMatrixByRow(){
		return matrix.getMatrixByRow();
	}

	public List<Ubicacion> getListDestinos() {
		return listDestinos;
	}

	public void setListDestinos(List<Ubicacion> listDestinos) {
		this.listDestinos = listDestinos;
	}
}

package operativa.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operativa.bean.entity.Ubicacion;
import operativa.model.dao.UbicacionDAO;

import org.apache.struts2.ServletActionContext;

import algorithm.OptimalTransport;
import algorithm.TMViewRow;
import algorithm.TransportMatrix;
import algorithm.VogelAlgorithm;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TravelerAction extends ActionSupport{
	
	private UbicacionDAO ubicacionDao = new UbicacionDAO();
	private static final long serialVersionUID = 1L;
	
	private Ubicacion origen;
	private List<Ubicacion> recorrido = new ArrayList<Ubicacion>();
	
	private List<Ubicacion> factories = new ArrayList<Ubicacion>();
	private List<Ubicacion> listDestinos= new ArrayList<Ubicacion>();
	private TransportMatrix matrix;
		
	public String calculateModel(){
		
		
		VogelAlgorithm vogel = new VogelAlgorithm();
		
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		//Obtengo fabricas y destinos del request
		String[] fabIds = request.getParameter("fabricas").split(",");
		String[] destIds = request.getParameter("destinos").split(",");
		for (int i = 0; i < fabIds.length; i++) {
			factories.add(ubicacionDao.findById(Integer.parseInt(fabIds[i]), true));
		}
		for (int i = 0; i < destIds.length; i++) {
			listDestinos.add(ubicacionDao.findById(Integer.parseInt(destIds[i]), true));
		}
		
		//Ejecuto el algoritmo con las fabricas y puntos de destino
		matrix = vogel.resolve(factories, listDestinos);
		
		/**
		 * TODO Aca se deberia acoplar el algoritmo para sacar la mejor solucion
		 */
		
		OptimalTransport oT = new OptimalTransport();
		
		matrix = oT.getOptimalSolution(matrix);
		
		return SUCCESS;
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

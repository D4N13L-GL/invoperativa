package operativa.action;

import java.util.List;

import operativa.bean.entity.Ubicacion;
import operativa.model.dao.UbicacionDAO;
import operativa.utils.Constantes;
import algorithm.TransportMatrix;
import algorithm.VogelAlgorithm;

import com.opensymphony.xwork2.ActionSupport;

public class TransportModelAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Ubicacion> factories;
	
	private List<Ubicacion> endPoints;
		
	public void calculateModel(){
		
		
		VogelAlgorithm vogel = new VogelAlgorithm();
		UbicacionDAO dao = new UbicacionDAO();
		
		//Extraigo las fabricas y los puntos destino de la base
		factories = dao.getByTipoUbicacion(Constantes.TipoUbicacion.FABRICA.toString());
		endPoints = dao.getByTipoUbicacion(Constantes.TipoUbicacion.DESTINO.toString());
		
		//Ejecuto el algoritmo con las fabricas y puntos de destino
		TransportMatrix matrix = vogel.resolve(factories, endPoints);
		
		/**
		 * TODO Aca se deberia acoplar el algoritmo para sacar la mejor solucion
		 */
		
	}
	
	

}

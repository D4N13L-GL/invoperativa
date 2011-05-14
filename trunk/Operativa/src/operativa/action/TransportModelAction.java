package operativa.action;

import java.util.List;

import operativa.bean.entity.Ubicacion;
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
		TransportMatrix matrix = vogel.resolve(factories, endPoints);	
		
	}
	
	

}

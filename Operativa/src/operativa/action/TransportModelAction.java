package operativa.action;

import java.util.List;

import operativa.bean.entity.Factory;
import operativa.bean.entity.PuntoDestino;

import algorithm.TransportMatrix;
import algorithm.VogelAlgorithm;

import com.opensymphony.xwork2.ActionSupport;

public class TransportModelAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Factory> factories;
	
	private List<PuntoDestino> endPoints;
	
	public void calculateModel(){
		
		VogelAlgorithm vogel = new VogelAlgorithm();		
		TransportMatrix matrix = vogel.resolve(factories, endPoints);	
		
	}
	
	

}

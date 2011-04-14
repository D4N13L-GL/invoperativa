package algorithm;

import java.util.List;

import operativa.bean.entity.Factory;
import operativa.bean.entity.PuntoDestino;

public class VogelAlgorithm {

	
	public TransportMatrix resolve(List<Factory> factories, List<PuntoDestino> destinations){		
		
		TransportMatrix matrix = new TransportMatrix(factories, destinations);
		this.resolveReal(matrix);			
		return matrix;
		
	}
	
	private void resolveReal(TransportMatrix matrix){
		int[] differencePos = new int[2];
		differencePos = matrix.getMaxDifference();
		if (differencePos[0] == -1){
			int mincost = matrix.getMinCostColumn(differencePos[1]);
			if(matrix.getOfert(mincost)!= 0 || matrix.getDemand(differencePos[1]) != 0){
				matrix.assign(mincost,differencePos[1]);
				this.resolveReal(matrix);
			}			
		}else{
			int mincost = matrix.getMinCostRow(differencePos[0]);
			if (matrix.getOfert(differencePos[0])!= 0 || matrix.getDemand(mincost)!= 0) {
				matrix.assign(differencePos[0], mincost);
				this.resolveReal(matrix);
			}			
		}
	}
}

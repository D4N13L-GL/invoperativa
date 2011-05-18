package algorithm;

import java.util.List;

import operativa.bean.entity.Ubicacion;

public class VogelAlgorithm {

	private int iterations;
	
	
	public TransportMatrix resolve(List<Ubicacion> factories, List<Ubicacion> destinations){		
		
		//Creo la matriz para el algoritmo con las fabricas y los puntos de destino
		TransportMatrix matrix = new TransportMatrix(factories, destinations);
		
		//Ejecuto la funcion que resuelve el algoritmo verdaderamente
		this.resolveReal(matrix);			
		
		return matrix;
		
	}
	
	private void resolveReal(TransportMatrix matrix){
		iterations++;
		int[] differencePos = new int[2];
		differencePos = matrix.getMaxDifference();
		if (differencePos[0] == -1){
			int mincost = matrix.getMinCostColumn(differencePos[1]);
			if(matrix.getOfert(mincost)!= 0 || matrix.getDemand(differencePos[1]) != 0){
				matrix.assign(mincost,differencePos[1]);
				matrix.setDifferencies();
				matrix.print();
				this.resolveReal(matrix);
			}			
		}else{
			if (differencePos[0] != -1){
				int mincost = matrix.getMinCostRow(differencePos[0]);
				if (matrix.getOfert(differencePos[0])!= 0 || matrix.getDemand(mincost)!= 0) {
					matrix.assign(differencePos[0], mincost);
					matrix.setDifferencies();
					matrix.print();
					this.resolveReal(matrix);
				}
			}
		}
	}
}

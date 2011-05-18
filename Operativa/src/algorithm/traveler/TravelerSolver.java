package algorithm.traveler;

import java.util.ArrayList;

import operativa.bean.entity.Costo;
import operativa.model.dao.CostoDAO;

public class TravelerSolver {

	private static final long serialVersionUID = 1L;
	private static CostoDAO costoDao;
	private static ArrayList<Double> partialResults = new ArrayList<Double>();
	
	public TravelerSolver() {
		super();
		costoDao = new CostoDAO();
	}
	
	public static void main(String[] args) {
	      TravelerSolver t = new TravelerSolver();
	      Integer[] A = new Integer[]{20,22,24,25,26,27};
	      Integer[] solution = new Integer[A.length];
	      Double costoFinal = 0.0;
	      costoFinal = t.tspBacktracking(A, 0, 0.0, 10000.0, A.length-1, solution);
	      
	      System.out.println("Parciales:");
	      for (Double d : partialResults) {
	    	  System.out.println(d);
	      }
	      System.out.println();
	      
	      System.out.println("Solución óptima:");
	      for (int i = 0; i < solution.length; i++) {
    		  System.out.print(solution[i] + "--");
	      }
	      
	      System.out.println();
	      System.out.println("Optimo: " + costoFinal);
	  }

	public double tspBacktracking(Integer[] A, int L, double lengthSoFar, double minCost, int n, Integer[] S){
		if (L == n){
			lengthSoFar += distance(A[n],A[0]);
			partialResults.add(lengthSoFar);
			if (lengthSoFar < minCost){
				minCost = lengthSoFar; 
				copyArray(A, S);
			}
		}
		else{
			for (int i = L + 1; i <= n; i++) {
				swap(A, L+1, i);
				double newLength = lengthSoFar + distance(A[L],A[L + 1]);
				if (newLength >= minCost)
					continue;
				else
					minCost = Math.min(minCost, tspBacktracking(A, L + 1, newLength, minCost, n, S));
				swap(A, L+1, i);
			}
		}
		return minCost;
	}

	private void copyArray(Integer[] a, Integer[] solution) {
		for (int i = 0; i < a.length; i++) {
			solution[i] = new Integer(a[i].intValue());
		}
	}

	private void swap(Integer[] a, int i, int j) {
		Integer aux;
		aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}

	private double distance(Integer i, Integer j) {
		Costo c = costoDao.findCost(i, j);
	    return c.getCosto();
	}

}

package algorithm;

import java.util.ArrayList;
import java.util.List;

import org.ejml.alg.dense.linsol.LinearSolver;
import org.ejml.alg.dense.linsol.LinearSolverFactory;
import org.ejml.alg.dense.linsol.LinearSolverSafe;
import org.ejml.data.DenseMatrix64F;


public class OptimalTransport {

	private double[][] coefCosts;

	private int firstCol;
	private int firstRow;

	public OptimalTransport() {
		super();
	}

	public TransportMatrix getOptimalSolution(TransportMatrix m) {
		List<Integer> uDualList = new ArrayList<Integer>();
		List<Integer> vDualList = new ArrayList<Integer>();
		loadDualLists(m, uDualList, vDualList);

		return searchForOptimalSolution(m, uDualList, vDualList);

	}

	public TransportMatrix searchForOptimalSolution(TransportMatrix m,
			List<Integer> uDualList, List<Integer> vDualList) {
		if (!this.isOptimo(m, uDualList, vDualList)) {
			TransportMatrix matrix = retrieveSolution(m, uDualList, vDualList);
			return getOptimalSolution(m);
		} else
			return m;
	}

	private TransportMatrix retrieveSolution(TransportMatrix m,
			List<Integer> uDualList, List<Integer> vDualList) {

		int maxRow = 0;
		int maxCol = 0;

		double maxValue = Double.NEGATIVE_INFINITY;

		for (int i = 0; i < uDualList.size(); i++) {

			for (int j = 0; j < vDualList.size(); j++) {

				if (uDualList.get(i) + vDualList.get(j) - m.getCost(i, j) >= maxValue) {
					maxRow = i;
					maxCol = j;
					maxValue = uDualList.get(i) + vDualList.get(j)
							- m.getCost(i, j);
				}

			}

		}
		return interchangeValues(m, maxRow, maxCol);

	}

	private TransportMatrix interchangeValues(TransportMatrix m, int maxRow,
			int maxCol) {

		List<TMCell> toSumTeta = new ArrayList<TMCell>();
		List<TMCell> toRestTeta = new ArrayList<TMCell>();

		obtainTetaLists(m, maxRow, maxCol, toSumTeta, toRestTeta);

		double minTeta = getMinimumTeta(toRestTeta);

		applyTransformation(toSumTeta, toRestTeta, minTeta);

		return m;
	}

	private void applyTransformation(List<TMCell> toSumTeta,
			List<TMCell> toRestTeta, double minTeta) {

		for (TMCell tmCell : toSumTeta) {
			tmCell.setAssign((int) (tmCell.getAssign() + (int)minTeta));
		}

		for (TMCell tmCell : toRestTeta) {
			tmCell.setAssign((int) (tmCell.getAssign() - (int)minTeta));
		}

	}

	private double getMinimumTeta(List<TMCell> toRestTeta) {

		double minimum = Double.POSITIVE_INFINITY;
		for (TMCell tmCell : toRestTeta) {
			if (tmCell.getAssign() <= minimum) {
				minimum = tmCell.getAssign();
			}
		}

		return minimum;
	}

	private void obtainTetaLists(TransportMatrix m, int maxRow, int maxCol,
			List<TMCell> toSumTeta, List<TMCell> toRestTeta) {

		this.firstCol = maxCol;
		this.firstRow = maxRow;

		System.out.println("First Col: " + firstCol);
		System.out.println("First Row: " + firstRow);
		toSumTeta.add(m.getCell(maxRow, maxCol));

		if (!obtainTetaListsByColBackwards(m, maxRow, maxCol, toSumTeta,
				toRestTeta, false, 2, 0))
			toSumTeta.clear();

	}

	private boolean obtainTetaListsByColBackwards(TransportMatrix m,
			int maxRow, int maxCol, List<TMCell> toSumTeta,
			List<TMCell> toRestTeta, boolean add, int step, int deep) {

		if (step == 4) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+maxCol);
			System.out.println("Fila: "+firstRow);
			
			if (add)
				toSumTeta.add(m.getCell(maxRow, firstCol));
			else
				toRestTeta.add(m.getCell(maxRow, firstCol));

			return true;
		}

		if (deep == 4) {
			return false;
		}

		int newColumn = m.getNextBasicByColBackward(maxRow, maxCol);

		if (newColumn == -1) {
			return obtainTetaListsByColFoward(m, maxRow, maxCol, toSumTeta,
					toRestTeta, add, step, deep + 1);
		}

		if (m.isValidByRow(maxRow, newColumn)) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+newColumn);
			System.out.println("Fila: "+maxRow);
			if (add)
				toSumTeta.add(m.getCell(maxRow, newColumn));
			else
				toRestTeta.add(m.getCell(maxRow, newColumn));

			return obtainTetaListsByColFoward(m, maxRow, newColumn, toSumTeta,
					toRestTeta, !add, step + 1, 0);

		}

		return obtainTetaListsByColBackwards(m, maxRow, newColumn, toSumTeta,
				toRestTeta, add, step, deep);

	}

	private boolean obtainTetaListsByColFoward(TransportMatrix m, int maxRow,
			int maxCol, List<TMCell> toSumTeta, List<TMCell> toRestTeta,
			boolean add, int step, int deep) {

		if (step == 4) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+maxCol);
			System.out.println("Fila: "+firstRow);
			if (add)
				toSumTeta.add(m.getCell(maxRow, firstCol));
			else
				toRestTeta.add(m.getCell(maxRow, firstCol));
			return true;
		}

		if (deep == 4) {
			return false;
		}

		int newColumn = m.getNextBasicByColFoward(maxRow, maxCol);

		if (newColumn == -1) {
			return obtainTetaListsByRowBackwards(m, maxRow, maxCol, toSumTeta,
					toRestTeta, add, step, deep + 1);
		}

		if (m.isValidByRow(maxRow, newColumn)) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+newColumn);
			System.out.println("Fila: "+maxRow);
			if (add)
				toSumTeta.add(m.getCell(maxRow, newColumn));
			else
				toRestTeta.add(m.getCell(maxRow, newColumn));

			return obtainTetaListsByRowBackwards(m, maxRow, newColumn,
					toSumTeta, toRestTeta, !add, step + 1, 0);

		}

		return obtainTetaListsByColFoward(m, maxRow, newColumn, toSumTeta,
				toRestTeta, add, step, deep);

	}

	private boolean obtainTetaListsByRowBackwards(TransportMatrix m,
			int maxRow, int maxCol, List<TMCell> toSumTeta,
			List<TMCell> toRestTeta, boolean add, int step, int deep) {

		if (step == 4) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+maxCol);
			System.out.println("Fila: "+maxRow);
			
			if (add)
				toSumTeta.add(m.getCell(firstRow, maxCol));
			else
				toRestTeta.add(m.getCell(firstRow, maxCol));
			
			return true;
		}
		if (deep == 4) {
			return false;
		}

		int newRow = m.getNextBasicByRowBackward(maxRow, maxCol);

		if (newRow == -1) {
			return obtainTetaListsByRowFoward(m, maxRow, maxCol, toSumTeta,
					toRestTeta, add, step, deep + 1);
		}

		if (m.isValidByCol(newRow, maxCol)) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+maxCol);
			System.out.println("Fila: "+newRow);
			if (add)
				toSumTeta.add(m.getCell(newRow, maxCol));
			else
				toRestTeta.add(m.getCell(newRow, maxCol));

			return obtainTetaListsByColBackwards(m, newRow, maxRow, toSumTeta,
					toRestTeta, !add, step + 1, 0);

		}

		return obtainTetaListsByRowBackwards(m, newRow, maxCol, toSumTeta,
				toRestTeta, add, step, deep);

	}

	private boolean obtainTetaListsByRowFoward(TransportMatrix m, int maxRow,
			int maxCol, List<TMCell> toSumTeta, List<TMCell> toRestTeta,
			boolean add, int step, int deep) {

		if (step == 4) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+maxCol);
			System.out.println("Fila: "+maxRow);
			if (add)
				toSumTeta.add(m.getCell(firstRow, maxCol));
			else
				toRestTeta.add(m.getCell(firstRow, maxCol));
			return true;
		}
		if (deep == 4) {
			return false;
		}

		int newRow = m.getNextBasicByRowFoward(maxRow, maxCol);

		if (newRow == -1) {
			return obtainTetaListsByColBackwards(m, maxRow, maxCol, toSumTeta,
					toRestTeta, add, step, deep + 1);
		}

		if (m.isValidByCol(newRow, maxCol)) {
			System.out.println("Nuevo: "+add);
			System.out.println("Columna: "+maxCol);
			System.out.println("Fila: "+newRow);
			if (add)
				toSumTeta.add(m.getCell(newRow, maxCol));
			else
				toRestTeta.add(m.getCell(newRow, maxCol));

			return obtainTetaListsByColBackwards(m, newRow, maxCol, toSumTeta,
					toRestTeta, !add, step + 1, 0);

		}

		return obtainTetaListsByRowFoward(m, newRow, maxCol, toSumTeta,
				toRestTeta, add, step, deep);
	}

	private boolean isOptimo(TransportMatrix m, List<Integer> uDualList,
			List<Integer> vDualList) {

		for (int i = 0; i < uDualList.size(); i++) {

			for (int j = 0; j < vDualList.size(); j++) {

				if (uDualList.get(i) + vDualList.get(j) - m.getCost(i, j) > 0)

					return false;
			}
		}
		return true;
	}

	private void loadDualLists(TransportMatrix m, List<Integer> uDualList,
			List<Integer> vDualList) {
		int equationQuantity = m.getRows() + m.getColumns() - 1;

		DenseMatrix64F A = new DenseMatrix64F(CreateCoefMatrix(m,
				equationQuantity));
		DenseMatrix64F B = new DenseMatrix64F(this.getCostsMatrix());
		DenseMatrix64F X = new DenseMatrix64F(this.getCostsMatrix());

		LinearSolver<DenseMatrix64F> solver = LinearSolverFactory.general(0, 0);
		solver = new LinearSolverSafe<DenseMatrix64F>(solver);

		solver.setA(A);
		solver.solve(B, X);

		this.ObtainDualLists(X.getData(), uDualList, vDualList, m.getRows(),
				m.getColumns(), equationQuantity);
	}

	private double[][] CreateCoefMatrix(TransportMatrix m, int eqc) {
		List<MatrixElem> basics = m.getBasicVars();
		double[][] CoefMatrix = new double[eqc][eqc];
		this.coefCosts = new double[eqc][1];
		// inicializo la matriz con 0
		for (int i = 0; i < eqc; i++) {
			for (int j = 0; j < eqc; j++) {
				CoefMatrix[i][j] = 0;
			}
		}
		int k = 0;
		while (k < basics.size() && k < eqc) {
			CoefMatrix[k][basics.get(k).getColumn()] = 1;
			CoefMatrix[k][m.getColumns() - 1 + basics.get(k).getRow()] = 1;
			coefCosts[k][0] = (double) basics.get(k).getCell().getCost();
			k++;
		}
		return CoefMatrix;
	}

	private double[][] getCostsMatrix() {
		return this.coefCosts;
	}

	private void ObtainDualLists(double[] solution, List<Integer> uDualList,
			List<Integer> vDualList, int rows, int colums, int eqc) {
		for (int i = 0; i < rows; i++) {
			uDualList.add(new Integer((int) solution[i]));
		}

		for (int j = rows; j < eqc; j++) {
			vDualList.add(new Integer((int) solution[j]));
		}

	}

}
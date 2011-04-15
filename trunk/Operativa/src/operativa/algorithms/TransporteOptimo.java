package operativa.algorithms;

import java.util.ArrayList;
import java.util.List;

import algorithm.MatrixElem;
import algorithm.TMCell;
import algorithm.TransportMatrix;

import com.welsungo.math.systemEqs.SistemaEcuaciones;

public class TransporteOptimo {

	public TransporteOptimo() {
		super();
	}

	public TransportMatrix getOptimalSolution(TransportMatrix m) {
		List<Integer> uDualList = new ArrayList<Integer>();
		List<Integer> vDualList = new ArrayList<Integer>();
		loadDualLists(m, uDualList, vDualList);

		if (!this.isOptimo(m, uDualList, vDualList)) {
			return retrieveSolution(m, uDualList, vDualList);
		}
		return m;
	}

	private TransportMatrix retrieveSolution(TransportMatrix m,
			List<Integer> uDualList, List<Integer> vDualList) {

		int maxRow = 0;
		int maxCol = 0;

		double maxValue = Double.MIN_VALUE;

		for (int i = 0; i < m.getRows(); i++) {

			for (int j = m.getRows(); j < m.getColumns(); j++) {

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

		obtainTetaListsByColumn(m, maxRow, maxCol, maxRow, maxCol, maxRow,
				maxCol, toSumTeta, toRestTeta, false);

		double minTeta = getMinimumTeta(toRestTeta);

		applyTransformation(toSumTeta, toRestTeta, minTeta);

		return m;
	}

	private void applyTransformation(List<TMCell> toSumTeta,
			List<TMCell> toRestTeta, double minTeta) {

		for (TMCell tmCell : toSumTeta) {
			tmCell.setAssign((int) (tmCell.getAssign() + minTeta));
		}

		for (TMCell tmCell : toRestTeta) {
			tmCell.setAssign((int) (tmCell.getAssign() - minTeta));
		}

	}

	private double getMinimumTeta(List<TMCell> toRestTeta) {

		double minimum = Double.MAX_VALUE;
		for (TMCell tmCell : toRestTeta) {
			if (tmCell.getAssign() <= minimum) {
				minimum = tmCell.getAssign();
			}
		}
		return minimum;
	}

	private void obtainTetaListsByColumn(TransportMatrix m, int maxRow,
			int maxCol, int fRow, int fCol, int lVRow, int lVCol,
			List<TMCell> toSumTeta, List<TMCell> toRestTeta, boolean add) {

		int auxRow = maxRow;
		int auxCol = maxCol;

		int nexRow = m.getNextBasicByCol(auxRow, auxCol, lVRow, lVCol, fRow,
				fCol);
		if (nexRow != auxRow) {
			if (add)
				toSumTeta.add(m.getCell(nexRow, auxCol));
			else
				toRestTeta.add(m.getCell(nexRow, auxCol));

			if (nexRow != fRow && auxCol != fCol)
			obtainTetaListsByRow(m, nexRow, auxCol, maxRow, maxCol, fRow, fCol,
					toSumTeta, toRestTeta, !add);
		} else {
			
			obtainTetaListsByRow(m, nexRow, auxCol, maxRow, maxCol, fRow, fCol,
					toSumTeta, toRestTeta, add);
		}
	}

	private void obtainTetaListsByRow(TransportMatrix m, int maxRow,
			int maxCol, int fRow, int fCol, int lVRow, int lVCol,
			List<TMCell> toSumTeta, List<TMCell> toRestTeta, boolean add) {

		int auxRow = maxRow;
		int auxCol = maxCol;

		int nexCol = m.getNextBasicByRow(auxRow, auxCol,lVRow, lVCol, fRow,
				fCol);
		if (nexCol != auxRow) {
			if (add)
				toSumTeta.add(m.getCell(auxRow, nexCol));
			else
				toRestTeta.add(m.getCell(auxRow, nexCol));

			if (auxRow != fRow && nexCol != fCol)
				obtainTetaListsByColumn(m, auxRow, nexCol, maxRow, maxCol,
						fRow, fCol, toSumTeta, toRestTeta, !add);
		} else {
			
			obtainTetaListsByColumn(m, auxRow, nexCol, maxRow, maxCol, fRow,
					fCol, toSumTeta, toRestTeta, add);
		}

	}

	private boolean isOptimo(TransportMatrix m, List<Integer> uDualList,
			List<Integer> vDualList) {
		for (int i = 0; i < m.getRows(); i++) {

			for (int j = m.getRows(); j < m.getColumns(); j++) {

				if (uDualList.get(i) + vDualList.get(j) - m.getCost(i, j) > 0)

					return false;
			}
		}
		return true;
	}

	private void loadDualLists(TransportMatrix m, List<Integer> uDualList,
			List<Integer> vDualList) {
		int equationQuantity = m.getRows() + m.getColumns();
		SistemaEcuaciones system = new SistemaEcuaciones();
		system.setNumEcs(equationQuantity);
		system.setCoef(CreateCoefMatrix(m, equationQuantity));
		this.ObtainDualLists(system.getSolucion(), uDualList, vDualList,
				m.getRows(), m.getColumns(), equationQuantity);

	}

	private double[][] CreateCoefMatrix(TransportMatrix m, int eqc) {
		List<MatrixElem> basics = m.getBasicVars();
		double[][] CoefMatrix = new double[eqc][eqc + 1];
		// inicializo la matriz con 0
		for (int i = 0; i < eqc; i++) {
			for (int j = 0; j < eqc + 1; j++) {
				CoefMatrix[i][j] = 0;
			}
		}
		int k = 0;
		while (k < basics.size() && k < eqc) {
			CoefMatrix[k][basics.get(k).getColumn()] = 1;
			CoefMatrix[k][m.getColumns() - 1 + basics.get(k).getRow()] = 1;
			CoefMatrix[k][eqc - 1] = (double) basics.get(k).getCell().getCost();
			k++;
		}
		return CoefMatrix;

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

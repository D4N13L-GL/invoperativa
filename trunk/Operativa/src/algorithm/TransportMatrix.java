package algorithm;

import java.util.ArrayList;
import java.util.List;

import operativa.bean.entity.Ubicacion;
import operativa.model.dao.CostoDAO;
import operativa.utils.Constantes;

public class TransportMatrix {

	private TMCell[][] matrix;

	private List<Ubicacion> factories;

	private List<Ubicacion> destinations;

	private CostoDAO costDAO;

	private Float[] rowDifferencies;

	private Float[] columnDifferencies;

	private Integer[] demands;

	private Integer[] oferts;

	public TransportMatrix(List<Ubicacion> factories,
			List<Ubicacion> destinations) {

		this.costDAO = new CostoDAO();
		this.factories = factories;
		this.destinations = destinations;
		
		this.balanceMatrix();
		
		this.rowDifferencies = new Float[factories.size()];
		this.columnDifferencies = new Float[destinations.size()];
		this.demands = new Integer[destinations.size()];
		this.oferts = new Integer[factories.size()];
		
		this.buildMatrix();
		this.setOfertsAndDemands();
		this.setDifferencies();
	}

	private void balanceMatrix() {
		
		int ofertaTotal = 0;
		int demandaTotal = 0;
		for (int i = 0; i < factories.size(); i++) {
			ofertaTotal += factories.get(i).getUnidades(); 
		}
		
		for (int i = 0; i < destinations.size(); i++) {
			demandaTotal += destinations.get(i).getUnidades();
		}
		
		int diff = ofertaTotal - demandaTotal;
		
		if (diff > 0){
			Ubicacion newOne = new Ubicacion();
			newOne.setUnidades(diff);
			newOne.setNombre("FalseDestination");
			newOne.setTipo(Constantes.TipoUbicacion.DESTINO.toString());
			this.destinations.add(newOne);
		} else {
			if (diff < 0){
				Ubicacion newOne = new Ubicacion();
				newOne.setUnidades(-diff);
				newOne.setNombre("FalseFactory");
				newOne.setTipo(Constantes.TipoUbicacion.FABRICA.toString());
				this.factories.add(newOne);
			}
		}
	}

	//Calcula las diferencias maximas por fila y luego por columna
	//Anda bien
	public void setDifferencies() {

		for (int i = 0; i < rowDifferencies.length; i++) {
			float val1 = -1f;
			float val2 = -1f;
			for (int j = 0; j < demands.length; j++) {
				if (this.matrix[i][j].isValid()) {
					if (val1 >= this.matrix[i][j].getCost() || val1 == -1f) {
						val2 = val1;
						val1 = this.matrix[i][j].getCost();
					} else if (val2 >= this.matrix[i][j].getCost()
							|| val2 == -1) {
						val2 = this.matrix[i][j].getCost();
					}
				}
			}
			if ((val2 == -1f) && (val1 == -1f)){
				rowDifferencies[i] = 0f;
				System.out.println("Difference ROW= "+ rowDifferencies[i]);
			}
			else {
				if (val2 == -1f){
					rowDifferencies[i] = val1;
					System.out.println("Difference ROW= "+ rowDifferencies[i]);
				}
				else{
					rowDifferencies[i]= val2 - val1;
					System.out.println("Difference ROW= "+ rowDifferencies[i]);
				}
					
			}
			
		}

		for (int i = 0; i < columnDifferencies.length; i++) {
			float val1 = -1f;
			float val2 = -1f;
			for (int j = 0; j < oferts.length; j++) {
				if (this.matrix[j][i].isValid()) {
					if (val1 >= this.matrix[j][i].getCost() || val1 == -1f) {
						val2 = val1;
						val1 = this.matrix[j][i].getCost();
					} else if (val2 >= this.matrix[j][i].getCost()
							|| val2 == -1f) {
						val2 = this.matrix[j][i].getCost();
					}
				}
			}
			
			if ((val2 == -1f) && (val1 == -1f)){
				columnDifferencies[i] = 0f;
				System.out.println("Difference COLUMN= "+ columnDifferencies[i]);
			}
			else {
				if (val2 == -1f){
					columnDifferencies[i] = val1;
					System.out.println("Difference COLUMN= "+ columnDifferencies[i]);
				}
				else{
					columnDifferencies[i]= val2 - val1;
					System.out.println("Difference COLUMN= "+ columnDifferencies[i]);
				}
					
			}
		}
	}

	//Carga las ofertas y las demandas desde las fabricas y destinos a los arreglos correspondiente
	//Anda bien!
	private void setOfertsAndDemands() {

		for (int i = 0; i < oferts.length; i++) {
			oferts[i] = this.factories.get(i).getUnidades();
		}

		for (int i = 0; i < demands.length; i++) {
			demands[i] = this.destinations.get(i).getUnidades();
			
		}
	}

	public double getCost(int i, int j) {
		return matrix[i][j].getCost();
	}
	
	//Anda bien
	private void buildMatrix() {
		matrix = new TMCell[factories.size()][destinations.size()];
		for (int i = 0; i < factories.size(); i++) {
			for (int j = 0; j < destinations.size(); j++) {
				float costo;
				
				if (factories.get(i).getNombre() == "FalseFactory") {
					costo = 0f;
				}else
					if (destinations.get(j).getNombre() == "FalseDestination") {
						costo = 0f;
					}else {
						costo = costDAO.findCost(factories.get(i).getId(),
								destinations.get(j).getId()).getCosto();
					}
				TMCell newCell = new TMCell();
				newCell.setCost(costo);
				matrix[i][j] = newCell;
			}
		}
	}

	public int[] getMaxDifference() {

		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		Float maxDifR = new Float(-1);
		Float maxDifC = new Float(-1);

		for (int i = 0; i < this.rowDifferencies.length; i++) {
			if (rowDifferencies[i] >= maxDifR){
					maxDifR = rowDifferencies[i];
					result[0] = i;
			}
		}

		for (int i = 0; i < this.columnDifferencies.length; i++) {
			if (columnDifferencies[i] >= maxDifC){
				maxDifC = columnDifferencies[i];
				result[1] = i;
			}
		}

		if (result[0] > result[1]) {
			result[1] = -1;
		} else {
			result[0] = -1;
		}
		return result;
	}

	public int getMinCostColumn(int column) {

		int result = 0;
		float aux = -1f;
		for (int i = 0; i < this.matrix.length; i++) {
			if (this.matrix[i][column].isValid()) {
				if (this.matrix[i][column].getCost() <= aux || aux == -1f) {
					result = i;
					aux = this.matrix[i][column].getCost();
				}
			}
		}
		return result;
	}

	public int getMinCostRow(int row) {

		int result = 0;
		float aux = -1f;
		for (int i = 0; i < this.columnDifferencies.length; i++) {
			if (this.matrix[row][i].isValid()) {
				if (this.matrix[row][i].getCost() <= aux || aux == -1f) {
					result = i;
					aux = this.matrix[row][i].getCost();
				}
			}
		}
		return result;
	}

	public void assign(int row, int column) {

		if (this.oferts[row] >= this.demands[column]) {
			this.matrix[row][column].setAssign(this.demands[column]);
			this.oferts[row] -= this.demands[column];
			this.demands[column] = 0;
			this.invalidColumn(column);
		} else {
			this.matrix[row][column].setAssign(this.oferts[row]);
			this.demands[column] -= this.oferts[row];
			this.oferts[row] = 0;
			this.invalidRow(row);
		}
	}

	public void invalidColumn(int column) {
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i][column].setValid(false);
		}

	}

	public void invalidRow(int row) {
		for (int i = 0; i < this.matrix[0].length; i++) {
			this.matrix[row][i].setValid(false);
		}
	}

	public int getDemand(int pos) {

		return this.demands[pos];
	}

	public int getOfert(int pos) {

		return this.oferts[pos];
	}

	public int getRows() {
		return this.matrix.length;
	}

	public int getColumns() {
		return this.matrix[0].length;
	}

	public List<MatrixElem> getBasicVars() {
		List<MatrixElem> result = new ArrayList<MatrixElem>();
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getColumns(); j++) {
				if (matrix[i][j].getAssign() != 0)
					result.add(new MatrixElem(matrix[i][j], new Integer(i),
							new Integer(j)));
			}
		}
		return result;
	}

	public TMCell getCell(int row, int col) {
		// TODO Auto-generated method stub
		return matrix[row][col];
	}

	public int getNextBasicByCol(int auxRow, int auxCol, int lVRow, int lVCol,
			int firstRow, int firstCol) {

		for (int i = auxRow - 1; i >= 0; i = i - 1) {
			if (matrix[i][auxCol].getAssign() != 0) {
				if (i != lVRow && auxCol != lVCol)
					return i;
				else
					break;
			}

			else {
				if (i == firstRow && auxCol == firstCol)
					return i;
			}

		}

		for (int i = auxRow + 1; i < this.getRows(); i++) {
			if (matrix[i][auxCol].getAssign() != 0) {
				if (i != lVRow && auxCol != lVCol)
					return i;
				else
					break;
			}

			else {
				if (i == firstRow && auxCol == firstCol)
					return i;
			}
		}

		return auxRow;
	}

	public int getNextBasicByRow(int auxRow, int auxCol, int lVRow, int lVCol,
			int fRow, int fCol) {

		for (int i = auxCol - 1; i >= 0; i = i - 1) {
			if (matrix[auxRow][i].getAssign() != 0) {
				if (auxRow != lVRow && i != lVCol)
					return i;
				else
					break;
			}

			else {
				if (auxRow == fRow && i == fCol)
					return i;
			}

		}

		for (int i = auxCol + 1; i < this.getRows(); i++) {
			if (matrix[auxRow][i].getAssign() != 0) {
				if (auxRow != lVRow && i != lVCol)
					return i;
				else
					break;
			}

			else {
				if (auxRow == fRow && i == fCol)
					return i;
			}
		}

		return auxRow;
	}

	public void print() {
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j].getAssign());
			}
			System.out.println("");
		}
		
	}
}

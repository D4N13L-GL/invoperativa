package algorithm;

public class MatrixElem {
	TMCell cell;
	Integer row;
	Integer column;
	
	public MatrixElem(TMCell cell, Integer row, Integer column) {
		super();
		this.cell = cell;
		this.row = row;
		this.column = column;
	}

	public TMCell getCell() {
		return cell;
	}

	public void setCell(TMCell cell) {
		this.cell = cell;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	

}

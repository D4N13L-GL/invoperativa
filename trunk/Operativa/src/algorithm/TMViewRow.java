package algorithm;

import java.util.List;

import operativa.bean.entity.Ubicacion;

public class TMViewRow {

	private List<TMCell> cells;
	
	private Ubicacion factory;
	
	private List<Ubicacion> path;

	public List<TMCell> getCells() {
		return cells;
	}

	public void setCells(List<TMCell> cells) {
		this.cells = cells;
	}

	public Ubicacion getFactory() {
		return factory;
	}

	public void setFactory(Ubicacion factory) {
		this.factory = factory;
	}

	public List<Ubicacion> getPath() {
		return path;
	}

	public void setPath(List<Ubicacion> path) {
		this.path = path;
	}
	
	
}

package algorithm;

import java.util.List;

import operativa.bean.entity.Ubicacion;

public class TMViewRow {

	private List<TMCell> list;
	
	private Ubicacion factory;
	
	private List<Ubicacion> path;

	public List<TMCell> getList() {
		return list;
	}

	public void setList(List<TMCell> list) {
		this.list = list;
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

package operativa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import operativa.action.utils.UbicacionRecorrido;
import operativa.bean.entity.Ubicacion;
import operativa.model.dao.UbicacionDAO;
import operativa.utils.Constantes.TipoUbicacion;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class InicioCalculosAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	private List<Ubicacion> fabricas;
	private List<Ubicacion> destinos;
	private UbicacionDAO ubicacionDao = new UbicacionDAO();
	private Map<String, UbicacionRecorrido> fabMap = new HashMap<String, UbicacionRecorrido>();
	private Map<String, UbicacionRecorrido> destMap = new HashMap<String, UbicacionRecorrido>();
	private List<Ubicacion> fabricasLista;
	private List<Ubicacion> destinosLista;
	
	@Override
	public void prepare() throws Exception {
		fabricas = ubicacionDao.getByTipoUbicacion(TipoUbicacion.FABRICA.toString());
		destinos = ubicacionDao.getByTipoUbicacion(TipoUbicacion.DESTINO.toString());
		for (Ubicacion f : fabricas) {
			fabMap.put(f.getId().toString(), new UbicacionRecorrido(f));
		}
		for (Ubicacion f : destinos) {
			destMap.put(f.getId().toString(), new UbicacionRecorrido(f));
		}
	}
	
	public String init(){
		return SUCCESS;
	}
	
	public List<UbicacionRecorrido> getListaRecorrido(List<Ubicacion> ubicaciones){
		List<UbicacionRecorrido> result = new ArrayList<UbicacionRecorrido>();
		for (Ubicacion u : ubicaciones){
			result.add(new UbicacionRecorrido(u, "false"));
		}
		return result;
	}
	
	public String nextStep(){
		fabricasLista = new ArrayList<Ubicacion>();
		destinosLista = new ArrayList<Ubicacion>();
		for (String s : fabMap.keySet()) {
			if (fabMap.get(s).getElegida().equals("true"))
				fabricasLista.add(fabMap.get(s).getUbicacion());
		}
		for (String s : destMap.keySet()) {
			if (destMap.get(s).getElegida().equals("true"))
				destinosLista.add(destMap.get(s).getUbicacion());
		}
		if (fabricasLista.size() == 0 || destinosLista.size() == 0){
			addActionError(getText("error.seleccionar.una"));
			return "error";
		}
		return "nextStep";
	}

	public List<Ubicacion> getFabricas() {
		return fabricas;
	}

	public void setFabricas(List<Ubicacion> fabricas) {
		this.fabricas = fabricas;
	}

	public List<Ubicacion> getDestinos() {
		return destinos;
	}

	public void setDestinos(List<Ubicacion> destinos) {
		this.destinos = destinos;
	}

	public Map<String, UbicacionRecorrido> getFabMap() {
		return fabMap;
	}

	public Map<String, UbicacionRecorrido> getDestMap() {
		return destMap;
	}

	public void setFabMap(Map<String, UbicacionRecorrido> fabMap) {
		this.fabMap = fabMap;
	}

	public void setDestMap(Map<String, UbicacionRecorrido> destMap) {
		this.destMap = destMap;
	}
	
	public String getFabricasListaString(){
		String result = "";
		for (Ubicacion fabrica : fabricasLista) {
			result+=fabrica.getId().toString() + ",";			
		}
		return result.substring(0, result.length()-1);
	}
	
	public String getDestinosListaString(){
		String result = "";
		for (Ubicacion destino : destinosLista) {
			result+=destino.getId().toString() + ",";			
		}
		return result.substring(0, result.length()-1);
	}
	
}

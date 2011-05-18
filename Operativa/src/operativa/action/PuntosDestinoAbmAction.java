package operativa.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import operativa.bean.entity.Costo;
import operativa.bean.entity.Ubicacion;
import operativa.model.dao.CostoDAO;
import operativa.model.dao.UbicacionDAO;
import operativa.model.dto.CostosDTO;
import operativa.utils.Constantes.TipoUbicacion;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class PuntosDestinoAbmAction extends ActionSupport implements ModelDriven<Ubicacion>, Preparable{
	

	private UbicacionDAO ubicacionDao = new UbicacionDAO();
	private CostoDAO costoDao = new CostoDAO();

	private static final long serialVersionUID = 1L;
	private List<Ubicacion> puntoDestinoList;
	private Ubicacion puntoDestino = new Ubicacion();
	
	private List<CostosDTO> costos = new ArrayList<CostosDTO>();
	private Map<String, CostosDTO> costosMap = new HashMap<String, CostosDTO>();
	private List<Ubicacion> nodoList = new ArrayList<Ubicacion>();
	
	@Override
	public void prepare() throws Exception {
		nodoList = ubicacionDao.findAll();
		for (Ubicacion destino : nodoList) {
			CostosDTO nuevo = new CostosDTO(destino);
			costos.add(nuevo);
			costosMap.put(destino.getId().toString(), nuevo);
		}
	}
	
	/**
	 * Crea o actualiza un punto de destino
	 * @return
	 */
	public String save()
	{	
		this.puntoDestino.setTipo(TipoUbicacion.DESTINO.toString());
		this.ubicacionDao.makePersistent(puntoDestino);
		// En este caso necesito guardar tanto el costo origen-destino, como destino-origen
		for (CostosDTO costo : costos) {
			Costo nuevo = new Costo();
			nuevo.setDesde(puntoDestino);
			nuevo.setHasta(costo.getDestino());
			nuevo.setCosto(costo.getCosto());
			Costo nuevo2 = new Costo();
			nuevo2.setDesde(costo.getDestino());
			nuevo2.setHasta(puntoDestino);
			nuevo2.setCosto(costo.getCosto());
			ArrayList<Costo> costoList = new ArrayList<Costo>();
			costoList.add(nuevo);
			costoList.add(nuevo2);
			costoDao.makePersistent(costoList);
		}
		ubicacionDao.commit();
		return SUCCESS;
	}
	
	/**
	 * Crea o actualiza un punto de destino
	 * @return
	 */
	public String update()
	{	
		ubicacionDao.persistUpdate(puntoDestino);
		return SUCCESS;
	}
	
	/**
	 * Lista todas los puntos de destino
	 * @return
	 */
	public String list()
	{
		puntoDestinoList = ubicacionDao.getByTipoUbicacion(TipoUbicacion.DESTINO.toString());
		nodoList = ubicacionDao.findAll();
		return SUCCESS;
	}
	
	/**
	 * Borra un punto de destino
	 * @return
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Ubicacion toDelete = new Ubicacion();
		toDelete.setId(Integer.parseInt(request.getParameter("id")));
		ubicacionDao.makeTransient(toDelete);
		return SUCCESS;
	}
	
	/**
	 * Obtiene un punto de destino segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		puntoDestino = ubicacionDao.findById(Integer.parseInt((request.getParameter("id"))),false);
		return SUCCESS;
	}

	@Override
	public Ubicacion getModel() {
		return puntoDestino;
	}

	public List<Ubicacion> getPuntoDestinoList() {
		return puntoDestinoList;
	}

	public void setPuntoDestinoList(List<Ubicacion> puntoDestinoList) {
		this.puntoDestinoList = puntoDestinoList;
	}

	public Ubicacion getPuntoDestino() {
		return puntoDestino;
	}

	public void setPuntoDestino(Ubicacion puntoDestino) {
		this.puntoDestino = puntoDestino;
	}

	public List<CostosDTO> getCostos() {
		return costos;
	}

	public void setCostos(List<CostosDTO> costos) {
		this.costos = costos;
	}

	public Map<String, CostosDTO> getCostosMap() {
		return costosMap;
	}

	public void setCostosMap(Map<String, CostosDTO> costosMap) {
		this.costosMap = costosMap;
	}

	public List<Ubicacion> getNodoList() {
		return nodoList;
	}

	public void setNodoList(List<Ubicacion> nodoList) {
		this.nodoList = nodoList;
	}
	

}

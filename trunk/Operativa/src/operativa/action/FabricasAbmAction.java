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

public class FabricasAbmAction extends ActionSupport implements ModelDriven<Ubicacion>, Preparable{
	

	private UbicacionDAO ubicacionDao = new UbicacionDAO();
	private CostoDAO costoDao = new CostoDAO();

	private static final long serialVersionUID = 1L;
	private List<Ubicacion> fabricaList;
	private Ubicacion fabrica = new Ubicacion();
	
	private List<CostosDTO> costos = new ArrayList<CostosDTO>();
	private Map<String, CostosDTO> costosMap = new HashMap<String, CostosDTO>();
	private List<Ubicacion> destinoList = new ArrayList<Ubicacion>();
	
	private boolean esEdicion = false;
	
	@Override
	public void prepare() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		esEdicion =request.getParameter("id") != null; 
		if (esEdicion){
			fabrica = ubicacionDao.findById(Integer.parseInt((request.getParameter("id"))),false);
		}
		
		destinoList = ubicacionDao.getByTipoUbicacion(TipoUbicacion.DESTINO.toString());
		for (Ubicacion destino : destinoList) {
			CostosDTO nuevo = new CostosDTO(destino);
			costos.add(nuevo);
			costosMap.put(destino.getId().toString(), nuevo);
		}
		
	}

	
	/**
	 * Crea una fabrica y calcula los costos a toads los puntos de destino
	 * insertandolos en la tabla COSTO
	 * @return
	 */
	public String save()
	{	
		fabrica.setTipo(TipoUbicacion.FABRICA.toString());
		this.ubicacionDao.makePersistent(fabrica);
		for (CostosDTO costo : costos) {
			Costo nuevo = new Costo();
			if (esEdicion){
				nuevo = costoDao.findCost(fabrica.getId(), costo.getDestino().getId());
			}
			else{
				nuevo = new Costo();
				nuevo.setDesde(fabrica);
				nuevo.setHasta(costo.getDestino());
			}
			nuevo.setCosto(costo.getCosto());
			if (esEdicion)
				costoDao.persistUpdate(nuevo);
			else 
				costoDao.makePersistent(nuevo);
		}
		ubicacionDao.commit();
		return SUCCESS;
	}

	/**
	 * Lista todas las fabricas
	 * @return
	 */
	public String list()
	{
		fabricaList = ubicacionDao.getByTipoUbicacion(TipoUbicacion.FABRICA.toString());
		destinoList = ubicacionDao.getByTipoUbicacion(TipoUbicacion.DESTINO.toString());
		return SUCCESS;
	}
	
	/**
	 * Borra una fabrica
	 * @return
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Ubicacion toDelete = ubicacionDao.findById(Integer.parseInt(request.getParameter("id")), false);
		List<Costo> costosABorrar = costoDao.findAllContaining(toDelete.getId());
		costoDao.makeTransient(costosABorrar);
		ubicacionDao.makeTransient(toDelete);
		ubicacionDao.commit();
		return SUCCESS;
	}
	
	/**
	 * Obtiene una unica fabrica segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		fabrica = ubicacionDao.findById(Integer.parseInt((request.getParameter("id"))),false);
		return SUCCESS;
	}
	
	public Ubicacion getFabrica() {
		return fabrica;
	}

	public void setFabrica(Ubicacion fabrica) {
		this.fabrica = fabrica;
	}

	public List<Ubicacion> getFabricaList() {
		return fabricaList;
	}

	public void setFabricaList(List<Ubicacion> fabricaList) {
		this.fabricaList = fabricaList;
	}

	@Override
	public Ubicacion  getModel() {
		return fabrica;
	}

	public List<Ubicacion> getDestinoList() {
		return destinoList;
	}

	public void setDestinoList(List<Ubicacion> destinoList) {
		this.destinoList = destinoList;
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

	

}
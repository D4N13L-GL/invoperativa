package operativa.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operativa.bean.entity.Costo;
import operativa.bean.entity.Factory;
import operativa.bean.entity.PuntoDestino;
import operativa.model.dao.CostoDAO;
import operativa.model.dao.DeliveryPointDAO;
import operativa.model.dao.FactoryDAO;
import operativa.utils.DistanceUtils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FabricasAbmAction extends ActionSupport implements ModelDriven<Factory>{
	

	private FactoryDAO fabricaDao = new FactoryDAO();
	private DeliveryPointDAO puntoDesinoDao = new DeliveryPointDAO();
	private CostoDAO costoDao = new CostoDAO();

	private static final long serialVersionUID = 1L;
	private List<Factory> fabricaList;
	private Factory fabrica = new Factory();
	
	/**
	 * Crea una fabrica y calcula los costos a todos los puntos de destino
	 * insertandolos en la tabla COSTO
	 * @return
	 */
	public String save()
	{	
		this.fabricaDao.makePersistent(fabrica);
		calcularCostos();
		fabricaDao.commit();
		return SUCCESS;
	}
	
	/**
	 * Calcula los costos (distancias) entre la fabrica y todos los puntos de destino
	 * para crear registros nuevos en la tabla COSTO
	 */
	private void calcularCostos() {
		List<PuntoDestino> destinos = puntoDesinoDao.findAll();
		for (PuntoDestino puntoDestino : destinos) {
			Float costo = DistanceUtils.getInstance().getDistance(fabrica.getLatitud(), fabrica.getLongitud(), 
										puntoDestino.getLatitud(), puntoDestino.getLongitud());
			Costo nuevo = new Costo();
			nuevo.setFabrica(fabrica.getId());
			nuevo.setDestino(puntoDestino.getId());
			nuevo.setCosto(costo);
			costoDao.makePersistent(nuevo);
		}
	}

	/**
	 * Actualiza una fabrica
	 * @return
	 */
	public String update()
	{	
		fabricaDao.persistUpdate(fabrica);
		return SUCCESS;
	}
	
	/**
	 * Lista todas las fabricas
	 * @return
	 */
	public String list()
	{
		fabricaList = fabricaDao.findAll();
		return SUCCESS;
	}
	
	/**
	 * Borra una fabrica
	 * @return
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Factory toDelete = new Factory();
		toDelete.setId(Integer.parseInt(request.getParameter("id")));
		fabricaDao.makeTransient(toDelete);
		return SUCCESS;
	}
	
	/**
	 * Obtiene una unica fabrica segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		fabrica = fabricaDao.findById(Integer.parseInt((request.getParameter("id"))),false);
		return SUCCESS;
	}
	
	public Factory getFabrica() {
		return fabrica;
	}

	public void setFabrica(Factory fabrica) {
		this.fabrica = fabrica;
	}

	public List<Factory> getFabricaList() {
		return fabricaList;
	}

	public void setFabricaList(List<Factory > fabricaList) {
		this.fabricaList = fabricaList;
	}

	@Override
	public Factory  getModel() {
		return fabrica;
	}
}

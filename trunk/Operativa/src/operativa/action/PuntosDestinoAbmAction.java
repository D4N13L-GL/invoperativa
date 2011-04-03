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

public class PuntosDestinoAbmAction extends ActionSupport implements ModelDriven<PuntoDestino>{
	

	private FactoryDAO fabricaDao = new FactoryDAO();
	private DeliveryPointDAO deliveryPointDao = new DeliveryPointDAO();
	private CostoDAO costoDao = new CostoDAO();

	private static final long serialVersionUID = 1L;
	private List<PuntoDestino> puntoDestinoList;
	private PuntoDestino puntoDestino = new PuntoDestino();
	
	/**
	 * Crea o actualiza un punto de destino
	 * @return
	 */
	public String save()
	{	
		this.deliveryPointDao.makePersistent(puntoDestino);
		calcularCostos();
		deliveryPointDao.commit();
		return SUCCESS;
	}
	
	/**
	 * Calcula los costos (distancias) entre la fabrica y todos los puntos de destino
	 * para crear registros nuevos en la tabla COSTO
	 */
	private void calcularCostos() {
		List<Factory> fabricas = fabricaDao.findAll();
		for (Factory fabrica : fabricas) {
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
	 * Crea o actualiza un punto de destino
	 * @return
	 */
	public String update()
	{	
		deliveryPointDao.persistUpdate(puntoDestino);
		return SUCCESS;
	}
	
	/**
	 * Lista todas los puntos de destino
	 * @return
	 */
	public String list()
	{
		puntoDestinoList = deliveryPointDao.findAll();
		return SUCCESS;
	}
	
	/**
	 * Borra un punto de destino
	 * @return
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		PuntoDestino toDelete = new PuntoDestino();
		toDelete.setId(Integer.parseInt(request.getParameter("id")));
		deliveryPointDao.makeTransient(toDelete);
		return SUCCESS;
	}
	
	/**
	 * Obtiene un punto de destino segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		puntoDestino = deliveryPointDao.findById(Integer.parseInt((request.getParameter("id"))),false);
		return SUCCESS;
	}
	
	public PuntoDestino getPuntoDestino() {
		return puntoDestino ;
	}

	public void setPuntoDestino(PuntoDestino puntoDestino ) {
		this.puntoDestino  = puntoDestino ;
	}

	public List<PuntoDestino> getPuntoDestinoList() {
		return puntoDestinoList;
	}

	public void setPuntoDestinoList(List<PuntoDestino > puntoDestinoList ) {
		this.puntoDestinoList = puntoDestinoList ;
	}

	@Override
	public PuntoDestino  getModel() {
		return puntoDestino;
	}
}

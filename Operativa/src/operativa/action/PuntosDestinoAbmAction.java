package operativa.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import operativa.bean.entity.PuntoDestino;
import operativa.model.dao.DeliveryPointDAO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PuntosDestinoAbmAction extends ActionSupport implements ModelDriven<PuntoDestino>{
	

	private DeliveryPointDAO deliveryPointDao = new DeliveryPointDAO();

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
		return SUCCESS;
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

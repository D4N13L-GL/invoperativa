package operativa.action;


import operativa.bean.entity.Factory;
import operativa.model.dao.FactoryDAO;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FabricasAbmAction extends ActionSupport implements ModelDriven<Factory>{
	

	private FactoryDAO fabricaDao = new FactoryDAO();

	private static final long serialVersionUID = 1L;
	private List<Factory> fabricaList;
	private Factory fabrica = new Factory();
	
	/**
	 * Crea o actualiza una fabrica
	 * @return
	 */
	public String save()
	{	
		this.fabricaDao.makePersistent(fabrica);
		return SUCCESS;
	}
	
	/**
	 * Crea o actualiza una fabrica
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

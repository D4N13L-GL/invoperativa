package operativa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import operativa.bean.entity.Fabrica;
import operativa.model.dao.FabricaDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FabricasAbmAction extends ActionSupport implements ModelDriven<Fabrica>{
	
	private static final long serialVersionUID = 1L;

	private FabricaDAO fabricaDAO = new FabricaDAO();
	private List<Fabrica> fabricaList;
	private Fabrica fabrica = new Fabrica();
	
	/**
	 * Crea o actualiza una fabrica
	 * @return
	 */
	public String save()
	{	
		fabricaDAO.save(fabrica);
		return SUCCESS;
	}
	
	/**
	 * Crea o actualiza una fabrica
	 * @return
	 */
	public String update()
	{	
		fabricaDAO.update(fabrica);
		return SUCCESS;
	}
	
	/**
	 * Lista todas las fabricas
	 * @return
	 */
	public String list()
	{
		fabricaList = fabricaDAO.list();
		return SUCCESS;
	}
	
	/**
	 * Borra una fabrica
	 * @return
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		fabricaDAO.delete(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * Obtiene una unica fabrica segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		fabrica = fabricaDAO.listById(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	public Fabrica getFabrica() {
		return fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public List<Fabrica> getFabricaList() {
		return fabricaList;
	}

	public void setFabricaList(List<Fabrica> fabricaList) {
		this.fabricaList = fabricaList;
	}

	@Override
	public Fabrica getModel() {
		return fabrica;
	}
	
}

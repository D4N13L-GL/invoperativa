package operativa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import operativa.bean.entity.Usuario;
import operativa.model.dao.UserDAO;

public class UsuariosAbmAction extends ActionSupport implements ModelDriven<Usuario>{

	
	private UserDAO userDAO = new UserDAO();
	private List<Usuario> usersList;
	private Usuario user = new Usuario();
	
	/**
	 * Crea o actualiza una Usuario
	 * @return
	 */
	public String save()
	{	
		userDAO.makePersistent(this.getUser());
		return SUCCESS;
	}
	
	/**
	 * Crea o actualiza un usuario
	 * @return
	 */
	public String update()
	{	
		userDAO.persistUpdate(this.getUser());
		return SUCCESS;
	}
	
	/**
	 * Lista todas los usuarios
	 * @return
	 */
	public String list()
	{
		this.usersList = this.userDAO.findAll();
		return SUCCESS;
	}
	
	/**
	 * Borra un usuario
	 * @return
	 */
	public String delete()
	{
		this.userDAO.makeTransient(this.getUser().getId());
		return SUCCESS;
	}
	
	/**
	 * Obtiene un unico usuario segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		user = userDAO.findById((Integer.parseInt(request.getParameter("id"))), false);
		return SUCCESS;
	}
	
	public List<Usuario> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Usuario> usersList) {
		this.usersList = usersList;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	@Override
	public Usuario getModel() {
		return getUser();
	}
	
	
}

package operativa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import operativa.bean.entity.Fabrica;
import operativa.bean.entity.Usuario;
import operativa.model.dao.FabricaDAO;
import operativa.model.dao.UsuarioDAO;

public class UsuariosAbmAction extends ActionSupport implements ModelDriven<Usuario>{
	
	private UsuarioDAO usuariosDao = new UsuarioDAO();
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String crearUsuario(){
		try{
			Usuario u = new Usuario();
			u.setUsername(username);
			u.setPassword(password);
			usuariosDao.crearUsuario(u);
			return "exito";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	//Cambios que hice...
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> usersList;
	private Usuario user = new Usuario();
	
	/**
	 * Crea o actualiza una fabrica
	 * @return
	 */
	public String save()
	{	
		usuarioDAO.crearUsuario(user);
		return SUCCESS;
	}
	
	/**
	 * Crea o actualiza una fabrica
	 * @return
	 */
	public String update()
	{	
		usuarioDAO.update(user);
		return SUCCESS;
	}
	
	/**
	 * Lista todas las fabricas
	 * @return
	 */
	public String list()
	{
		usersList = usuarioDAO.list();
		return SUCCESS;
	}
	
	/**
	 * Borra una fabrica
	 * @return
	 */
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		usuarioDAO.delete(Integer.parseInt(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * Obtiene una unica fabrica segun un ID
	 * @return
	 */
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		user = usuarioDAO.listById(Integer.parseInt(request.getParameter("id")));
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

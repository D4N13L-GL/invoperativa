package operativa.action;

import operativa.bean.entity.Usuario;
import operativa.model.dao.UsuarioDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public String execute() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario u = dao.getUser(username, password);
			if (u != null) {
				return "success";
			} else {
				addActionError(getText("error.login"));
				return "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
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
	
	public String register()
	{
		return "register";
	}
}

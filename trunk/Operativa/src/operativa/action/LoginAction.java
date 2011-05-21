package operativa.action;

import java.util.Map;

import operativa.bean.entity.Usuario;
import operativa.model.dao.UserDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	@SuppressWarnings("unchecked")
	public String execute() {

		try {
			UserDAO dao = new UserDAO();
			
			Usuario u = dao.findUser(username, password);
			if (u != null) {
				Map<String, String> session = ActionContext.getContext().getSession();
			    session.put("logged-in","true");
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

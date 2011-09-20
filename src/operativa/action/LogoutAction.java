package operativa.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception { 
	    Map<String, String> session = ActionContext.getContext().getSession();
	    session.remove("logged-in"); 
	    return SUCCESS;
	}

}

package operativa.action;

import com.opensymphony.xwork2.ActionSupport;

public class WelcomeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	public String initAbmFabricas(){
		return "abmFabricas";
	}
	
	public String initAbmUsuarios(){
		return "abmUsuarios";
	}
	
	public String initAbmPuntosEntrega(){
		return "abmPuntosEntrega";
	}
	
	public String goHome(){
		return SUCCESS;
	}
	
}

package operativa.action;

import operativa.bean.entity.Usuario;
import operativa.model.dao.UsuarioDAO;

public class UsuariosAbmAction {
	
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
	
	
}

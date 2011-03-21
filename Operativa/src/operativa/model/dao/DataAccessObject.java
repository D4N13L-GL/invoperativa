package operativa.model.dao;

public interface DataAccessObject {
	
	public void create(Object[] param);
	
	public void remove(Object[] param);
	
	public Object getById(int id);
	
	public Object getByAttribute(String attr, Object value);

}

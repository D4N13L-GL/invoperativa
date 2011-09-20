package operativa.model.dao;

import java.util.List;

import operativa.bean.entity.Ubicacion;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UbicacionDAO extends GenericHibernateDAO<Ubicacion, Integer> {

	@Override
	public void makeTransient(Integer id) {
		@SuppressWarnings("unused")
		Transaction tx =  super.session.beginTransaction();
		Ubicacion oldUbicacion = (Ubicacion) super.session.get(Ubicacion.class, id);
		super.session.delete(oldUbicacion);		
	}
	
	public List<Ubicacion> getByTipoUbicacion(String tipoUbicacion){
		return this.findByCriteria(Restrictions.eq("tipo", tipoUbicacion));
	}
	
}

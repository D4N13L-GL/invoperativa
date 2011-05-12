package operativa.model.dao;

import java.util.List;

import operativa.bean.entity.Costo;

import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class CostoDAO extends GenericHibernateDAO<Costo, Integer> {

	@Override
	public void makeTransient(Integer id) {
		@SuppressWarnings("unused")
		Transaction tx =  super.session.beginTransaction();
		Costo oldCosto = (Costo) super.session.get(Costo.class, id);
		super.session.delete(oldCosto);
	}

	public Costo findCost(Integer ori_id, Integer dest_id) {
		List<Costo> result = this.findByCriteria(Restrictions.eq("fabrica.id", ori_id), 
				Restrictions.eq("punto_destino.id", dest_id));		
		return (result.size() > 0 ? result.get(0) : null ); 
	}

}

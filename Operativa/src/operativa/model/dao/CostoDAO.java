package operativa.model.dao;

import operativa.bean.entity.Costo;

import org.hibernate.Transaction;

public class CostoDAO extends GenericHibernateDAO<Costo, Integer> {

	@Override
	public void makeTransient(Integer id) {
		@SuppressWarnings("unused")
		Transaction tx =  super.session.beginTransaction();
		Costo oldCosto = (Costo) super.session.get(Costo.class, id);
		super.session.delete(oldCosto);
	}

}

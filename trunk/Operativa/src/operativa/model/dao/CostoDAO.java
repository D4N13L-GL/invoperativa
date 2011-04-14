package operativa.model.dao;

import java.util.List;

import operativa.bean.entity.Costo;
import operativa.bean.entity.Factory;
import operativa.bean.entity.PuntoDestino;

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

	public Costo findCost(Factory factory, PuntoDestino puntoDestino) {
		List<Costo> result = this.findByCriteria(Restrictions.eq("fabrica_id", factory.getId()), 
				Restrictions.eq("punto_destino_id", puntoDestino.getId()));		
		return (result.size() > 0 ? result.get(0) : null ); 
	}

}

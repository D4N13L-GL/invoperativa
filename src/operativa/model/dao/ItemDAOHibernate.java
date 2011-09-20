package operativa.model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.cache.ReadWriteCache.Item;


public class ItemDAOHibernate extends GenericHibernateDAO<Item, Long> implements
		ItemDAO {

	@Override
	public Long getMaxBid(Long itemId) {
		Query q = getSession().getNamedQuery(ItemDAO.QUERY_MAXBID);
        q.setParameter("itemid", itemId);
        return (Long) q.uniqueResult();
	}

	@Override
	public Long getMinBid(Long itemId) {
		Query q = getSession().getNamedQuery(ItemDAO.QUERY_MINBID);
        q.setParameter("itemid", itemId);
        return (Long) q.uniqueResult();
	}

	@Override
	public void makeTransient(Long id) {
		// TODO Auto-generated method stub
		
	}
}

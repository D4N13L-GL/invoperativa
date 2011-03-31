package operativa.model.dao;

import org.hibernate.cache.ReadWriteCache.Item;

public interface ItemDAO extends GenericDAO<Item, Long> {
	 
    public static final String QUERY_MAXBID = "ItemDAO.QUERY_MAXBID";
    public static final String QUERY_MINBID = "ItemDAO.QUERY_MINBID";
 
    public Long getMaxBid(Long itemId);
    public Long getMinBid(Long itemId);
 
}

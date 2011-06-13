package operativa.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import operativa.utils.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

public abstract class GenericHibernateDAO<T, ID extends Serializable>
implements GenericDAO<T, ID> {

	private Class<T> persistentClass;
	protected Session session;

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0];
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public void setSession(Session s) {
		this.session = s;
	}

	protected Session getSession() {
		if (session == null)
			throw new IllegalStateException("Session has not been set on DAO before usage");
		return session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public T findById(ID id, boolean lock) {	
		this.session.beginTransaction();
		T entity;
		if (lock)
			entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (T) getSession().load(getPersistentClass(), id);	
		return entity;
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		this.session.beginTransaction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example =  Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	public T makePersistent(T entity) {
		Transaction tx =  this.session.getTransaction();
		tx.begin();
		getSession().save(entity);
		flush();
		return entity;
	}
	
	public void makePersistent(List<T> entitys){
		Transaction tx =  this.session.getTransaction();
		tx.begin();
		for (T t : entitys) {
			getSession().save(t);
		}
		flush();
	}
	
	public T persistUpdate(T entity){
		this.session.beginTransaction();
		getSession().update(entity);
		flush();
		return entity;
	}
	
	public void persistUpdate(List<T> entitys){
		Transaction tx =  this.session.getTransaction();
		tx.begin();
		for (T t : entitys) {
			getSession().update(t);
		}
		flush();
	}

	public void makeTransient(T entity) {
		Transaction tx =  this.session.getTransaction();
		tx.begin();
		getSession().delete(entity);
		flush();
	}
	
	public void makeTransient(List<T> entity) {
		Transaction tx =  this.session.getTransaction();
		tx.begin();
		for (T t : entity) {
			getSession().delete(t);	
		}
		flush();
	}
	
	public abstract void makeTransient(ID id);

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	/**
	 * Metodo para ser sobreescrito por las subclases. Devuelve
	 * los objetos que cumplen con cierto criterio.
	 * 
	 * @param criterion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		this.session.beginTransaction();
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
	
	public void commit(){
		session.getTransaction().commit();
	}
}

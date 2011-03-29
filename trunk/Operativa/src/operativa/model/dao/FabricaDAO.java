package operativa.model.dao;

import java.util.List;

import operativa.bean.entity.Fabrica;
import operativa.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FabricaDAO{
	
	//TODO: Esto se puede mejorar... Habría que hacer un diseño antes de mandarse a codear!

	private Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public void crearFabrica(String localizacion, String nombre, String produccion,
								String latitud, String longitud) throws RuntimeException{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		Fabrica nueva = new Fabrica();
		
		nueva.setLocalizacion(localizacion);
		nueva.setNombre(nombre);
		nueva.setProduccion(Integer.parseInt(produccion));
		nueva.setLatitud(Float.parseFloat(latitud));
		nueva.setLongitud(Float.parseFloat(longitud));
		
		session.save(nueva);
		
		tx.commit();
					
	}

	public void delete(Integer id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Fabrica fabrica = (Fabrica) session.get(Fabrica.class, id);
			session.delete(fabrica);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
	}

	@SuppressWarnings("unchecked")
	public List<Fabrica> list() {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		List<Fabrica> fabricas = null;
		try {
			fabricas = (List<Fabrica>) session.createQuery("from Fabrica").list();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return fabricas;
	}

	public Fabrica listById(Integer id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Fabrica fabrica = null;
		try {
			fabrica = (Fabrica) session.get(Fabrica.class, id);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return fabrica;
	}

	public void save(Fabrica fab) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(fab);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
	}
	
	public void update(Fabrica fab){
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.merge(fab);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

}

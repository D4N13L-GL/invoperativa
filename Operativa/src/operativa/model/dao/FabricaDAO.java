package operativa.model.dao;

import operativa.bean.entity.Fabrica;
import operativa.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FabricaDAO {
	
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

}

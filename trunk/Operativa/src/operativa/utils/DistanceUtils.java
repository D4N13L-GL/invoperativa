package operativa.utils;

public class DistanceUtils {
	
	private static DistanceUtils INSTANCE;
	
	/**
	 * Obtiene la distancia entre dos puntos dados por su latitud/longitud
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * 
	 * @return
	 */
	public double getDistance(double lat1, double lon1, double lat2, double lon2) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  dist = dist * 1.609344;
		  
		  return (dist);
		}

	/**
	 * Convierte grados decimales en radianes
	 * @param deg
	 * @return
	 */
	private double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
	}

	/**
	 * Convierte radianes a grados decimales
	 * @param rad
	 * @return
	 */
	private double rad2deg(double rad) {
	  return (rad * 180.0 / Math.PI);
	}
	
	/**
	 * Retorna la instancia Singleton
	 * @return
	 */
	public static DistanceUtils getInstance(){
		if (INSTANCE == null)
			INSTANCE = new DistanceUtils();
		return INSTANCE;
	}

}

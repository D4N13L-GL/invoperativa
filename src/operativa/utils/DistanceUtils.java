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
	public float getDistance(float lat1, float lon1, float lat2, float lon2) {
		  float theta = lon1 - lon2;
		  float dist = (float) (Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta)));
		  dist = (float) Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = (float) (dist * 60 * 1.1515);
		  dist = (float) (dist * 1.609344);
		  
		  return (dist);
		}

	/**
	 * Convierte grados decimales en radianes
	 * @param deg
	 * @return
	 */
	private float deg2rad(float deg) {
		  return (float) (deg * Math.PI / 180.0);
	}

	/**
	 * Convierte radianes a grados decimales
	 * @param rad
	 * @return
	 */
	private float rad2deg(float rad) {
	  return (float) (rad * 180.0 / Math.PI);
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

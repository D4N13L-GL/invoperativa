package algorithm.traveler;

import operativa.bean.entity.Costo;
import operativa.model.dao.CostoDAO;

import org.jgap.Chromosome;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.salesman.Salesman;

public class TravelerGeneticSolver extends Salesman {

	private int cities = 7;
	private CostoDAO costoDao;

	  
	public IChromosome createSampleChromosome(Object a_initial_data) {
	    try {
	      Integer[] cityIds = (Integer[]) a_initial_data;
	      cities = cityIds.length;
	      Gene[] genes = new Gene[cities];
	      for (int i = 0; i < genes.length; i++) {
	        genes[i] = new IntegerGene(getConfiguration());
	        genes[i].setAllele(new Integer(cityIds[i]));
	      }
	      IChromosome sample = new Chromosome(getConfiguration(), genes);
	      return sample;
	    }
	    catch (InvalidConfigurationException iex) {
	      throw new IllegalStateException(iex.getMessage());
	    }
	}

	  
    public double distance(Gene a_from, Gene a_to) {
	    Costo c = costoDao.findCost((Integer)a_from.getAllele(), (Integer) a_to.getAllele());
	    return c.getCosto();
    	
	}

	  
	  public static void main(String[] args) {
	    try {
	      TravelerGeneticSolver t = new TravelerGeneticSolver();
	      IChromosome optimal = t.findOptimalPath(new Integer[]{16,7,8,9,10,11,12});
	      System.out.println("Solution: ");
	      System.out.println(optimal);
	      System.out.println("Score " +
	                         (Integer.MAX_VALUE / 2 - optimal.getFitnessValue()));
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	  }


	public TravelerGeneticSolver() {
		super();
		costoDao = new CostoDAO();
	}

}

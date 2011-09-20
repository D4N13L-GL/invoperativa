package algorithm;

public class TMCell {

	private boolean valid = true;
	
	private int assign = 0;
	
	private float cost;	
	

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getAssign() {
		return assign;
	}

	public void setAssign(int assign) {
		this.assign = assign;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}	
}

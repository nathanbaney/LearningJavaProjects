package islandmapper;

public class Land {
	
	protected boolean isCounted = false; //has been added to island already or not
	private int landType;
	private int gridX, gridY; //values of coords
	
	public Land(int x, int y, int type) { //x, y are grid values in 2d array (the map), type is landType
		gridX = x;
		gridY = y;
		landType = type;
	}
	public int getXValue() {
		return gridX;
	}
	public int getYValue() {
		return gridY;
	}
	public int getLandType() {
		return landType;
	}
	public boolean getCountability() {
		return isCounted;
	}
	public void count() {
		isCounted = true;
	}
	
	public String toString() {
		String landTypeString = "";
		String landString = "";
		switch (landType) {
		case 0: 
			landTypeString = "Water";
			break;
		case 1:
			landTypeString = "Land";
			break;
		default:
			landTypeString = "ERROR";
		}
		landString += landTypeString + " at (" + gridX + ", " + gridY + ")";
		return landString;
	}
}

package islandmapper;

public class Island {
	protected Land[] landArray = new Land[20]; //CHANGE 20 TO GETMAPSIZE OF MAP LATER!!!!
	private int landCount = 0;
	
	public Island(Land initialLand) { //whenever the parser finds an uncounted land (initialLand), create a new Island object 
		landArray[landCount] = initialLand;
		initialLand.count();
		landCount++;
	}
	public void addLand(Land newLand) { //whenever land builder finds adjacent land, add newLand to landArray
		landArray[landCount] = newLand;
		newLand.count();
		landCount++;
	}
	public int getLandCount() {
		return landCount;
	}
}

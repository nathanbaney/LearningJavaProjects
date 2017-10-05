package islandmapper;

public class Map {
	public int mapSize = 100;
	public int sizeX, sizeY;
	public Land[][] mapArray;
	
	
	public Map(int x, int y) { //x and y are bounds of map
		sizeX = x;
		sizeY = y;
//		mapArray = new Land[sizeX][sizeY]; //creates 2d array with input bounds (IDK IF THIS IS CORRECT SYNTAX, CHECK THIS FIRST IF STUFF BREAKS
		
//		mapArray = generateDeterministicIsland();
		
		mapArray = generateRandomIsland();
	}
	public Land getLandAbove(Land landCenter) {
		return mapArray[landCenter.getXValue()][landCenter.getYValue() - 1];
	}
	public Land getLandRight(Land landCenter) {
		return mapArray[landCenter.getXValue() + 1][landCenter.getYValue()];
	}
	public Land getLandBelow(Land landCenter) {
		return mapArray[landCenter.getXValue()][landCenter.getYValue() + 1];
	}
	public Land getLandLeft (Land landCenter) {
		return mapArray[landCenter.getXValue() - 1][landCenter.getYValue()];
	}
	public Land[] getAdjacentLands(Land landCenter) {
		Land[] landAdj = new Land[4]; //all adjacent lands
		int landAdjCount = 0; //amount of adjacent lands
		
		while (landAdjCount < 4) { //while sides havent been checked...
			switch(landAdjCount) {
			case 0: 
				//check and add land above
				if (!(landCenter.getYValue() == 0)) { //if its not at the top..
					if (!mapArray[landCenter.getXValue()][landCenter.getYValue()+1].isCounted) {
						landAdj[landAdjCount] = mapArray[landCenter.getXValue()][landCenter.getYValue()+1];
						mapArray[landCenter.getXValue()][landCenter.getYValue()+1].count();
					}
				}
				landAdjCount++;
				break;
			case 1:
				//check right
				if (!(landCenter.getXValue() == sizeX - 1)) { //if its not at the far right..
					if (!mapArray[landCenter.getXValue()+1][landCenter.getYValue()].isCounted) {
						landAdj[landAdjCount] = mapArray[landCenter.getXValue()+1][landCenter.getYValue()];
						mapArray[landCenter.getXValue()+1][landCenter.getYValue()].count();
					}
				}
				landAdjCount++;
				break;
			case 2:
				//check below
				if (!(landCenter.getYValue() == sizeY - 1)) { //if its not at the bottom..
					if (!mapArray[landCenter.getXValue()][landCenter.getYValue()-1].isCounted) {
						landAdj[landAdjCount] = mapArray[landCenter.getXValue()][landCenter.getYValue()-1];
						mapArray[landCenter.getXValue()][landCenter.getYValue()-1].count();
					}
				}
				landAdjCount++;
				break;
			case 3: 
				//check left
				if (!(landCenter.getXValue() == 0)) { //if its not at the far left..
					if (!mapArray[landCenter.getXValue()-1][landCenter.getYValue()].isCounted) {
						landAdj[landAdjCount] = mapArray[landCenter.getXValue()-1][landCenter.getYValue()];
						mapArray[landCenter.getXValue()-1][landCenter.getYValue()].count();
					}
				}
				landAdjCount++;
				break;
			default:
				System.out.println("Something fucked up! Whoops!");
			}
		}
		return landAdj;
	}
	public void buildIsland(Island island) { //takes island (an array of lands), and generates the rest of the island around it
		int builderIndex = 0; //provides input for switch statement on which direction to check
		for (int index = 0; index < island.getLandCount(); index++) {//take every land in island, find adjacent lands, add to island
			while (builderIndex < 4) { //while directions havent all been checked...
				switch (builderIndex) {
				case 0: //check top
					if (island.landArray[index].getYValue() != 0 && mapArray[island.landArray[index].getXValue()][island.landArray[index].getYValue() - 1].getLandType() == 1) { //if its not at the top and land above land at index is a land...
						if (!mapArray[island.landArray[index].getXValue()][island.landArray[index].getYValue() - 1].isCounted) {//...and its been counted...
							island.addLand(mapArray[island.landArray[index].getXValue()][island.landArray[index].getYValue() - 1]);
						}
					}
					builderIndex++;
					break;
				case 1: //check right
					if (island.landArray[index].getXValue() != sizeX - 1 && mapArray[island.landArray[index].getXValue() + 1][island.landArray[index].getYValue()].getLandType() == 1) { //if its not at the far right and land right of land at index is a land...
						if (!mapArray[island.landArray[index].getXValue() + 1][island.landArray[index].getYValue()].isCounted) {//...and its been counted...
							island.addLand(mapArray[island.landArray[index].getXValue() + 1][island.landArray[index].getYValue()]);
						}
					}
					builderIndex++;
					break;
				case 2: //check below
					if (island.landArray[index].getYValue() != sizeY - 1 && mapArray[island.landArray[index].getXValue()][island.landArray[index].getYValue() + 1].getLandType() == 1) { //if its not at the bottom and land below land at index is a land...
						if (!mapArray[island.landArray[index].getXValue()][island.landArray[index].getYValue() + 1].isCounted) {//...and its been counted...
							island.addLand(mapArray[island.landArray[index].getXValue()][island.landArray[index].getYValue() + 1]);
						}
					}
					builderIndex++;
					break;
				case 3: //check left
					if (island.landArray[index].getXValue() != 0 && mapArray[island.landArray[index].getXValue() - 1][island.landArray[index].getYValue()].getLandType() == 1) { //if its not at the far left and land left of land at index is a land...
						if (!mapArray[island.landArray[index].getXValue() - 1][island.landArray[index].getYValue()].isCounted) {//...and its been counted...
							island.addLand(mapArray[island.landArray[index].getXValue() - 1][island.landArray[index].getYValue()]);
						}
					}
					builderIndex++;
					break;
				default:
					System.out.println("Something broke in the switch statement for the islandbuilder!");
				}
			}
			builderIndex = 0;
		}
	}
	
	private Land[][] generateRandomIsland() {
		Land[][] randomMap = new Land[sizeX][sizeY];
		for (int indexY = 0; indexY < sizeY; indexY++) {
			for (int indexX = 0; indexX < sizeX; indexX++) {
				randomMap[indexX][indexY] = new Land(indexX, indexY, (int) (2*Math.random()));
			}
		}
		return randomMap;
	}
	
	private Land[][] generateDeterministicIsland() {
		Land[][] map = new Land[4][4];
		
		Land[] firstColumn = new Land[4];
		firstColumn[0] = new Land(0,0,0);
		firstColumn[1] = new Land(0,1,1);
		firstColumn[2] = new Land(0,2,0);
		firstColumn[3] = new Land(0, 3, 0);
		
		map[0] = firstColumn;
		
		Land[] secondColumn = new Land[4];
		secondColumn[0] = new Land(1,0,1);
		secondColumn[1] = new Land(1,1,0);
		secondColumn[2] = new Land(1,2,0);
		secondColumn[3] = new Land(1, 3, 0);
		
		map[1] = secondColumn;
		
		Land[] thirdColumn = new Land[4];
		thirdColumn[0] = new Land(2,0,1);
		thirdColumn[1] = new Land(2,1,1);
		thirdColumn[2] = new Land(2,2,0);
		thirdColumn[3] = new Land(2, 3, 1);
		
		map[2] = thirdColumn;
		
		Land[] forthColumn = new Land[4];
		forthColumn[0] = new Land(3,0,0);
		forthColumn[1] = new Land(3,1,0);
		forthColumn[2] = new Land(3,2,1);
		forthColumn[3] = new Land(3, 3, 1);
		
		map[3] = forthColumn;
		
		return map;
	}
}

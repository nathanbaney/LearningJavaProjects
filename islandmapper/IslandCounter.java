package islandmapper;

import java.util.Scanner;

public class IslandCounter {

	public static void main(String[] args) {
		int mapX = 4; //debug vals change later
		int mapY = 4;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Input x value: ");
		mapX = scan.nextInt();
		System.out.println("Input y value: ");
		mapY = scan.nextInt();
		Map map = new Map(mapX, mapY);
		Island[] islandList = new Island[20]; //20 IS ARBITRARY CHANGE LATER 
		int islandCount = 0;
		
		try  {
			for (int indexY = 0; indexY < mapY; indexY++) {
				for (int indexX = 0; indexX < mapX; indexX++) {
					System.out.print(map.mapArray[indexX][indexY].toString() + "\t");
					if (!map.mapArray[indexX][indexY].isCounted && map.mapArray[indexX][indexY].getLandType() == 1) { //1 is land, 0 is water. if uncounted and land...
						islandList[islandCount] = new Island(map.mapArray[indexX][indexY]); //...instance an island with that land as 1st object
						map.buildIsland(islandList[islandCount]);
						islandCount++;
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\nNumber of islands: " + islandCount);
		
		
		scan.close();
	}

}

# LearningJavaProjects
random projects 


## Maven Support
I added maven support to this repo.  Dependencies can be listed in the pom.xml file.
* install maven (set env variables and make sure you can do ```mvn -v``)
* execute ```mvn test```
  * This will compile all code in the src/main/java directory
  * Then run all tests (junit) in the src/test/java directory
  * for more about junit, [read here](https://github.com/junit-team/junit4/wiki/Getting-started)

## Island Counter
* Given a 2D int array that represents a map grid, find all islands on the grid.
* Land is defined as '1' and water is '0'
* An island is all of the land pixels that are adjacent: top, bottom, right, left
* The IslandCounter.countIslands method will return the number of islands found

### Jonathan's implementation
I used a two step algorithm to find islands.  This algorithm is destructive as it mutates the grid.  For this reason, I clone the input grid.
* First, I use iteration to find a pixel of land (pixel is a grid coordinate of x,y)
* Count the new land found as an island and set the pixel to water so it doesn't get counted again
* Use recursion on the found land pixel and deterministically search right, left, top, bottom for more land.
* Source is in com.baney.jonathan.islands.IslandCounter
* Test is in com.baney.jonathan.islands.IslandCounterTest
* ```mvn test``` executed at the project root will execute the test (or run through eclipse)


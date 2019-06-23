package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		Cell c = maze.getCell(randGen.nextInt(width),randGen.nextInt(height));
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(c);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		System.out.println("1");
		//A. mark cell as visited
		currentCell.hasBeenVisited();
		//B. check for unvisited neighbors using the cell
		//currentCell.x
		ArrayList<Cell> unVisited = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(unVisited.isEmpty() == false) {
			//C1. select one at random.
			Cell chosen = unVisited.get(randGen.nextInt(unVisited.size()));
			//C2. push it to the stack
			uncheckedCells.push(chosen);
			//C3. remove the wall between the two cells
			removeWalls(currentCell,chosen);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = chosen;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		else {
			//D1. if the stack is not empty
			if(uncheckedCells.isEmpty() == false) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if((i == -1 && j == -1)||(i == -1 && j == 1)||(i == 1 && j == -1)||(i == 1 && j == 1)||(i == 0 && j == 0)) {
				}else if(c1.getX()+i == c2.getX() && c1.getY()+j == c2.getY()) {
					if(i == -1) {
						c1.setWestWall(false);
						c2.setEastWall(false);
					}else if(i == 1) {
						c1.setEastWall(false);
						c2.setWestWall(false);
					}else if(j == -1) {
						c1.setNorthWall(false);
						c2.setSouthWall(false);
					}else if(j == 1) {
						c1.setSouthWall(false);
						c2.setNorthWall(false);
					}
					i = 2;
					j = 2;
				}
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> alc = new ArrayList<Cell>();
		 for(int i = -1; i <= 1; i++) {
			 for(int j = -1; j <= 1; j++) {
				 if(c.getX()+i >= maze.getWidth() || c.getX()+i < 0 || c.getY()+j >= maze.getHeight() || c.getY()+j < 0) {
				 }else if(maze.getCell(c.getX()+i, c.getY()+j).hasBeenVisited() == true) {
					alc.add(maze.getCell(c.getX()+i, c.getY()+j));
			     }
			 }
		 } 
		return alc;
	}
}

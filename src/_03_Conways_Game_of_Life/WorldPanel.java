package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.

	Cell[][] cells;
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize = w/cpr;
		//3. Initialize the cell array to the appropriate size.
		cells = new Cell[cpr][cpr];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				cells[i][j] = new Cell(cellSize*i,cellSize*j,cellSize);
			}
		}
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive member to true of false
		Random r = new Random();
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				int l = r.nextInt(2);
				if(l == 0) {
				    cells[i][j].isAlive = false;
				}else if(l == 1) {
					cells[i][j].isAlive = true;
				}
			}
		}
		repaint();
	}
	
	public void clearCells() {
		//5. Iterate through the cells and set them all to dead.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				cells[i][j].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				cells[i][j].draw(g);
			}
		}
		
		
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for(int i = 0; i < livingNeighbors.length; i++) {
			for(int j = 0; j < livingNeighbors.length; j++) {
				livingNeighbors[i][j] = getLivingNeighbors(cells[i][j].getX(),cells[i][j].getY());
			}
		}
		//8. check if each cell should live or die
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				cells[i][j].liveOrDie(livingNeighbors[i][j]);
			}
		}
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an int of 8 or less based on how many
	//   living neighbors there are of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int living = 0;
		int cellX = x/cellSize;
		int cellY = y/cellSize;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0) {
				}else if(cellX+i < 0 || cellX+i > cells.length-1 || cellY+j < 0 || cellY+j > cells.length-1) {
				}else if(cells[cellX+i][cellY+j].isAlive == true) {
					living++;
				}
			}
		}
		return living;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells.length; j++) {
				if(e.getX() > cells[i][j].getX() && e.getX() < cells[i][j].getX()+cellSize) {
					if(e.getY() > cells[i][j].getY() && e.getY() < cells[i][j].getY()+cellSize) {
						if(cells[i][j].isAlive == true) {
							cells[i][j].isAlive = false;
						}else {
							cells[i][j].isAlive = true;
						}
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}

package _01_Crazy_Digital_Painting;

import java.awt.Color;
import java.util.Random;

public class CrazyDigitalPainting {
	//1. Create two final static integers for the width and height of the display.
	final static int width = 800;
	final static int height = 800;
	
	//2. Create a 2D array of Color objects. You will need to import
	//java.awt.Color. Initialize the size of the array using the 
	//integers created in step 1.
	Color[][] colors = new Color[width][height];
	
	
	public CrazyDigitalPainting() {
		//3. Open the crazy_digital_painting.png file and look at the image.
		Random r = new Random();
		int l = 0;
		int k = 0;
		int h = 0;
		int jj = 0;
		//4. Iterate through the 2D array and initialize each Color object
		//   to a new color. The sample image was created using the following 
		//   pattern:
		//   colors[i][j] = new Color(i % 256, (i * j) % 256, j % 256);
		for(int i = 0; i < colors.length; i++) {
			for(int j = 0; j < colors.length; j++) {
				l = r.nextInt(2);
				h = r.nextInt(2);
				if(l == 0) {
					l = i;
				}else if(l == 1) {
					l = j;
				}if(h == 0) {
					h = i;
				}else if(h == 1) {
					h = j;
				}
				colors[i][j] = new Color(h % 50, l % 255, k % 100);
				jj = j;
			}
			k = r.nextInt(2);
			if(k == 0) {
				k = i;
			}else if(k == 1) {
				k = jj;
			}
		}
		//5. Come up with your own pattern to make a cool crazy image.
		
		//6. Use the ColorArrayDisplayer class to call the displayColorsAsImage method 
		//   to show off your picture.
		ColorArrayDisplayer cad = new ColorArrayDisplayer();
		cad.displayColorsAsImage(colors);
		
	}
	
	public static void main(String[] args) {
		new CrazyDigitalPainting();
	}
}

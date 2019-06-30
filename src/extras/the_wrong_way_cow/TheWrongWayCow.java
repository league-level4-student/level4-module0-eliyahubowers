//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.ArrayList;
import java.util.HashMap;

import _04_Maze_Maker.Cell;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	int[] positionOfTheBackwardsCow = new int[2];
    	int[] directionsTally = new int[4];
    	String[] directions = {"N","E","S","W"};
    	HashMap<String,String> cowsDirection = new HashMap<String,String>();
    	//find c{
        for(int i = 0; i < field.length; i++) {
        	for(int j = 0; j < field.length; j++) {
        		if(field[i][j] == 'c') {
        			//}find c
        			//find o{
        			for(int l = -1; l <= 1; l++) {
        				 for(int k = -1; k <= 1; k++) {
        					 if((i == j)||(i+j == 0)) {//if not adjacent  						 
        					 }else if(field[i+l][j+k] == 'o') {
        						 //}find o
        						 //right o?{
        						 if(field[i+(2*l)][j+(2*k)] == 'w') {
        							 //}right o?
        							 if(l == 1) {
        								 directionsTally[1] ++;
        								 cowsDirection.put("E", i+""+j);
        							 }else if(l == -1) {
        								 directionsTally[3] ++;
        								 cowsDirection.put("W", i+""+j);
        							 }else if(k == 1) {
        								 directionsTally[2] ++;
        								 cowsDirection.put("S", i+""+j);
        							 }else if(k == -1) {
        								 directionsTally[0] ++;
        								 cowsDirection.put("N", i+""+j);
        							 }
        							 l = 2;
        							 k = 2;
        						 }
        				     }
        				 }
        			 } 
        		}
        	}
        }
        for(int i = 0; i < directionsTally.length; i++) {
        	if(directionsTally[i] == 1) {
        		positionOfTheBackwardsCow[0] = Integer.parseInt(cowsDirection.get(directions[i]).substring(0, 1));
        		positionOfTheBackwardsCow[1] = Integer.parseInt(cowsDirection.get(directions[i]).substring(1));
        		i = directionsTally.length;
        	}
        }
        return positionOfTheBackwardsCow;
    }
}

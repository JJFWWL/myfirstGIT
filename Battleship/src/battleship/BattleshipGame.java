package battleship;

import java.util.Scanner;
/**
 * @author Jinjie Fan, discussed with Xiaoyong Liu(Sheryl), Tian Qiu
 * BattleshipGame control main function
 */
public class BattleshipGame {

	/**function to run as main function
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//play again loop
		boolean gamePlay=true;
		while (gamePlay) {
			System.out.println("Welcome to the Battleship Game! Let's play now!");
			//initialize the data
			//print the initial ocean map
			Ocean ocean=new Ocean();
			ocean.placeAllShipsRandomly();
			ocean.print();
			//row and column variables for user to input
			int row;
			int column;
			//variables that story totalshot, totalhit, shipsunk numbers
			int totalshot=0;
			int totalhit=0;
			int shipsSunk=0;
			Scanner scan=new Scanner(System.in);
			//loop for game play
			while(!ocean.isGameOver()) {
				
				//loop for user input check row (0-9)
				do {
				System.out.println("Please enter row num (0-9)");
				if (scan.hasNextInt()) {
					row=scan.nextInt();
					if(0<=row && row<=9) {
						break;
					}
					else {
						System.out.println("input is out of 0-9 range. Please enter num (0-9)");
						
					}
				}
				else {
					System.out.println("no number input. Please enter num (0-9)");
					scan.nextLine();
				}
				}while(true); 
				
				//loop for user input check column (0-9)
				do {
				System.out.println("Please enter column num (0-9)");
				if (scan.hasNextInt()) {
					column=scan.nextInt();
					if(0<=column && column<=9) {
						break;
					}
					else {
						System.out.println("input is out of 0-9 range. Please enter num (0-9)");
						
					}
				}
				else {
					System.out.println("no number input. Please enter num (0-9)");
					scan.nextLine();
				}
				}while(true); 
				
		
				
				
				//after user hit, update the data and story the shoot information
				boolean checkshoot=true;
				checkshoot=ocean.shootAt(row, column);
				totalshot=ocean.getShotsFired();
				totalhit=ocean.getHitCount();
				shipsSunk=ocean.getShipsSunk();
				
				//print the informations for user
				if(checkshoot && !ocean.getShipArray()[row][column].isSunk()) {
					System.out.println("hit");
				}
				else if (checkshoot && ocean.getShipArray()[row][column].isSunk()) {
					System.out.println("hit,you just sank a ship - "+ ocean.getShipArray()[row][column].getShipType());
				}
				else if(!checkshoot && ocean.getShipArray()[row][column].isSunk()) {
					System.out.println("miss, you shoot at sunk ship");
				}
				else {
					System.out.println("miss, you shoot at empty ocean");
				}
				
				//print updated shot, hit, sunk ship information
				System.out.println("total shots= "+totalshot);
				System.out.println("total hits= "+totalhit);
				System.out.println("total sunk ships= "+shipsSunk);
				ocean.print();
				
				if(ocean.isGameOver()) {
					System.out.println("congratulation! you sunk all ships and your final score is: "+ totalshot);
				}
			}
			
			
			
			//ask user whether play one more times
			
			Scanner scan2=new Scanner(System.in);
			
			do {
				System.out.println("do you want to play again? (Y/N)");
				String userAnswer=scan2.nextLine();
				if (userAnswer.equals("Y")||userAnswer.equals("y")||userAnswer.equals("Yes")||userAnswer.equals("yes")||userAnswer.equals("YES")) {
					gamePlay=true;
					break;
				}
				else if (userAnswer.equals("N")||userAnswer.equals("n")||userAnswer.equals("NO")||userAnswer.equals("no")||userAnswer.equals("No")) {
					gamePlay=false;
					System.out.println("BYE");
					scan.close();
					scan2.close();
					break;
				}
				else {
					System.out.println("not valid, please re-enter!");
				}
			} while (true);
			
			

	}
	
}

}

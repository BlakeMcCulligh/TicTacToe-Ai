package ticTacToe;

import java.io.*;

/**
 * This class will create a GameBoard object and allow a round of TicTacToe to be played against a random AI.
 * 
*@author Mr. Aldworth
*@author Blake McCulligh
*/
public class RondumAi {
	public static void RandomAI() throws Exception {
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in), 1);
		GameBoard game = new GameBoard();// makes a blank gameboard
		int turn = 0;
		int index;
		boolean errorFlag;
		boolean gameOver = false;
		
		System.out.println("You are X's");
		System.out.println("Press ENTER to continue");
		keyboard.readLine();
		System.out.println();
		System.out.println();
		game.drawBoard();
		System.out.println();
		System.out.println();
		
		while (gameOver == false) {
			
			errorFlag = false;
			
			if (turn % 2 == 0) {// if it is player 1's turn
				
				do {
					System.out.print("Enter your choice (1-9): ");
					index = Integer.parseInt(keyboard.readLine());// Assumes a valid number is entered
					errorFlag = game.play("X", index);
					
					if (errorFlag == false)
						System.out.println("That square is already taken or invalid.  Try again");
					
				} while (errorFlag == false);// makes sure player enters a square not yet used

				if (game.checkWin("X")) {
					
					System.out.println();
					System.out.println();
					game.drawBoard();
					System.out.println();
					System.out.println();
					
					System.out.println("YOU WIN!!");
					gameOver = true;
				}
				
				
			} else {// player 2s turn
				
				do {
					index = (int)(Math.random()*9+1);//picks a random tile
					
					errorFlag = game.play("O", index);
					
				} while (errorFlag == false);// makes sure the tile is not yet used
				
				System.out.println();
				System.out.println();
				game.drawBoard();
				System.out.println();
				System.out.println();
				
				if (game.checkWin("O")) {
					System.out.println("YOU Lose!!!");
					gameOver = true;
				}

			}
			
			turn++;
			
			if (turn == 9 && gameOver == false) {
				gameOver = true;
				System.out.println("DRAW");
			}
		} // end while
	}// end main
}// end class


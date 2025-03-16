package ticTacToe;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

import lerningAi.Database;
import lerningAi.PickMove;

/**
 * This class will create a GameBoard object and allow a round of TicTacToe to
 * be played against a AI that learns as more games are played.
 * 
 * @author Mr. Aldworth
 * @author Blake McCulligh
 */
public class LerningAI {

	public static void LearningAi(File moveRanksFile) throws Exception {
		
		Database.setup(moveRanksFile);

		Scanner scanner = new Scanner(System.in);

		boolean play;

		do {// starts a new game as long as the player wants to continue playing
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in), 1);
			GameBoard game = new GameBoard();// makes a blank gameboard
			int turn = 0;
			int index;
			boolean errorFlag;
			boolean gameOver = false;
			int gameResult = 0;

			System.out.println("You are X's");
			System.out.println("Press ENTER to continue");
			keyboard.readLine();
			System.out.println();
			game.drawBoard();
			System.out.println();

			while (gameOver == false) {

				errorFlag = false;

				if (turn % 2 == 0) {// AIs turn

					Database.updateboardState(game);
					game.play("O", PickMove.play() + 1);

					System.out.println();
					game.drawBoard();
					System.out.println();

					if (game.checkWin("O")) {
						gameResult = 1;

						System.out.println("YOU Lose!!!");
						gameOver = true;
					}

				} else {// players turn
					
					/*
					  do { index = (int)(Math.random()*9+1);
					  
					  errorFlag = game.play("X", index);
					  
					  } while (errorFlag == false);// makes sure the square is not yet used
					 */
					
					do {

						System.out.print("Enter your choice (1-9): ");
						index = Integer.parseInt(keyboard.readLine());// Assumes a valid number is entered
						errorFlag = game.play("X", index);

						if (errorFlag == false)
							System.out.println("That square is already taken or invalid.  Try again");

					} while (errorFlag == false);// makes sure player enters a square not yet used

					System.out.println();
					game.drawBoard();
					System.out.println();

					if (game.checkWin("X")) {

						gameResult = -1;

						System.out.println();
						game.drawBoard();
						System.out.println();
						System.out.println("YOU WIN!!");

						gameOver = true;
					}

				}

				Database.updateboardState(game);
				Database.updateStates(turn);
				turn++;

				if (turn == 9 && gameOver == false) {

					gameResult = 0;

					gameOver = true;
					System.out.println("DRAW");
				}
			}

			Database.updateStates(turn);
			Database.updateDataBase(gameResult, turn);

			System.out.println("Would you like to play again? (Yes, No)");

			String playAgain = scanner.nextLine();

			if (playAgain.equals("No") || playAgain.equals("no")) {
				play = false;
			} else {
				play = true;
			}
		
		} while (play == true);
		
		Database.save(moveRanksFile);

		scanner.close();
	}
}

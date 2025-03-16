package ticTacToe;

import java.io.*;

/**
 * This class will create a GameBoard object and allow a round of
 * TicTacToe to be played against a impossible to beat AI.
 * 
 * @author Mr. Aldworth
 * @author Blake McCulligh
 */
public class ImposibleAI {

	public static void ImposibleAi() throws Exception {

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in), 1);
		GameBoard game = new GameBoard();// makes a blank gameboard
		int turn = 0;
		int index;
		boolean errorFlag;
		boolean gameOver = false;

		System.out.println("You are X's");
		System.out.println("Press ENTER to continue");
		keyboard.readLine();

		while (gameOver == false) {

			errorFlag = false;

			if (turn % 2 == 0) {// AIs turn

				game.play("O", AIsChoice(game, turn) + 1);

				System.out.println();
				game.drawBoard();
				System.out.println();

				if (game.checkWin("O")) {
					System.out.println("YOU Lose!!!");
					gameOver = true;
				}

			} else {// players turn

				do {
					System.out.print("Enter your choice (1-9): ");
					index = Integer.parseInt(keyboard.readLine());// Assumes a valid number is entered
					errorFlag = game.play("X", index);

					if (errorFlag == false)
						System.out.println("That square is already taken or invalid.  Try again");
				} while (errorFlag == false);// makes sure player enters a square not yet used

				if (game.checkWin("X")) {

					System.out.println();
					game.drawBoard();
					System.out.println();

					System.out.println("YOU WIN!!");
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

	/**
	 * Finds what spot is the best move. Based on the player playing perfectly.
	 * 
	 * @param game The game board.
	 * @param turn How many moves have been played.
	 * @return The best move for the AI.
	 */
	static int AIsChoice(GameBoard game, int turn) {

		int result;
		int bestResult = -1000;
		int bestMove = -1;

		for (int i = 0; i < 9; i++) {

			if (game.board[i].owned() == false) {

				game.board[i].setOwner("O");

				result = minimax(game, game.board[i], 0, turn, false);

				game.board[i].setOwner(null);

				if (result > bestResult) {
					bestResult = result;
					bestMove = i;
				}
			}[]
		}
		return bestMove;
	}

	/**
	 * Finds what result that move will lead to, 1 if it leads to a AI win, -1 if it leads to a
	 * player wins, 0 for leading to a tie. Always assumes the player plays perfectly.
	 * 
	 * @param game The Game Board.
	 * @param tile The Tile on the board.
	 * @param depth How many moves into the future is currently being looked at.
	 * @param turn How many moves have been played.
	 * @param isMaximizing Is it currently trying to maximize the score.
	 * @return The rank for that move.
	 */
	static int minimax(GameBoard game, GameTile tile, int depth, int turn, boolean isMaximizing) {

		//if the game is over return the result
		if (game.checkWin("O")) {
			return 1;
		} else if (game.checkWin("X")) {
			return -1;
		} else if (turn + depth == 10) {
			return 0;
		}

		if (isMaximizing) { // finds the best result for meves when it is the ais turn
			
			int result;
			int bestResult = -1;

			for (int i = 0; i < 9; i++) {
				if (game.board[i].owned() == false) {

					//temparraly set the tile to O
					game.board[i].setOwner("O");

					//calls itself if to find results for posible moves
					result = minimax(game, game.board[i], depth + 1, turn, false);

					//remove the tempary owner
					game.board[i].setOwner(null);

					if (result > bestResult) {
						bestResult = result;
					}
				}
			}
			return bestResult;

		} else { // finds the result that is the worst for the ai when it is the players turn
					//(result for the players best move)
			
			int result;
			int worstResult = 1;

			for (int i = 0; i < 9; i++) {
				if (game.board[i].owned() == false) {

					//Temporally set the tile to O
					game.board[i].setOwner("X");

					//calls itself if to find results for posible moves
					result = minimax(game, game.board[i], depth + 1, turn, true);

					//remove the tempary owner
					game.board[i].setOwner(null);

					if (result < worstResult) {
						worstResult = result;
					}
				}
			}
			return worstResult;
		}
	}

}

package lerningAi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import ticTacToe.GameBoard;

/**
 * This class is for the lerning ai stores all data used for the learning ai
 * updates the data
 * 
 * @author Blake McCulligh
 */
public class Database {

	/**
	 * Ranks OF All Board States Each square bracket is what the corresponding game
	 * tiles state is 0 is empty, 1 is AI, 2 is the human player, 5 is not yet been
	 * in a game Equals to who will win with that game board state -1 AI loss, 0
	 * tie, 1 AI win.
	 */
	static int moveRank[][][][][][][][][] = new int[3][3][3][3][3][3][3][3][3];

	/**
	 * Board States In Last Game. Stores all the different board states in the last
	 * game First square bracket is what turn it is, and the second is what tile
	 * Equals to who owns that tile 0 is empty, 1 is AI, 2 is the human player.
	 */
	static int bSILG[][] = new int[10][9];

	/**
	 * Current Board State Square bracket is what tile Equals to who owns that tile 0
	 * is empty, 1 is AI, 2 is the human player.
	 */
	static int boardState[] = new int[9];

	/**
	 * Setup Move Rank sets all of move rank to 5 if there is saved data the use it.
	 * 
	 * @throws FileNotFoundException File that stores the move ranks.
	 */
	public static void setup(File moveRanksFile) throws FileNotFoundException {

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				for (int c = 0; c < 3; c++) {
					for (int d = 0; d < 3; d++) {
						for (int e = 0; e < 3; e++) {
							for (int f = 0; f < 3; f++) {
								for (int j = 0; j < 3; j++) {
									for (int k = 0; k < 3; k++) {
										for (int l = 0; l < 3; l++) {
											moveRank[a][b][c][d][e][f][j][k][l] = 5;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// making sure there is actually data on the text file before importing it.
		Scanner freader1 = new Scanner(moveRanksFile);

		String line = "5";
		line = freader1.nextLine();
		freader1.close();

		if (line.equals("1") || line.equals("0") || line.equals("-1") || line.equals("5")) {
			Scanner freader2 = new Scanner(moveRanksFile);
			for (int a = 0; a < 3; a++) {
				for (int b = 0; b < 3; b++) {
					for (int c = 0; c < 3; c++) {
						for (int d = 0; d < 3; d++) {
							for (int e = 0; e < 3; e++) {
								for (int f = 0; f < 3; f++) {
									for (int j = 0; j < 3; j++) {
										for (int k = 0; k < 3; k++) {
											for (int l = 0; l < 3; l++) {

												int line2 = 5;
												line2 = Integer.parseInt(freader2.nextLine());

												moveRank[a][b][c][d][e][f][j][k][l] = line2;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			freader2.close();
		}
	}

	/**
	 * Updates the boardState[] to the current layout of the board.
	 * 
	 * @param game The game Board.
	 */
	public static void updateboardState(GameBoard game) {

		for (int i = 0; i < 9; i++) {
			if (game.board[i].getOwner() == "O") {
				boardState[i] = 1;
			} else if (game.board[i].getOwner() == "X") {
				boardState[i] = 2;
			} else {
				boardState[i] = 0;
			}
		}
	}

	/**
	 * Adds a turn to bSILG[][] (Board States In Last Game) runs after every turn.
	 * 
	 * @param turn How many moves have been played.
	 */
	public static void updateStates(int turn) {

		for (int b = 0; b < 9; b++) {
			bSILG[turn][b] = boardState[b];
		}
	}

	/**
	 * Updates moveRank after every game.
	 * 
	 * @param gameResult Who won the game.
	 * @param turn How many moves have been played.
	 */
	public static void updateDataBase(int gameResult, int turn) {

		// saves for all the rotations and mirrors of the board
		int num[][] = { { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, { 6, 3, 0, 7, 4, 1, 8, 5, 2 }, { 8, 7, 6, 5, 4, 3, 2, 1, 0 },
				{ 2, 5, 8, 1, 4, 7, 0, 3, 6 }, { 2, 1, 0, 5, 4, 3, 8, 7, 6 }, { 8, 5, 2, 7, 4, 1, 6, 3, 0 },
				{ 6, 7, 8, 3, 4, 5, 0, 1, 2 }, { 0, 3, 6, 1, 4, 7, 2, 5, 8 } };

		garantedMoves(gameResult, turn, num);

		notGarantedMoves(gameResult, turn, num);
	}

	/**
	 * Updates the garmented results from a certain board state.
	 * 
	 * @param gameResult Who won the game.
	 * @param turn How many moves have been played.
	 * @param num Rotations and mirrors of the board.
	 */
	public static void garantedMoves(int gameResult, int turn, int[][] num) {
		for (int i = 0; i < 8; i++) {
			// sets the final move to the game result
			moveRank[boardState[num[i][0]]][boardState[num[i][1]]][boardState[num[i][2]]][boardState[num[i][3]]][boardState[num[i][4]]][boardState[num[i][5]]][boardState[num[i][6]]][boardState[num[i][7]]][boardState[num[i][8]]] = gameResult;

			// sets the second last move to the game result
			moveRank[bSILG[turn - 2][num[i][0]]][bSILG[turn - 2][num[i][1]]][bSILG[turn
					- 2][num[i][2]]][bSILG[turn - 2][num[i][3]]][bSILG[turn
							- 2][num[i][4]]][bSILG[turn - 2][num[i][5]]][bSILG[turn
									- 2][num[i][6]]][bSILG[turn - 2][num[i][7]]][bSILG[turn
											- 2][num[i][8]]] = gameResult;
		}
	}

	/**
	 * Updates the not garmented results from a certain board state the results for
	 * moves other then the last two.
	 * 
	 * @param gameResult Who won the game.
	 * @param turn How many moves have been played.
	 * @param num Rotations and mirrors of the board.
	 */
	public static void notGarantedMoves(int gameResult, int turn, int[][] num) {

		for (int j = turn; j > 0; j--) {

			int posibleMoves[] = posibleMoveFinder(j);

			int posibleMovesRanks[] = posibleMoveRankFinder(posibleMoves, j);

			int posibleMovesWithoutRank[] = PickMove.posibleMovesWithoutRankFinder(posibleMovesRanks);

			if (posibleMovesWithoutRank.length == 0) {

				int bestMove = -1;
				for (int i = 0; i < posibleMoveFinder(j).length; i++) {
					if (posibleMovesRanks[i] > bestMove) {
						bestMove = posibleMovesRanks[i];
					}
				}

				for (int i = 0; i < 8; i++) {
					moveRank[bSILG[j - 1][num[i][0]]][bSILG[j - 1][num[i][1]]][bSILG[j - 1][num[i][2]]][bSILG[j
							- 1][num[i][3]]][bSILG[j - 1][num[i][4]]][bSILG[j - 1][num[i][5]]][bSILG[j
									- 1][num[i][6]]][bSILG[j - 1][num[i][7]]][bSILG[j - 1][num[i][8]]] = bestMove;
				}
			}
		}
	}

	/**
	 * Finds all the possible moves and stores them in a array for any board state in
	 * the last game.
	 * 
	 * @param j What turn is being looked at.
	 * @return The possible moves.
	 */
	public static int[] posibleMoveFinder(int j) {

		int numOfPosibleMoves = 0;

		for (int i = 0; i < 9; i++) {
			if (bSILG[j][i] == 0)
				numOfPosibleMoves++;
		}

		int posibleMoves[] = new int[numOfPosibleMoves];
		int posMove = 0;

		for (int i = 0; i < 9; i++) {
			if (bSILG[j][i] == 0) {
				posibleMoves[posMove] = i;
				posMove++;
			}
		}
		return posibleMoves;
	}

	/**
	 * Finds the ranks of all the possible moves for any board state in
	 * the last game.
	 * 
	 * @param posibleMoves All of the empty tiles.
	 * @param j What turn is being looked at.
	 * @return Ranks of the possible moves.
	 */
	public static int[] posibleMoveRankFinder(int posibleMoves[], int j) {

		int posibleMovesRanks[] = new int[posibleMoves.length];

		for (int i = 0; i < posibleMoves.length; i++) {
			if (posibleMoves[i] == 0) {
				posibleMovesRanks[i] = moveRank[1][bSILG[j][1]][bSILG[j][2]][bSILG[j][3]][bSILG[j][4]][bSILG[j][5]][bSILG[j][6]][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 1) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][1][bSILG[j][2]][bSILG[j][3]][bSILG[j][4]][bSILG[j][5]][bSILG[j][6]][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 2) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][1][bSILG[j][3]][bSILG[j][4]][bSILG[j][5]][bSILG[j][6]][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 3) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][bSILG[j][2]][1][bSILG[j][4]][bSILG[j][5]][bSILG[j][6]][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 4) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][bSILG[j][2]][bSILG[j][3]][1][bSILG[j][5]][bSILG[j][6]][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 5) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][bSILG[j][2]][bSILG[j][3]][bSILG[j][4]][1][bSILG[j][6]][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 6) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][bSILG[j][2]][bSILG[j][3]][bSILG[j][4]][bSILG[j][5]][1][bSILG[j][7]][bSILG[j][8]];

			} else if (posibleMoves[i] == 7) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][bSILG[j][2]][bSILG[j][3]][bSILG[j][4]][bSILG[j][5]][bSILG[j][6]][1][bSILG[j][8]];

			} else if (posibleMoves[i] == 8) {
				posibleMovesRanks[i] = moveRank[bSILG[j][0]][bSILG[j][1]][bSILG[j][2]][bSILG[j][3]][bSILG[j][4]][bSILG[j][5]][bSILG[j][6]][bSILG[j][7]][1];
			}
		}
		return posibleMovesRanks;
	}

	/**
	 * Saves all the data to a text file.
	 * 
	 * @param moveRanksFile File that stores the move ranks.
	 * @throws IOException
	 */
	public static void save(File moveRanksFile) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(moveRanksFile));

		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				for (int c = 0; c < 3; c++) {
					for (int d = 0; d < 3; d++) {
						for (int e = 0; e < 3; e++) {
							for (int f = 0; f < 3; f++) {
								for (int j = 0; j < 3; j++) {
									for (int k = 0; k < 3; k++) {
										for (int l = 0; l < 3; l++) {

											writer.write(String.valueOf(moveRank[a][b][c][d][e][f][j][k][l]));
											writer.newLine();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		writer.close();
	}
}

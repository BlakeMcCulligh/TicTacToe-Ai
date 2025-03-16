package ticTacToe;

/**
 * Class for Tic Tac Toe gameboard
 * 
 * @author Blake McCuliigh
 */
public class GameBoard {
	
	/**
	 * An array of 9 GameTiles representing the TicTacToe gameboard
	 */
	public GameTile[] board;

	public GameTile getTile(int num) {
		return board[num];
	}

	/**
	 * Constructs an empty gameboard of 9 GameTiles and fills it with unowned tiles
	 */
	public GameBoard() {
		board = new GameTile[9];
		for (int i = 0; i < 9; i++)
			board[i] = new GameTile(i);
	}

	/**
	 * This will allow a player to claim a GameTile on the TicTacToe board
	 * 
	 * @return A boolean true if the player successfully played on a tile, false if
	 *         that tile is already owned or the index is out of bounds
	 * @param player A String indicating which player is to own the tile ("X" or
	 *               "O")
	 * @param tile   An integer representing the tile the player wishes to claim
	 */
	public boolean play(String player, int tile) {

		if (board[tile - 1].owned() == false) {
			board[tile - 1].setOwner(player);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This will check to see if there are three tiles in a row belonging to a
	 * player
	 * 
	 * @return A boolean true if the player has three tiles in a row, false
	 *         otherwise
	 * @param player A String indicating which player to check for a win ("X" or
	 *               "O")
	 */
	public boolean checkWin(String player) {

		// top row
		int numInRow = 0;
		for (int i = 0; i < 3; i++)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// middle row
		for (int i = 3; i < 6; i++)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// bottom row
		for (int i = 6; i < 9; i++)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// left colum
		for (int i = 0; i < 7; i += 3)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// middle colum
		for (int i = 1; i < 8; i += 3)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// right colum
		for (int i = 2; i < 9; i += 3)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// decresing diagenl
		for (int i = 0; i < 9; i += 4)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		numInRow = 0;

		// incressing dianganl
		for (int i = 2; i < 7; i += 2)
			if (board[i].getOwner() != null && board[i].getOwner().equals(player))
				numInRow++;

		if (numInRow == 3)
			return true;

		return false;
	}

	/**
	 * This will draw the current gameboard on the screen
	 */
	public void drawBoard() {

		String owner[] = new String[9];

		// changing the nulls to spaces for printing
		for (int i = 0; i < 9; i++) {

			if (board[i].getOwner() == "X" || board[i].getOwner() == "O") {
				owner[i] = board[i].getOwner();

			} else {
				owner[i] = " ";
			}
		}

		System.out.println("┌─┬─┬─┐");

		System.out.println("│" + owner[0] + "│" + owner[1] + "│" + owner[2] + "│");

		System.out.println("├─┼─┼─┤");

		System.out.println("│" + owner[3] + "│" + owner[4] + "│" + owner[5] + "│");

		System.out.println("├─┼─┼─┤");

		System.out.println("│" + owner[6] + "│" + owner[7] + "│" + owner[8] + "│");

		System.out.println("└─┴─┴─┘");
	}
}// end class

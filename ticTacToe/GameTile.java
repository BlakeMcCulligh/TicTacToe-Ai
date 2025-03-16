package ticTacToe;

/**
 * Class for Tic-Tac-Toe tile
 * 
 * @author Blake McCulligh
 */
public class GameTile {
	/**
	 * Stores a String representing the owner "X" or "O" of a GameTile
	 */
	public String owner;
	int number;

	/**
	 * Constructs a GameTile, sets owner to null by default
	 */
	public GameTile(int i) {
		owner = null;
		number = i;
	}

	/**
	 * This will return who owns this particular GameTile
	 * 
	 * @return String "X" if player 1 owns tile, "Y" if player 2 owns tile, empty
	 *         string if unowned
	 */
	public String getOwner() {
		
		if (owner == null) 
			return " ";
		
		return owner;
	}

	/**
	 * This will return the number asinged to the tile
	 * 
	 * @return int of tile
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * This will assign a new owner to the game tile
	 * 
	 * @param player A String indicating which player will own the tile ("X" or "O")
	 */
	public void setOwner(String player) {
		owner = player;
	}

	/**
	 * This will determine whether any player owns a particular tile
	 * 
	 * @return boolean true if a player owns the tile, false otherwise
	 */
	public boolean owned() {
		if (owner != null) {
			return true;
		} else {
			return false;
		}
	}
}

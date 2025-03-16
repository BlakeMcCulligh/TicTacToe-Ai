package lerningAi;

/**
 * This class is for the learning AI. 
 * Picks the next best move if the data is already collected.
 * If there is not yet enough data pick a random move that does not lead to a loss.
 * 
 * @author Blake McCulligh
 */
public class PickMove {

	/**
	 * Picks the ai's move.
	 * If all of the next moves have a rank play the next best move.
	 * If there is a next move that leads to a win play it.
	 * If non of the above are true play a random move that has no data.
	 * 
	 * @return AI's move
	 */
	public static int play() {

		int posibleMoves[] = posibleMoveFinder();

		int posibleMovesRanks[] = posibleMoveRankFinder(posibleMoves);

		//if there is a move that leads to a win play it
		for (int i = 0; i < posibleMoves.length; i++) {
			if (posibleMovesRanks[i] == 1) {
				return posibleMoves[i];
			}
		}

		int posibleMovesWithoutRank[] = posibleMovesWithoutRankFinder(posibleMovesRanks);

		//if there are no moves that lead to a win and no moves that have no rank play a move the leads to a tie
		if (posibleMovesWithoutRank.length == 0) {
			for (int i = 0; i < posibleMoves.length; i++) {
				if (posibleMovesRanks[i] == 0) {
					return posibleMoves[i];
				}
			}
		}
		
		//if there is only one posible move play it
		if (posibleMoves.length == 1) {
			return posibleMoves[0];
		}
		
		//this shuld not be needed but some how every 1000 games or so it is needed
		if(posibleMovesWithoutRank.length == 0) {
			return posibleMoves[(int)(Math.random()*posibleMoves.length)];
		}
		
		//if there is no move that leads to a win play a random move
		return posibleMoves[posibleMovesWithoutRank[ (int)(Math.random()*posibleMovesWithoutRank.length)]];
	}

	/**
	 * Finds all the possible moves and stores them in a array.
	 * 
	 * @return The possible moves.
	 */
	public static int[] posibleMoveFinder() {

		int numOfPosibleMoves = 0;

		for (int i = 0; i < 9; i++) {
			if (Database.boardState[i] == 0)
				numOfPosibleMoves++;
		}

		int posibleMoves[] = new int[numOfPosibleMoves];
		int posMove = 0;

		for (int i = 0; i < 9; i++) {
			if (Database.boardState[i] == 0) {
				posibleMoves[posMove] = i;
				posMove++;
			}
		}
		return posibleMoves;
	}

	/**
	 * Finds the ranks of all the possible moves.
	 * 
	 * @param posibleMoves All of the possible moves.
	 * @return Ranks of the possible moves.
	 */
	public static int[] posibleMoveRankFinder(int posibleMoves[]) {

		int posibleMovesRanks[] = new int[posibleMoves.length];

		for (int i = 0; i < posibleMoves.length; i++) {
			if (posibleMoves[i] == 0) {
				posibleMovesRanks[i] = Database.moveRank[1][Database.boardState[1]][Database.boardState[2]][Database.boardState[3]][Database.boardState[4]][Database.boardState[5]][Database.boardState[6]][Database.boardState[7]][Database.boardState[8]];
		
			} else if (posibleMoves[i] == 1) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][1][Database.boardState[2]][Database.boardState[3]][Database.boardState[4]][Database.boardState[5]][Database.boardState[6]][Database.boardState[7]][Database.boardState[8]];

			} else if (posibleMoves[i] == 2) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][1][Database.boardState[3]][Database.boardState[4]][Database.boardState[5]][Database.boardState[6]][Database.boardState[7]][Database.boardState[8]];

			} else if (posibleMoves[i] == 3) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][Database.boardState[2]][1][Database.boardState[4]][Database.boardState[5]][Database.boardState[6]][Database.boardState[7]][Database.boardState[8]];

			} else if (posibleMoves[i] == 4) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][Database.boardState[2]][Database.boardState[3]][1][Database.boardState[5]][Database.boardState[6]][Database.boardState[7]][Database.boardState[8]];

			} else if (posibleMoves[i] == 5) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][Database.boardState[2]][Database.boardState[3]][Database.boardState[4]][1][Database.boardState[6]][Database.boardState[7]][Database.boardState[8]];

			} else if (posibleMoves[i] == 6) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][Database.boardState[2]][Database.boardState[3]][Database.boardState[4]][Database.boardState[5]][1][Database.boardState[7]][Database.boardState[8]];

			} else if (posibleMoves[i] == 7) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][Database.boardState[2]][Database.boardState[3]][Database.boardState[4]][Database.boardState[5]][Database.boardState[6]][1][Database.boardState[8]];

			} else if (posibleMoves[i] == 8) {
				posibleMovesRanks[i] = Database.moveRank[Database.boardState[0]][Database.boardState[1]][Database.boardState[2]][Database.boardState[3]][Database.boardState[4]][Database.boardState[5]][Database.boardState[6]][Database.boardState[7]][1];
			}
		}
		return posibleMovesRanks;
	}

	/**
	 * Finds all the possible moves that do not have a rank.
	 * 
	 * @param posibleMovesRanks The ranks of all the possible moves.
	 * @return The possible moves without a rank.
	 */
	public static int[] posibleMovesWithoutRankFinder(int posibleMovesRanks[]) {

		int numOfposibleMovesWithoutRank = 0;

		for (int i = 0; i < posibleMovesRanks.length; i++) {
			if (posibleMovesRanks[i] == 5) {
				numOfposibleMovesWithoutRank++;
			}
		}

		int posibleMovesWithoutRank[] = new int[numOfposibleMovesWithoutRank];
		int posMove = 0;

		for (int i = 0; i < posibleMovesRanks.length; i++) {
			if (posibleMovesRanks[i] == 5) {

				posibleMovesWithoutRank[posMove] = i;
				posMove++;
			}
		}
		return posibleMovesWithoutRank;
	}

}

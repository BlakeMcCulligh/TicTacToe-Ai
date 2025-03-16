package ticTacToe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * This main program starts the kind of game the player wants to play.
 * 
 * @author Blake McCulligh
 */
public class Main {

	public static void main(String[] args) throws Exception {

		File moveRanksFile = new File("moveRanks.txt");

		System.out.print(
				"What vertion would you like to play (1 - 2 player, 2 - random AI, 3 - imposible AI, 4 - lerning AI: ");

		Scanner scanner = new Scanner(System.in);

		int gameMode = scanner.nextInt();

		if (gameMode == 1) {
			try {
				TwoPlayer.TwoPllayer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (gameMode == 2) {
			try {
				RondumAi.RandomAI();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (gameMode == 3) {
			try {
				ImposibleAI.ImposibleAi();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (gameMode == 4) {
		
			clearData(moveRanksFile);
			
			try {
				LerningAI.LearningAi(moveRanksFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		scanner.close();
	}
	
	/**
	 * Asks the user if they want to clear the data and if they reply yes, clear it.
	 * 
	 * @param moveRanksFile The file that stores the move ranks.
	 * @throws Exception
	 */
	public static void clearData(File moveRanksFile) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Would you like to delete all stored data? (yes or no)");
	
		String deleteData = scanner.nextLine();

		if (deleteData.equals("Yes") || deleteData.equals("yes")) {
			BufferedWriter writer = new BufferedWriter(new FileWriter(moveRanksFile));

			for (int i = 0; i < 19684; i++) {
				writer.write("5");
				writer.newLine();
			}
			writer.close();
		}
		//scanner.close();
	}
}

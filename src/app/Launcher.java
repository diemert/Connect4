package app;

import java.util.Scanner;

public class Launcher {
	public static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("WELCOME TO CONNECT4");
		System.out.println("\nPress enter to play");
		s.nextLine();
		System.out.println("What size board would you like to use?");
		int newSize = s.nextInt();
		while (true) {
			if (newSize > 10 || newSize < 3) {
				System.out.println("Please try again: ");
				newSize = s.nextInt();
			} else break;
		}
	
		System.out.println("How many pieces in a row will it take to win?");
		int newGoal = s.nextInt();
		while (true) {
			if (newGoal < 3 || newGoal > newSize) {
				System.out.println("Please try again: ");
				newGoal = s.nextInt();
			} else break;
		}
		Board game = new Board(newSize);
		game.setSize(newSize);
		game.setGoal(newGoal);
		
		
		while (true) {	
			gameLoop(game);
			System.out.println("Do you want to play again? Yes or no?");
			String yesorno = s.next();
			yesorno = yesorno.toLowerCase();
			if (!yesorno.equals("yes")) {
				System.out.println("Thank you for playing!"); 
				break;
			}
		}
	}
	
	public static void gameLoop(Board game) {
		game.reset();
		boolean player1 = true;
		System.out.println(game);
		while (true) {
			if (player1) {
				System.out.println("Player 1: Which column?");
				int choice  = s.nextInt();
				while (!game.dropPiece('X', choice)) {
					System.out.println("Please try again: ");
					choice  = s.nextInt();
				}
				System.out.println(game);
				if (game.isWinner('X')) {
					System.out.println("Player 1 wins!");
					System.out.println(game.changeScore('X'));
					break;
				}
			} 
			else {
				System.out.println("Player 2: Which column?");
				int choice  = s.nextInt();
				while (!game.dropPiece('O', choice)) {
					System.out.println("Please try again: ");
					choice  = s.nextInt();
				}
				System.out.println(game);
				if (game.isWinner('O')) {
					System.out.println("Player 2 wins!");
					System.out.println(game.changeScore('O'));
					break;
				}
			}
			
			if (game.tieGame()) {
				System.out.println("Tie game!");
				System.out.println(game.changeScore('L'));
				break;
			}
			player1 = !player1;
		}
		
	}
}

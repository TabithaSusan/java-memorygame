package controller.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.game.IGame;
import model.Card;
import model.IBoard;
import model.Position;

public class PlayerCUI implements IPlayer {

	private int id;
	private IGame game;
	private Position position1;
	private Position position2;
	/**
	 * Method to initialize the Player
	 * 
	 * @param game Current Game Session
	 * @param id
	 */

	@Override
	public void initialize(IGame game, int id) {
		this.game = game;
		this.id = id;
	}
	
	/**
	 * Method to read Input given via Consol
	 * 
	 * @param br bufferedreader
	 * @param input Userinput
	 */
	private int readInt() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			int i = Integer.parseInt(input);
			return i;			
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Method to enable the Player to flip cards, as soon as it is his turn.
	 * Checks if the chosen Cards are valid, if not outputs error.
	 */
	@Override
	public void enable() {
		IBoard board = game.getBoard();
		System.out.println("Spieler " + id + " ist am Zug.");
		// Show Board to Player
		System.out.println(board); 
		int input = -1;
		
		// What does the player want to do? Play a game, save or load a game?
		do {
			System.out.println("Wollen Sie ein eine Karte aufdecken, das Spiel speichern oder ein Spiel laden?");
			System.out.println(" 0 \t für Karte aufdecken\n 1 \t für Speichern \n 2 \t für Laden");
			input = readInt();
		} while(input < 0 || input > 2);
		
		if(input == 1) {
			game.save();
		} else if (input == 2) {
			game.load();
			return;
		}
		
		boolean valid = false;
		
		// Listing for Userinput to choose Card
		
		do {
			System.out.println("Wählen sie die Zeile der ersten Karte.");
			int row1 = readInt();
			System.out.println("Wählen sie die Spalte der ersten Karte.");
			int col1 = readInt();
			System.out.println("Wählen sie die Zeile der zweiten Karte.");
			int row2 = readInt();
			System.out.println("Wählen sie die Spalte der zweiten Karte.");
			int col2 = readInt();
			
			position1 = new Position(row1, col1);
			position2 = new Position(row2, col2);
			
			valid = board.validPosition(position1, position2);
			

			if(!valid) {
				System.out.println("Dieser Zug ist nicht möglich! Wählen Sie bitte anders.");
				
			}
		} while (!valid);
		game.update();
		
	}

	/**
	 * Method to disable Player, after the player as fliped to cards. This will end
	 * his turn.
	 */
	@Override
	public void disable() {
		System.out.println("Der Zug von Spieler " + id + " wurde beendet. Bitten Warten bis die Daten ausgewertet sind!");
	}

	/**
	 * Method track the first selected Card of the player.
	 */
	@Override
	public Position firstSelectedPosition() {
		return position1;
	}

	/**
	 * Method to track the second selected Card of the player.
	 */
	@Override
	public Position secondSelectedPosition() {
		return position2;
	}

	/**
	 * Method to show first selected Card.
	 * 
	 * @param fstCard card value
	 * @param fstSelectedPosition Position of the Card on the board
	 */
	@Override
	public void showFirstCard(Card fstCard, Position fstSelectedPosition) {
		 System.out.println("Der Kartenwert der Aufgedeckten Karte 1 ist: "+fstCard.getNumber());
	        System.out.println("Und die Karte 1 befindet sich an der Position: "+ fstSelectedPosition.getRow() + " | " + fstSelectedPosition.getColumn());
	        
	}

	/**
	 * Method to show second selected Card.
	 * 
	 * @param scndCard card value
	 * @param scndSelectedPostion Position of the Card on the board
	 */
	@Override
	public void showSecondCard(Card scndCard, Position scndSelectedPostion) {
		System.out.println("Der Kartenwert der Aufgedeckten Karte 2 ist: "+scndCard.getNumber());
        System.out.println("Und die Karte 2 befindet sich an der Position: "+ scndSelectedPostion.getRow() + " | " + scndSelectedPostion.getColumn());
        
	}

	/**
	 * Method to set Player as winner of the game
	 */
	@Override
	public void win() {
		System.out.println("Spieler " + id + " hat gewonnen!");
	}

	/**
	 * Method to set game as draw between two players
	 */
	@Override
	public void draw() {
		System.out.println("Es ist unentschieden!");
	}

	/**
	 * Method to set Player as the loser of the game.
	 */
	@Override
	public void lose() {
		System.out.println("Spieler " + id + " hat verloren.");
	}

}

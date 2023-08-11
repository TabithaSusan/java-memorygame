package controller.player;

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
	 * @param game
	 * @param id
	 */

	@Override
	public void initialize(IGame game, int id) {
		this.game = game;
		this.id = id;
	}

	/**
	 * Method to enable the Player to flip cards, as soon as it is his turn.
	 * Checks if the chosen Cards are valid, if not outputs error.
	 */
	@Override
	public void enable() {
		IBoard board = game.getBoard();
		System.out.println(board); // Spieler das Spielfeld anzeigen
		boolean valid = false;
		
		do {
			int row1 = 0; //TODO Userinput
			int col1 = 0; //TODO Userinput
			int row2 = 1; //TODO Userinput
			int col2 = 1; //TODO Userinput
			
			position1 = new Position(row1, col1);
			position2 = new Position(row2, col2);
			
			valid = board.validPosition(position1, position2);
			

			if(!valid) {
				System.out.println("Dieser Zug ist nicht m�glich!");
				
			}
		} while (!valid);
		
		game.update();
		
	}

	/**
	 * Method to disable Player, after the player as flipped to cards. This will end
	 * his turn.
	 */
	@Override
	public void disable() {
		// TODO Auto-generated method stub

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
	 * TODO muss nicht am board ver�ndert werden. eine nachricht auf der konsole reicht aus mit der nummer.
	 * 
	 * @param fstCard
	 * @param fstSelectedPosition
	 */
	@Override
	public void showFirstCard(Card fstCard, Position fstSelectedPosition) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to show second selected Card.
	 * 
	 * @param scndCard
	 * @param scndSelectedPosition
	 */
	@Override
	public void showSecondCard(Card scndCard, Position scndSelectedPostion) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to set Player as winner of the game
	 */
	@Override
	public void win() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to set game as draw between two players
	 */
	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to set Player as the loser of the game.
	 */
	@Override
	public void lose() {
		// TODO Auto-generated method stub

	}

}

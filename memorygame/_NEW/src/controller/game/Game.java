package controller.game;

import controller.player.IPlayer;
import model.IBoard;
import model.IHiddenBoard;

public class Game implements IGame {

	private IPlayer player1;
	private IPlayer player2;
	private IHiddenBoard board;
	
	public Game(IPlayer player1, IPlayer player2, IHiddenBoard board) {
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
	}
	
	/**
	 * Method to start the game
	 */
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		player1.initialize(this, 05);
		player2.initialize(this, 15);
	}

	/**
	 * Method that contains game logic. It will start a new turn with the other
	 * player. Will be called upon by current player.
	 * 
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to save current game to hard drive
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to load last game from hard drive
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to call up game board that will be visible to the player.
	 */
	@Override
	public IBoard getBoard() {
		return board;
	}

}

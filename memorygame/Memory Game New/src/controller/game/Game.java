package controller.game;

import controller.player.IPlayer;
import controller.saveload.FileWriterReader;
import model.IBoard;
import model.IHiddenBoard;
import model.PersistenceObject;

public class Game implements IGame {

	private IPlayer player1;
	private IPlayer player2;
	private IBoard board;
	private IHiddenBoard hiddenboard;
	
	private int whatsTheCurrentStatus;
	private final int NOBODY = 0;
	private final int PLAYER1 = 1;
	private final int PLAYER2 = 3;
		
	
	public Game(IPlayer player1, IPlayer player2, IHiddenBoard board) {
		this.player1 = player1;
		this.player2 = player2;
		this.hiddenboard = board;
		whatsTheCurrentStatus = NOBODY;
	}
	
	/**
	 * Method to start the game
	 */
	
	@Override
	public void start() {
		//TODO Wieso ist das hier nicht genutzt?
		board = hiddenboard;
		player1.initialize(this, 01);
		player2.initialize(this, 02);
		whatsTheCurrentStatus = PLAYER1;
		player1.enable();
	}

	/**
	 * Method that contains game logic. It will start a new turn with the other
	 * player. Will be called upon by current player.
	 * 
	 */
	@Override
	 public void update() {
		if(whatsTheCurrentStatus == PLAYER1) {
			player1.disable();
			//Shows Card 1 & 2 at position with card value
			player1.showFirstCard(hiddenboard.getHiddenCardAtPosition(player1.firstSelectedPosition()), player1.firstSelectedPosition());
			player1.showSecondCard(hiddenboard.getHiddenCardAtPosition(player1.secondSelectedPosition()), player1.secondSelectedPosition());
			//If cards match they stay visible
			if(hiddenboard.isMatch(player1.firstSelectedPosition(), player1.secondSelectedPosition(), 01) == true) {
					//No more cards left to match
					if(board.finish() == true) {
						whatsTheCurrentStatus = NOBODY;
						//Player 1 has more matches then player 2
						if(board.pairsFoundBy(01) > board.pairsFoundBy(02)) {
							player1.win();
							player2.lose();
						//Player 2 has more matches then player 1
						}else if(board.pairsFoundBy(01) < board.pairsFoundBy(02)) {
							player1.lose();
							player2.win();
						 //PLayer 1 and 2 have the same amount of matches
						}else {
							player1.draw();
							player2.draw();
						}
					//There are still cards left to match
					}else {
						player1.enable();
					}
			}else {
				player1.disable();
				whatsTheCurrentStatus = PLAYER2;
				player2.enable();  
			}
			
		}else if(whatsTheCurrentStatus == PLAYER2) {
			player2.disable();
			//Shows Card 1 & 2 at position with card value
			player2.showFirstCard(hiddenboard.getHiddenCardAtPosition(player2.firstSelectedPosition()), player2.firstSelectedPosition());
			player2.showSecondCard(hiddenboard.getHiddenCardAtPosition(player2.secondSelectedPosition()), player2.secondSelectedPosition());
			//If cards match they stay visible
			if(hiddenboard.isMatch(player2.firstSelectedPosition(), player2.secondSelectedPosition(), 02) == true) {
					//No more cards left to match
					if(board.finish() == true) {
						whatsTheCurrentStatus = NOBODY;
						//Player 1 has more matches then player 2
						if(board.pairsFoundBy(01) > board.pairsFoundBy(02)) {
							player1.win();
							player2.lose();
						//Player 2 has more matches then player 1
						}else if(board.pairsFoundBy(01) < board.pairsFoundBy(02)) {
							player1.lose();
							player2.win();
						//PLayer 1 and 2 have the same amount of matches
						}else {
							player1.draw();
							player2.draw();
						}
					//There are still cards left to match
					}else {
						player2.enable();
					}
			}else {
				player2.disable();
				whatsTheCurrentStatus = PLAYER1;
				player1.enable();  
			}
		}
    		
	}
	/**
	 * Method to save current game to hard drive
	 */
	@Override
	public void save() {
		PersistenceObject po = new PersistenceObject(board, whatsTheCurrentStatus);
		FileWriterReader.writeToFile(po);
	}

	/**
	 * Method to load last game from hard drive
	 */
	@Override
	public void load() {
		PersistenceObject po = FileWriterReader.readFromFile();
		
		if(po != null) {
			this.whatsTheCurrentStatus = po.getStatus();
			this.board = po.getBoard();
			if(whatsTheCurrentStatus == PLAYER1) {
				player1.enable();
				player2.disable();
			} else if(whatsTheCurrentStatus == PLAYER2) {
				player2.enable();
				player1.disable();
			}
		}
	}

	/**
	 * Method to call up game board that will be visible to the player.
	 */
	@Override
	public IBoard getBoard() {
		return board;
	}

}

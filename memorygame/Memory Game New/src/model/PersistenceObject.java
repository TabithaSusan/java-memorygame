package model;

import java.io.Serializable;

public class PersistenceObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -127311658584995362L;
	private IBoard board;
	private int status;
	
	
	
	/**
	 * Constructor of the PersistenceObject for saving the state of the game
	 * 
	 * @param board2
	 * @param status
	 */
	public PersistenceObject(IBoard board, int status) {
		this.board = board;
		this.status = status;
	}
	
	
	/**
	 * get-Method of the board
	 * 
	 * @return the board of the persistence Object
	 */

	public IBoard getBoard() {
		return board;
	}

	/**
	 * get-Method of the status
	 * 
	 * @return the status of the persistence Object
	 */
	public int getStatus() {
		return status;
	}
}

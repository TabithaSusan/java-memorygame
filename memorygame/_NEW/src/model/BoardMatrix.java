package model;

public class BoardMatrix implements IBoard {

	protected Card[][] board;
	protected int height;
	protected int width;
	protected int score;

	/**
	 * Method to test if all card pairs have been found.
	 * 
	 * @return Is the game over?
	 */
	public boolean finish() {
		for (Card[] row : board) {
			for (Card card : row) {
				if (card.discoveredBy() == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method to track who found how many pairs.
	 * 
	 * @param playerID ID of the current player.
	 * @param counter  Score counter of playerID
	 * @return How many pairs have been found?
	 */

	public int pairsFoundBy(int playerID) {
		int counter = 0;
		for (Card[] row : board) {
			for (Card card : row) {
				if (card.discoveredBy() == playerID) {
					counter++;
				}
			}
		}
		return counter;
	}

	/**
	 * Method
	 * 
	 * @param height
	 * @param width
	 */
	public BoardMatrix(int height, int width) {
		this.height = height;
		this.width = width;
		board = new Card[height][width];

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) {
				board[row][column] = new Card(0);
			}
		}
	}

	/**
	 * Mathod to state the bounds of the game.
	 * 
	 * @param p Position of the current card.
	 * @return Is the card within the bounds?
	 */

	public boolean inBounds(Position p) {
		return p.getRow() >= 0 && p.getRow() < height && p.getColumn() >= 0 && p.getColumn() < width;
	}

	/**
	 * Method to flip the card.
	 * 
	 * @param p Position der Karte
	 * @return What number does the card have?
	 */

	public Card getCardAtPosition(Position p) {
		if (inBounds(p)) {
			return board[p.getRow()][p.getColumn()];
		}
		return new Card(0);
	}

	/**
	 * Method to check if cards is on Valid Position.
	 * a)Are those cards within the bound?
	 * b)Are those cards two different cards?
	 * c)Are those cards still Hidden?
	 * 
	 * @param position1 Position of first selected Card.
	 * @param position2 Position of second selected Card.
	 * @return are those two cards a valid choice?
	 */

	public boolean validPosition(Position position1, Position position2) {
		if (!inBounds(position1) || !inBounds(position2)) {
			return false;
		}
		if (position1.equals(position2)) {
			return false;
		}
		if (!getCardAtPosition(position1).isHidden() || !getCardAtPosition(position2).isHidden()) {
			return false;
		}
		return true;
	}

	public String toString() {
		String s = "";
		for (Card[] row : board) {
			for (Card card : row) {
				s += card.getNumber() + "\t";
			}
			s += "\n";
		}
		return s;
	}

}

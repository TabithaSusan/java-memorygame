package model;

public class SetBoardMatrix extends BoardMatrix implements SetBoard {

	public SetBoardMatrix(int height, int width) {
		super(height, width);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Method to flip Card so Players can see the number on the back.
	 * Will not flip it for more than one turn, of not pair.
	 * @param p Position of hidden card
	 * @return Can the card be fliped?
	 */

	@Override
	public boolean setFirstCardFlipped(Position p) {
		// TODO Auto-generated method stub
		if(super.inBounds(p)) {
			board[p.getRow()][p.getColumn()] = Content.NUMBER;
			return true;
		}
		return false;
	}
	
	/**
	 * Method to flip Card so Players can see the number on the back.
	 * Will not flip it for more than one turn, of not pair.
	 * @param p Position of hidden card
	 * @return Can the card be flipped?
	 */

	@Override
	public boolean setSecondCardFlipped(Position p) {
		// TODO Auto-generated method stub
		if(super.inBounds(p )) {
			board[p.getRow()][p.getColumn()] = Content.NUMBER;
			return true;
		}
		return false;
	}

}

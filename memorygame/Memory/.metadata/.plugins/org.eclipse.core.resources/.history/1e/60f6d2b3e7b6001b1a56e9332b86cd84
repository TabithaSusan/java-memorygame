package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HiddenBoardMatrix extends BoardMatrix implements HiddenBoard {

	private Card[][] hiddenBoard;

	public HiddenBoardMatrix(int height, int width) {
		super(height, width);
		hiddenBoard = new Card[height][width];

		List<Card> list = new ArrayList<Card>(height * width);
		for (int i = 1; i <= (height * width) / 2; i++) {
			list.add(new Card(i));
			list.add(new Card(i));
		}

		Collections.shuffle(list);

		int index = 0;
		for (int row = 0; row < hiddenBoard.length; row++) {
			for (int column = 0; column < hiddenBoard[row].length; column++) {
				hiddenBoard[row][column] = list.get(index);
				index++;
			}
		}
	}

	/**
	 * Method to check if two cards are a pair.
	 * 
	 * @param position1 Position of the first card.
	 * @param position2 Position of the second card.
	 * @param playerID ID of current player.
	 * @return Are those cards a Pair?
	 */
	public boolean isMatch(Position position1, Position position2, int playerID) {
		if (!validPosition(position1, position2)) {
			return false;
		}
		Card card1 = getHiddenCardAtPosition(position1);
		Card card2 = getHiddenCardAtPosition(position2);
		if (card1.getNumber() == card2.getNumber()) {
			card1.setDiscoveredBy(playerID);
			card2.setDiscoveredBy(playerID);
			board[position1.getRow()][position1.getColumn()] = card1;
			board[position2.getRow()][position2.getColumn()] = card2;
			return true;
		}
		return false;
	}

	/**
	 * Fetch hidden at position
	 * 
	 * @param position Position of hidden Card
	 * @return Hidden card position
	 */

	public Card getHiddenCardAtPosition(Position position) {
		if (inBounds(position)) {
			return hiddenBoard[position.getRow()][position.getColumn()];
		}
		return new Card(0);
	}

	public String toStringHidden() {
		String s = "";
		for (Card[] row : hiddenBoard) {
			for (Card card : row) {
				s += card.getNumber() + "\t";
			}
			s += "\n";
		}
		return s;
	}

}

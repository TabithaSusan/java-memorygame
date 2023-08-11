package model;

public class Position {

	private int row;
	private int column;

	public Position(int row, int column) {
		this.row = row;
		this.column = column;

	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	/**
	 * Method to check if two positions are at the same spot in the Array.
	 * @param o 
	 * @return
	 */

	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position p = (Position) o;
			return p.getRow() == this.getRow() && p.getColumn() == this.getColumn();
		}
		return false;
	}
}

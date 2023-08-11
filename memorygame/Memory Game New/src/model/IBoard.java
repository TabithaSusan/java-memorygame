package model;

public interface IBoard {

	public boolean inBounds(Position p);

	public Card getCardAtPosition(Position p);

	public boolean finish();

	public int pairsFoundBy(int playerID);

	public boolean validPosition(Position position1, Position position2);
}

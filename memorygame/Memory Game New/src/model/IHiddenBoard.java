package model;

public interface IHiddenBoard extends IBoard{

	public Card getHiddenCardAtPosition(Position position);

	public boolean isMatch(Position position1, Position position2, int playerID);
}
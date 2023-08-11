package model;

public class Card {

	private int number;
	private boolean hidden;
	private int discoveredBy;

	public Card(int number) {
		this.number = number;
		this.hidden = true;
		this.discoveredBy = -1;
	}

	public int getNumber() {
		return number;
	}

	public boolean isHidden() {
		return hidden;
	}

	public int discoveredBy() {
		return discoveredBy;
	}
	
	/**
	 * Method to set a pair as discovered by playerID. Pair will be uncovered from now on.
	 * @param playerID ID of player
	 */

	public void setDiscoveredBy(int playerID) {
		discoveredBy = playerID;
		hidden = false;
	}
}

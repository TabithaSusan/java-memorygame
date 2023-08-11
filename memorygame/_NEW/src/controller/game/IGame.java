package controller.game;

import model.IBoard;

public interface IGame {

	
	/**
	 * Startet das Spiel. Die Spielbretter werden angezeigt.
	 */
	public void start();

	/**
	 * Ist der Spieler mit seiner Auswahl fertig, l�uft die Spielelogik. Das Spiel
	 * deckt beide gew�hlte Positionen aus firstSelectedPosition und
	 * secondSelectedPosition auf. Und mittels showFirstCard und showSecondCard
	 * angezeigt. Dann wird isMatch() aufgerufen. Danach wird �berpr�ft ob finish()
	 * true ist, wenn nicht, wird der andere Spieler enabled(). Ist das Spiel zu
	 * Ende, wir der jeweilige Status angezigt, win(), draw(), lose().
	 */
	public void update();

	/**
	 * Speichert das aktuelle Spiel auf der Festplatte.
	 */
	public void save();

	/**
	 * L�dt das Spiel von der Festplatte.
	 */
	public void load();

	/**
	 * Zeigt das Spielbrett an.
	 */
	public IBoard getBoard();
}

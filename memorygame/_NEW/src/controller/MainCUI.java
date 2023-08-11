package controller;

import controller.game.Game;
import controller.player.PlayerCUI;
import model.HiddenBoardMatrix;

public class MainCUI {

	public static void main(String[] args) {
		PlayerCUI player1 = new PlayerCUI();
		PlayerCUI player2 = new PlayerCUI();
		HiddenBoardMatrix board = new HiddenBoardMatrix(4,8);
		Game game = new Game(player1, player2, board);
		
		game.start();
		
	}

}

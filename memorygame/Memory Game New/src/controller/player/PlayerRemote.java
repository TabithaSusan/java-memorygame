package controller.player;

import java.net.Socket;

import controller.game.Game;
import controller.game.IGame;
import controller.net.MemorySocket;
import model.Card;
import model.Position;

public class PlayerRemote implements IPlayer {
	
	private MemorySocket socketToPlayer;
	private IGame game;
	
	public PlayerRemote(Socket socket) {
		socketToPlayer = new MemorySocket(socket);
		game = null;
	}

	
	private void listen() {
		System.out.println("Server wartet auf Nachricht");
		String s = socketToPlayer.receiveMessage();
		switch(s) {
		case "update": game.update(); break;
		case "save": game.save(); listen(); break;
		case "load": game.load(); break;
		case "sendchatmessage": game.sendChatMessage(socketToPlayer.receiveMessage()); listen(); break;
		default: listen();
		}
	}
	
	@Override
	public void initialize(IGame game, int id) {
		this.game = game;
		socketToPlayer.send("initialize");
		socketToPlayer.send(position1);
		socketToPlayer.send(position2);
		socketToPlayer.send(id);

	}

	@Override
	public void enable() {
		socketToPlayer.send("enable");
		socketToPlayer.send(game.getBoard());
		listen();

	}

	@Override
	public void disable() {
		socketToPlayer.send("disable");
		socketToPlayer.send(game.getBoard());

	}

	@Override
	public Position firstSelectedPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position secondSelectedPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showFirstCard(Card fstCard, Position fstSelectedPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showSecondCard(Card scndCard, Position scndSelectedPostion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void win() {
		socketToPlayer.send("win");

	}

	@Override
	public void draw() {
		socketToPlayer.send("draw");

	}

	@Override
	public void lose() {
		socketToPlayer.send("lose");

	}
	
	public void receiveChatMessage(String message) {
		socketToPlayer.send("receivechatmessage");
		socketToPlayer.send(message);
	}

}

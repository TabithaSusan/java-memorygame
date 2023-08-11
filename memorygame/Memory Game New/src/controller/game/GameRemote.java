package controller.game;

import java.net.Socket;

import controller.net.MemorySocket;
import controller.player.IPlayer;
import model.IBoard;

public class GameRemote implements IGame {
	
	private IPlayer player;
	private MemorySocket socketToGame;
	private IBoard board;
	private ListenService listenService;
	
	private boolean iSendChatMessage;
	
	public GameRemote(IPlayer player, Socket socket) {
		this.player = player;
		this.socketToGame = new MemorySocket(socket);
		this.board = null;
		listenService = new ListenService(socketToGame);
		listenService.setOnSucceeded(
				(WorkerStateEvent t) -> {
					String s = (String) t.getSource().getValue();
					listenService.reset();
					process(s);
				});
		this.iSendChatMessage = false;
	}

	private void listen() {
		System.out.println("Client wartet auf Nachricht");
		String s = socketToGame.receiveMessage();
		switch(s) {
		case "initialize": initialize(); break;
		case "enable": enable(); break;
		case "disable": disable(); break;
		case "place": place(); break;
		case "win": win(); break;
		case "lose": lose(); break;
		case "draw": draw(); break;
		}
	}
	
	private void process(String s) {
		switch(s) {
		case "initialize": initialize(); break;
		case "enable": enable(); break;
		case "disable": disable(); break;
		case "place": place(); break;
		case "win": win(); break;
		case "lose": lose(); break;
		case "draw": draw(); break;
		case "receivechatmessage": receiveChatMessage(); break;
		}
	}
	
	private void receiveChatMessage() {
		String message = socketToGame.receiveMessage();
		player.receiveChatMessage(message);
		if(!iSendChatMessage) {
			listenService.start();
		}
		iSendChatMessage = false;
	}
	
	private void initialize() {
		Content c = socketToGame.//TODO Anpassen an Memory();
		int id = socketToGame.receiveInt();
		player.initialize(c, this, id);
		listenService.start();
	}
	
	private void enable() {
		board = socketToGame.receiveBoard();
		player.enable();
	}
	
	private void disable() {
		board = socketToGame.receiveBoard();
		player.disable();
		listenService.start();
	}
	
	private void place() {
		socketToGame.send(player.//TODO Anpassen an Memory());
		listenService.start();
	}
	
	private void win() {
		player.win();
		socketToGame.close();
	}
	
	private void lose() {
		player.lose();
		socketToGame.close();
	}
	
	private void draw() {
		player.draw();
		socketToGame.close();
	}
	
	@Override
	public void start() {
		listenService.start();
	}

	@Override
	public void update() {
		socketToGame.send("update");
		listenService.start();
	}

	@Override
	public void save() {
		socketToGame.send("save");
	}

	@Override
	public void load() {
		socketToGame.send("load");
		listenService.start(
	}

	@Override
	public IBoard getBoard() {
		return board;
	}
	
	public void sendChatMessage(String message) {
		this.iSendChatMessage =true;
		socketToGame.send("sendchatmessage");
		socketToGame.send(message);
		listenService.start();
	}

}

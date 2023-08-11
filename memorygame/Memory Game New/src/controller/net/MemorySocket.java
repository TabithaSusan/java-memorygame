package controller.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.IBoard;

public class MemorySocket {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/**
	 * Constructor
	 * 
	 * @param socket Socket to send and receive data.
	 */
	
	public MemorySocket(Socket socket) {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Method to close the socket
	 */
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to receive Integer
	 * 
	 * @return If failed =-1 if not, gives back correct Integer
	 */
	
	public int receiveInt() {
		try {
			int id = in.readInt();
			System.out.println("Empfange ID" + id);
			return id;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//TODO Methode einfügen, welches dem receiveContent gleicht, angepasst an das Memoryspiel!
	
	
	/**
	 * Method to receive Board
	 * 
	 * @return If failed = null, if not, will return Board for game
	 */
	public IBoard receiveBoard() {
		try {
			IBoard board = (IBoard) in.readObject();
			System.out.println("Empfange Board " + board);
			return board;
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	//TODO Methode einfügen, die receivePosition gleicht, angepasst an das Memoryspiel! In diesem Fall mit zwei Positionen.
	
	
	/**
	 * Method to receive Messages
	 * 
	 * @return If failed only "" will be returned, if not, the message will be returned.
	 */
	public String receiveMessage() {
		try {
			String message = (String) in.readObject();
			System.out.println("Empfange Nachricht: " + message);
			return message;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * Method to send ID
	 * 
	 * @param id ID to send
	 */
	public void send(int id) {
		System.out.println("Sende ID " + id);
		try {
			out.writeInt(id);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO Methode einfügen, die send(Content c) gleicht, anngepasst an das Memoryspiel.
	
	
	/**
	 * Method to send Board
	 * 
	 * @param board Board to send
	 */
	public void send(IBoard board) {
		System.out.println("Sende Board " + board);
		try {
			out.reset();
			out.writeObject(board);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO Methode einfügen, die send(Position position) gleicht, anngepasst an das Memoryspiel.
	
	public void send(String message) {
		System.out.println("Sende Nachricht "+ message);
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}

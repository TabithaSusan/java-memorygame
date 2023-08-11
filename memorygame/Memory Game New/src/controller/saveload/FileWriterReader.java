package controller.saveload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.PersistenceObject;

public class FileWriterReader {
	
	/**
	 * Method to save current complete Board to file board.ser via PersistenceObject
	 * 
	 * @param PersistenceObject for saving
	 */
	public static void writeToFile(PersistenceObject po) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("board.ser"));
			oos.writeObject(po);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Reads PersistenceObject from file board.ser to load game
	 * 
	 * @return if success PersistenceObject will be loaded, of not null
	 */
	
	public static PersistenceObject readFromFile() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("board.ser"));
			Object o = ois.readObject();
			ois.close();
			PersistenceObject po = (PersistenceObject) o;
			return po;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch  (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}

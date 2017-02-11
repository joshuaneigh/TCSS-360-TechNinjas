package view;

import java.io.IOException;
import java.net.ServerSocket;

import model.exception.DuplicateInstanceException;

/**
 * 
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 10 Feb 2017
 */
public class Main {

	private static final int PORT = 12345;
	private static final ServerSocket SOCKET;
	
	static {
		try {
			SOCKET = new ServerSocket(PORT);
		} catch (IOException e) {
			throw new DuplicateInstanceException("Another instance is already running.");
		}
	}
	
	public static void main(final String[] args) {
		new Runnable() {
			@Override
			public void run() {
				new TextUI().launch();
				try {
					SOCKET.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
	
}

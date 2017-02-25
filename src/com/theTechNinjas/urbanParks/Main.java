package com.theTechNinjas.urbanParks;

import java.io.IOException;
import java.net.ServerSocket;

import com.theTechNinjas.urbanParks.model.exception.DuplicateInstanceException;
import com.theTechNinjas.urbanParks.view.TextUI;

public final class Main {

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
				TextUI.launch();
				try {
					SOCKET.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
	
}

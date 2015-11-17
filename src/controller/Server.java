package controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

	private ServerListner listner;
	private Party party;

	public Server(ServerListner listner) {
		this.listner = listner;
	}

	public void connect(Party party) {
		this.party = party;
		System.out.println("Jogadores Conectados: " + party.toString());
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
	}

	public void disconnect() {
		System.out.println("Jogadores Desconectados: " + party.toString());
		listner.onDisconnect(this);
	}

}

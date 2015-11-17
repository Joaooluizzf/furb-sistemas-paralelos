package controller;

import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerManager implements ServerListner {

	private Vector<Server> servers = new Vector<>(Constantes.SERVERS_MAX_NUMBER);
	private int ocupados;

	private final Lock lock = new ReentrantLock();

	private final Condition notFull = lock.newCondition();

	public ServerManager() {
		int i = 0;
		while (i < Constantes.SERVERS_MAX_NUMBER) {
			servers.addElement(new Server(this));
			i++;
		}
	}

	public void connect(Party party) {
		lock.lock();

		try {
			while (ocupados == Constantes.SERVERS_MAX_NUMBER) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Server server = servers.firstElement();
			new Thread(() -> server.connect(party)).start();
			servers.remove(0);
			ocupados++;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void onDisconnect(Server server) {
		lock.lock();
		try {
			servers.addElement(server);
			ocupados--;
			notFull.signal();
		} finally {
			lock.unlock();
		}

	}
}
package controller;

public class QueueManager implements PartyListner {

	ServerManager serverManager = new ServerManager();
	private PartyMaker partyMaker;

	public QueueManager() {
		partyMaker = new PartyMaker(this);
	}

	public synchronized void addPlayer(String player) {
		try {
			while (partyMaker.isFull()) {
				wait();
			}

			partyMaker.addPlayer(player);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void connect(Party party) {
		new Thread(() -> serverManager.connect(party)).start();
		notify();
	}

}
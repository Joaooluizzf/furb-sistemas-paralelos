package controller;

import java.util.ArrayList;
import java.util.List;

public class PartyMaker {

	PartyListner listner;
	private List<String> party = new ArrayList<>(Constantes.SERVER_CAPACITY);

	public PartyMaker(PartyListner listner) {
		this.listner = listner;
	}

	public synchronized void addPlayer(String player) {
		party.add(player);
		System.out.println("Player " + player + " entrou para o grupo. " + party.toString());
		process();
	}

	public synchronized void process() {
		if (party.size() == Constantes.SERVER_CAPACITY) {
			System.out.println("Grupo esperando servidor: " + party.toString() + ".");
			listner.connect(new Party(party));
			party = new ArrayList<>(Constantes.SERVER_CAPACITY);
		}
	}

	public synchronized boolean isFull() {
		return !(party.size() < Constantes.SERVER_CAPACITY);
	}

}

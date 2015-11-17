package controller;

import java.util.List;

public class Party {

	private List<String> players;

	public Party(List<String> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return players.toString();
	}
}

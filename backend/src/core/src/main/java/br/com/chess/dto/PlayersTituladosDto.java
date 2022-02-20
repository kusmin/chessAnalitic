package br.com.chess.dto;

import java.util.List;

public class PlayersTituladosDto {
    private List<String> players;

    public PlayersTituladosDto() {
        // Construtor padrao
    }

    public PlayersTituladosDto(List<String> players) {
        this.players = players;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}

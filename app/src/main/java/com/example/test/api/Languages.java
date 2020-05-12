package com.example.test.api;

public class Languages {
    private final String choice;
    private final int votes;

    public Languages(String choice, int votes) {
        this.choice = choice;
        this.votes = votes;
    }

    public String getChoice() {
        return choice;
    }

    public int getVotes() {
        return votes;
    }
}

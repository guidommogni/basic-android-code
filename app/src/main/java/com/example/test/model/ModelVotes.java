package com.example.test.model;

import com.example.test.api.Languages;

import java.util.Objects;

public class ModelVotes {
    private final String language;
    private final int votes;

    public ModelVotes(String language, int votes) {
        this.language = language;
        this.votes = votes;
    }

    public static ModelVotes getFromDto(final Languages dto) {
        return new ModelVotes(dto.getChoice(), dto.getVotes());
    }

    public String getLanguage() {
        return language;
    }

    public int getVotes() {
        return votes;
    }

    public boolean isAValidModel() {
        return language != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelVotes that = (ModelVotes) o;
        return votes == that.votes &&
                Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, votes);
    }
}

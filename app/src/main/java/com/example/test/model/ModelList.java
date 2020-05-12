package com.example.test.model;

import androidx.annotation.VisibleForTesting;

import com.example.test.api.Languages;
import com.example.test.api.MyList;

import java.util.LinkedList;
import java.util.List;

public class ModelList {
    private final String title;
    private final List<ModelVotes> votes;

    public ModelList(String title, List<ModelVotes> votes) {
        this.title = title;
        this.votes = votes;
    }

    public static ModelList getFromDto(final MyList list) {
        final List<ModelVotes> votes = new LinkedList<>();
        if (list.getLanguages() != null && !list.getLanguages().isEmpty()) {
            for (final Languages languages: list.getLanguages()) {
                final ModelVotes modelVotes = ModelVotes.getFromDto(languages);
                if (modelVotes.isAValidModel()) {
                    votes.add(ModelVotes.getFromDto(languages));
                }
            }
        }
        return new ModelList(list.getTitle(), votes);
    }

    public String getTitle() {
        return title;
    }

    public List<ModelVotes> getVotes() {
        return votes;
    }

    @VisibleForTesting
    public static class Builder {

        private String title;
        private List<ModelVotes> votes;

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withLanguages(final List<ModelVotes> languages) {
            this.votes = languages;
            return this;
        }

        public ModelList build() {
            return new ModelList(title, votes);
        }
    }
}

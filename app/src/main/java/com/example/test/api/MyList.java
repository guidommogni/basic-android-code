package com.example.test.api;

import androidx.annotation.VisibleForTesting;

import java.util.List;

public class MyList {
    private final String title;
    private final String description;
    private final List<Languages> languages;

    public MyList(String title, String description, List<Languages> languages) {
        this.title = title;
        this.description = description;
        this.languages = languages;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    @VisibleForTesting
    public static class Builder {

        private String title;
        private String description;
        private List<Languages> votes;

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withLanguages(final List<Languages> languages) {
            this.votes = languages;
            return this;
        }

        public MyList build() {
            return new MyList(title, description, votes);
        }
    }
}

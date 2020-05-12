package com.example.test.model;

import com.example.test.api.Languages;
import com.example.test.api.MyList;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelListTest {

    private static final Languages FAKE = new Languages("asd", 1);

    @Test
    public void givenAValidDtoThenReturnValidModel() {
        final ModelList list = ModelList.getFromDto(getValidDto());

        assertEquals(2, list.getVotes().size());
        assertEquals("title", list.getTitle());
    }

    @Test
    public void givenNullVotesListThenReturnEmptyList() {
        final ModelList list = ModelList.getFromDto(getNullList());

        assertEquals(0, list.getVotes().size());
        assertEquals("title", list.getTitle());
    }

    private MyList getValidDto() {
        final List<Languages> languages = new LinkedList<>();
        languages.add(FAKE);
        languages.add(FAKE);
        return new MyList("title", "asd", languages);
    }

    private MyList getNullList() {
        return new MyList("title", "asd", null);
    }
}

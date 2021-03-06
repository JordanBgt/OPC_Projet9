package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class JournalComptableTest {

    @Test
    public void getByCode() {
        // GIVEN
        JournalComptable journalComptable1 = new JournalComptable("JC1", "Journal comptable 1");
        JournalComptable journalComptable2 = new JournalComptable("JC2", "Journal comptable 2");
        JournalComptable journalComptable3 = new JournalComptable("JC3", "Journal comptable 3");
        JournalComptable journalComptable4 = new JournalComptable("JC4", "Journal comptable 4");
        List<JournalComptable> journalComptableList =
                Stream.of(journalComptable1, journalComptable2, journalComptable3, journalComptable4)
                .collect(Collectors.toList());

        // WHEN
        JournalComptable result = JournalComptable.getByCode(journalComptableList, "JC2");

        // THEN
        assertThat(result).isEqualTo(journalComptable2);
    }

    @Test
    public void getByCodeWhenListHasNullValues() {
        // GIVEN
        JournalComptable journalComptable1 = new JournalComptable("JC1", "Journal comptable 1");
        List<JournalComptable> journalComptableList = Stream.of(null, journalComptable1, null).collect(Collectors.toList());

        // WHEN
        JournalComptable result = JournalComptable.getByCode(journalComptableList, "JC2");

        // THEN
        assertThat(result).isNull();
    }

}

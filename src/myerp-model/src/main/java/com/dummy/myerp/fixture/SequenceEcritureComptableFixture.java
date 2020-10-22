package com.dummy.myerp.fixture;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;

public class SequenceEcritureComptableFixture {

    public static SequenceEcritureComptable buildSequenceEcritureComptable() {
        return new SequenceEcritureComptable(2016, 51, "BQ");
    }
}

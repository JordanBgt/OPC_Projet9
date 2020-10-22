package com.dummy.myerp.fixture;

import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;

import java.sql.Date;
import java.time.Instant;

public class EcritureComptableFixture {

    public static EcritureComptable buildEcritureComptable() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setReference("AC-2020/00001");
        ecritureComptable.setLibelle("libelle");
        ecritureComptable.setDate(Date.from(Instant.now()));
        ecritureComptable.setJournal(JournalComptableFixture.buildJournalComptableAchat());
        return ecritureComptable;
    }
}

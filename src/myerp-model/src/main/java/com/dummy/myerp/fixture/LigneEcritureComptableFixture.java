package com.dummy.myerp.fixture;

import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LigneEcritureComptableFixture {

    public static List<LigneEcritureComptable> buildListLigneEcritureComptable() {
        return Stream.of(new LigneEcritureComptable(CompteComptableFixture.buildCompteComptableClients(), "libelle", new BigDecimal("100.00"), null),
                new LigneEcritureComptable(CompteComptableFixture.buildCompteComptableClients(), "libelle", null, new BigDecimal("100.00")))
                .collect(Collectors.toList());
    }

    public static LigneEcritureComptable buildLigneEcritureComptableDebit() {
        return new LigneEcritureComptable(CompteComptableFixture.buildCompteComptableClients(), "libelle", new BigDecimal("100.00"), null);
    }

    public static LigneEcritureComptable buildLigneEcritureComptableCredit() {
        return new LigneEcritureComptable(CompteComptableFixture.buildCompteComptableClients(), "libelle", null, new BigDecimal("100.00"));
    }

}

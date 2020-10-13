package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CompteComptableTest {

    @Test
    public void getByNumero() {
        CompteComptable compteComptable1 = new CompteComptable(1, "CC1");
        CompteComptable compteComptable2 = new CompteComptable(2, "CC2");
        CompteComptable compteComptable3 = new CompteComptable(3, "CC3");
        CompteComptable compteComptable4 = new CompteComptable(4, "CC4");
        List<CompteComptable> compteComptableList =
                Stream.of(compteComptable1, compteComptable2, compteComptable3, compteComptable4)
                .collect(Collectors.toList());
        CompteComptable result = CompteComptable.getByNumero(compteComptableList, 3);
        assertThat(result).isEqualTo(compteComptable3);
    }
}

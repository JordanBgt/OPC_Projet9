package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.fixture.CompteComptableFixture;
import com.dummy.myerp.fixture.JournalComptableFixture;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.junit.Test;
import org.springframework.transaction.TransactionStatus;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ComptabiliteManagerImplIT extends BusinessTestCase {

    private ComptabiliteManagerImpl comptabiliteManager = new ComptabiliteManagerImpl();

    @Test
    public void getListCompteComptable() {
        // GIVEN
        List<CompteComptable> compteComptableList = CompteComptableFixture.buildListCompteComptable();

        // WHEN
        List<CompteComptable> results = comptabiliteManager.getListCompteComptable();

        // THEN
        assertThat(results).isEqualTo(compteComptableList);
    }

    @Test
    public void getListJournalComptable() {
        // GIVEN
        List<JournalComptable> journalComptableList = JournalComptableFixture.buildJournalComptableList();

        // WHEN
        List<JournalComptable> results = comptabiliteManager.getListJournalComptable();

        // THEN
        assertThat(results).isEqualTo(journalComptableList);
    }

    @Test
    public void getListEcritureComptable() {
        //WHEN
        List<EcritureComptable> results = comptabiliteManager.getListEcritureComptable();

        // THEN
        assertThat(results.size()).isEqualTo(5);
    }

    @Test
    public void addReferenceWhereYearIs2016AndJournalCodeIsAC() throws Exception {
        // GIVEN
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyy");
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setJournal(JournalComptableFixture.buildJournalComptableAchat());
        ecritureComptable.setDate(formatter.parse("22-10-2016"));
        ecritureComptable.setLibelle("libelle");

        // WHEN
        TransactionStatus ts = getTransactionManager().beginTransactionMyERP();
        comptabiliteManager.addReference(ecritureComptable);

        // THEN
        assertThat(ecritureComptable.getReference()).isEqualTo("AC-2016/00041");
        // TODO : voir comment invoquer getDaoProxy() pour vérifier que la valeur est bien incrémentée
        getTransactionManager().rollbackMyERP(ts);
    }

    @Test
    public void addReferenceWhereYearIs2020AndJournalCodeIsBQ() throws Exception {
        // GIVEN
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setJournal(JournalComptableFixture.buildJournalComptableBanque());
        ecritureComptable.setDate(Date.from(Instant.now()));
        ecritureComptable.setLibelle("libelle");

        // WHEN
        TransactionStatus ts = getTransactionManager().beginTransactionMyERP();
        comptabiliteManager.addReference(ecritureComptable);

        // THEN
        assertThat(ecritureComptable.getReference()).isEqualTo("BQ-2020/00001");
        getTransactionManager().rollbackMyERP(ts);
    }


    // TODO : voir comment tester les 3 dernières méthodes vu qu'on a pas accés au getDaoProxy
}

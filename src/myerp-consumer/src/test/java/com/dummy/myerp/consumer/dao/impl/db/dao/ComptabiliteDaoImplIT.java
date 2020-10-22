package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.fixture.*;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ComptabiliteDaoImplIT extends ConsumerTestCase {

    private ComptabiliteDaoImpl comptabiliteDao = new ComptabiliteDaoImpl();

    @Test
    public void getListCompteComptable() {
        // GIVEN
        List<CompteComptable> compteComptableList = CompteComptableFixture.buildListCompteComptable();

        // WHEN
        List<CompteComptable> results = comptabiliteDao.getListCompteComptable();

        // THEN
        assertThat(results).containsExactlyInAnyOrderElementsOf(compteComptableList);
    }

    @Test
    public void getListJournalComptable() {
        // GIVEN
        List<JournalComptable> journalComptableList = JournalComptableFixture.buildJournalComptableList();

        // WHEN
        List<JournalComptable> results = comptabiliteDao.getListJournalComptable();

        // THEN
        assertThat(results).containsExactlyInAnyOrderElementsOf(journalComptableList);
    }

    @Test
    public void getListEcritureComptable() {
        // WHEN
        List<EcritureComptable> results = comptabiliteDao.getListEcritureComptable();

        // THEN
        assertThat(results.size()).isEqualTo(5);
    }

    @Test
    public void getEcritureComptable() throws NotFoundException {
        // GIVEN
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(-1);
        ecritureComptable.setLibelle("Cartouches d’imprimante");
        ecritureComptable.setReference("AC-2016/00001");

        // WHEN
        EcritureComptable result = comptabiliteDao.getEcritureComptable(-1);

        // THEN
        assertThat(result.getId()).isEqualTo(ecritureComptable.getId());
        assertThat(result.getReference()).isEqualTo(ecritureComptable.getReference());
        assertThat(result.getLibelle()).isEqualTo(ecritureComptable.getLibelle());
    }

    @Test
    public void getEcritureComptableThrowsNotFoundException() {
        assertThatThrownBy(() -> comptabiliteDao.getEcritureComptable(187)).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void getEcritureComptableByRef() throws NotFoundException {
        // GIVEN
        EcritureComptable ecritureComptable = EcritureComptableFixture.buildEcritureComptable();
        ecritureComptable.setReference("VE-2016/00002");
        ecritureComptable.setId(-2);

        // WHEN
        EcritureComptable result = comptabiliteDao.getEcritureComptableByRef(ecritureComptable.getReference());

        // THEN
        assertThat(result.getReference()).isEqualTo(ecritureComptable.getReference());
        assertThat(result.getId()).isEqualTo(ecritureComptable.getId());
    }

    @Test
    public void getEctireComptableByRefThrowsNotFoundException() {
        assertThatThrownBy(() -> comptabiliteDao.getEcritureComptableByRef("AC-2020/00432")).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void loadListLigneEcriture() {
        // GIVEN
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(-1);

        // WHEN
        comptabiliteDao.loadListLigneEcriture(ecritureComptable);

        // THEN
        assertThat(ecritureComptable.getListLigneEcriture().size()).isEqualTo(3);
    }

    @Test
    public void insertEcritureComptable() {
        // GIVEN
        EcritureComptable ecritureComptable = EcritureComptableFixture.buildEcritureComptable();

        // WHEN
        comptabiliteDao.insertEcritureComptable(ecritureComptable);

        // THEN
        assertThat(ecritureComptable.getId()).isNotNull();

        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId()); // TODO : voir si on peut faire un rollback
    }

    @Test
    public void insertListLigneEcritureComptable() {
        // GIVEN
        EcritureComptable ecritureComptable = EcritureComptableFixture.buildEcritureComptable();
        comptabiliteDao.insertEcritureComptable(ecritureComptable);
        List<LigneEcritureComptable> ligneEcritureComptableList = LigneEcritureComptableFixture.buildListLigneEcritureComptable();
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(0));
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(1));

        // WHEN
        comptabiliteDao.insertListLigneEcritureComptable(ecritureComptable);

        // THEN
        comptabiliteDao.loadListLigneEcriture(ecritureComptable);
        assertThat(ecritureComptable.getListLigneEcriture()).isEqualTo(ligneEcritureComptableList);

        comptabiliteDao.deleteListLigneEcritureComptable(ecritureComptable.getId());
        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());
    }

    @Test
    public void updateEcritureComptable() throws NotFoundException {
        // GIVEN
        EcritureComptable ecritureComptable = EcritureComptableFixture.buildEcritureComptable();
        comptabiliteDao.insertEcritureComptable(ecritureComptable);
        List<LigneEcritureComptable> ligneEcritureComptableList = LigneEcritureComptableFixture.buildListLigneEcritureComptable();
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(0));
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(1));
        comptabiliteDao.insertListLigneEcritureComptable(ecritureComptable);

        ecritureComptable.setJournal(JournalComptableFixture.buildJournalComptableBanque());
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(0));
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(1));

        // WHEN
        comptabiliteDao.updateEcritureComptable(ecritureComptable);

        // THEN
        EcritureComptable result = comptabiliteDao.getEcritureComptable(ecritureComptable.getId());
        comptabiliteDao.loadListLigneEcriture(ecritureComptable);
        assertThat(result).isEqualToIgnoringGivenFields(ecritureComptable, "date"); // On ignore le champ date car il n'a pas le même format dans la réponse de la bdd

        comptabiliteDao.deleteListLigneEcritureComptable(ecritureComptable.getId());
        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());
    }

    @Test
    public void deleteEcritureComptable() throws NotFoundException {
        // GIVEN
        EcritureComptable ecritureComptable = EcritureComptableFixture.buildEcritureComptable();
        comptabiliteDao.insertEcritureComptable(ecritureComptable);

        // WHEN
        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());

        // THEN
        assertThatThrownBy(() -> comptabiliteDao.getEcritureComptable(ecritureComptable.getId())).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void deleteListLigneEcritureComptable() {
        // GIVEN
        EcritureComptable ecritureComptable = EcritureComptableFixture.buildEcritureComptable();
        List<LigneEcritureComptable> ligneEcritureComptableList = LigneEcritureComptableFixture.buildListLigneEcritureComptable();
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(0));
        ecritureComptable.getListLigneEcriture().add(ligneEcritureComptableList.get(1));
        comptabiliteDao.insertEcritureComptable(ecritureComptable);

        // WHEN
        comptabiliteDao.deleteListLigneEcritureComptable(ecritureComptable.getId());

        // THEN
        comptabiliteDao.loadListLigneEcriture(ecritureComptable);
        assertThat(ecritureComptable.getListLigneEcriture().size()).isZero();

        comptabiliteDao.deleteEcritureComptable(ecritureComptable.getId());
    }

    @Test
    public void getLastSequenceEcritureComptableByJournalCodeAndAnnee() throws NotFoundException {
        // GIVEN
        SequenceEcritureComptable sequenceEcritureComptable = SequenceEcritureComptableFixture.buildSequenceEcritureComptable();

        // WHEN
        SequenceEcritureComptable result =
                comptabiliteDao.getLastSequenceEcritureComptableByJournalCodeAndAnnee(sequenceEcritureComptable.getJournalCode(),
                        sequenceEcritureComptable.getAnnee());

        // THEN
        assertThat(result).isEqualTo(sequenceEcritureComptable);
    }

    @Test
    public void updateDerniereValeurSequenceEcritureComptableByJournalCode() throws NotFoundException {
        // GIVEN
        SequenceEcritureComptable sequenceEcritureComptable = SequenceEcritureComptableFixture.buildSequenceEcritureComptable();
        sequenceEcritureComptable.setDerniereValeur(62);

        // WHEN
        comptabiliteDao.updateDerniereValeurSequenceEcritureComptableByJournalCode(
                sequenceEcritureComptable.getDerniereValeur(), sequenceEcritureComptable.getJournalCode());

        // THEN
        SequenceEcritureComptable result =
                comptabiliteDao.getLastSequenceEcritureComptableByJournalCodeAndAnnee(sequenceEcritureComptable.getJournalCode(),
                        sequenceEcritureComptable.getAnnee());
        assertThat(result).isEqualTo(sequenceEcritureComptable);

        comptabiliteDao.updateDerniereValeurSequenceEcritureComptableByJournalCode(51, sequenceEcritureComptable.getJournalCode());
    }

    @Test
    public void insertSequenceEcritureComptable() throws NotFoundException {
        // GIVEN
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2020, 1, "AC");

        // WHEN
        comptabiliteDao.insertSequenceEcritureComptable(sequenceEcritureComptable);

        // THEN
        SequenceEcritureComptable result =
                comptabiliteDao.getLastSequenceEcritureComptableByJournalCodeAndAnnee(sequenceEcritureComptable.getJournalCode(),
                        sequenceEcritureComptable.getAnnee());
        assertThat(result).isEqualTo(sequenceEcritureComptable);

        comptabiliteDao.deleteSequenceEcritureComptable(sequenceEcritureComptable);
    }

    @Test
    public void deleteSequenceEcritureComptable() {
        // GIVEN
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable(2020, 1, "AC");
        comptabiliteDao.insertSequenceEcritureComptable(sequenceEcritureComptable);

        // WHEN
        comptabiliteDao.deleteSequenceEcritureComptable(sequenceEcritureComptable);

        // THEN
        assertThat(comptabiliteDao.getLastSequenceEcritureComptableByJournalCodeAndAnnee(sequenceEcritureComptable.getJournalCode(), sequenceEcritureComptable.getAnnee())).isNull();

    }
}

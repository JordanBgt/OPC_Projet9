package fixture;

import com.dummy.myerp.model.bean.comptabilite.JournalComptable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JournalComptableFixture {

    public static JournalComptable buildJournalComptableAchat() {
        return new JournalComptable("AC", "Achat");
    }

    public static JournalComptable buildJournalComptableVente() {
        return new JournalComptable("VE", "Vente");
    }

    public static JournalComptable buildJournalComptableBanque() {
        return new JournalComptable("BQ", "Banque");
    }

    public static JournalComptable buildJournalComptableOD() {
        return new JournalComptable("OD", "Opérations Diverses");
    }

    public static List<JournalComptable> buildJournalComptableList() {
        return Stream.of(new JournalComptable("AC", "Achat"),
                new JournalComptable("VE", "Vente"),
                new JournalComptable("BQ", "Banque"),
                new JournalComptable("OD", "Opérations Diverses"))
                .collect(Collectors.toList());
    }
}

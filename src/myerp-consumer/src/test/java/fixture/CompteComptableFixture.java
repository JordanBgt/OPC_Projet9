package fixture;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompteComptableFixture {

    public static List<CompteComptable> buildListCompteComptable() {
        return Stream.of(new CompteComptable(401, "Fournisseurs"),
                new CompteComptable(411, "Clients"),
                new CompteComptable(4456, "Taxes sur le chiffre d'affaires déductibles"),
                new CompteComptable(4457, "Taxes sur le chiffre d'affaires collectées par l'entreprise"),
                new CompteComptable(512, "Banque"),
                new CompteComptable(606, "Achats non stockés de matières et fournitures"),
                new CompteComptable(706, "Prestations de services"))
                .collect(Collectors.toList());
    }

    public static CompteComptable buildCompteComptableClients() {
        return new CompteComptable(411, "Clients");
    }
}

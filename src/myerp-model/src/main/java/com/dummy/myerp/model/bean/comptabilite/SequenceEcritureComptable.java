package com.dummy.myerp.model.bean.comptabilite;


import java.util.Objects;

/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

    // ==================== Attributs ====================
    /** L'année */
    private Integer annee;
    /** La dernière valeur utilisée */
    private Integer derniereValeur;
    /** Le code du journal*/
    private String journalCode;

    // ==================== Constructeurs ====================
    /**
     * Constructeur
     */
    public SequenceEcritureComptable() {
    }

    /**
     * Constructeur
     *
     * @param pAnnee -
     * @param pDerniereValeur -
     * @param pJournalCode -
     */
    public SequenceEcritureComptable(Integer pAnnee, Integer pDerniereValeur, String pJournalCode) {
        annee = pAnnee;
        derniereValeur = pDerniereValeur;
        journalCode = pJournalCode;
    }


    // ==================== Getters/Setters ====================
    public Integer getAnnee() {
        return annee;
    }
    public void setAnnee(Integer pAnnee) {
        annee = pAnnee;
    }
    public Integer getDerniereValeur() {
        return derniereValeur;
    }
    public void setDerniereValeur(Integer pDerniereValeur) {
        derniereValeur = pDerniereValeur;
    }
    public String getJournalCode() { return journalCode; }
    public void setJournalCode(String journalCode) { this.journalCode = journalCode; }

    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
            .append("annee=").append(annee)
            .append(vSEP).append("derniereValeur=").append(derniereValeur)
            .append("}");
        return vStB.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SequenceEcritureComptable that = (SequenceEcritureComptable) o;
        return Objects.equals(annee, that.annee) &&
                Objects.equals(derniereValeur, that.derniereValeur) &&
                Objects.equals(journalCode, that.journalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annee, derniereValeur, journalCode);
    }
}

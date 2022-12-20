package org.hbrs.se1.ws22.uebung8;

public class Hotelsuche {
    private ReiseAnbieterAdapter reiseAnbieterAdapter = new ReiseAnbieterAdapter();
    private SuchAuftrag suchAuftrag = new SuchAuftrag();
    private SuchErgebnis suchErgebnis = new SuchErgebnis();

    private SuchErgebnis suchen(SuchAuftrag sA) {
        suchAuftrag = sA;
        suchErgebnis = reiseAnbieterAdapter.suchen(sA);
        return suchErgebnis;
    }
}

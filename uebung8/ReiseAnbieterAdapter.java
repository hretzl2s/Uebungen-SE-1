package org.hbrs.se1.ws22.uebung8;

public class ReiseAnbieterAdapter {
    private ReiseAnbieter reiseAnbieter = new ReiseAnbieter();

    public SuchErgebnis suchen(SuchAuftrag sA) {
        //Transformation der Eingabedaten (Suchauftrag --> QueryObject)
        QueryObject oldObject = transformEingabe(sA);

        //Delegation auf das Alt-System (Legacy-Class) Hier: ReiseAnbieter
        QueryResult oldResult = reiseAnbieter.executeQuery(oldObject);

        //Transformation der Ausgabedaten (QueryResult --> SuchErgebnis)
        SuchErgebnis neuesErgebnis = transformAusgabe(oldResult);
        return neuesErgebnis;
    }

    private SuchErgebnis transformAusgabe(QueryResult oldResult) {
        return new SuchErgebnis();
    }

    private QueryObject transformEingabe(SuchAuftrag sA) {
        return new QueryObject();
    }
}

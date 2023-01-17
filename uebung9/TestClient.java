package org.hbrs.se1.ws22.uebung9;

import java.io.UnsupportedEncodingException;

public class TestClient {
    public static void main(String[] args) {
        ComplexDocument doc0 = new ComplexDocument();
        doc0.setID(1);

        TextDocument doc2 = new TextDocument("Die Klausur im Fach SE findet bald statt!", TextDocument.Encoding.UTF8);
        doc2.setID(2);

        doc0.addDocument(doc2);

        ComplexDocument doc3 = new ComplexDocument();
        doc3.setID(3);
        doc0.addDocument(doc3);

        GraficDocument doc4 = new GraficDocument("localhost:8080");
        doc4.setID(4);
        doc3.addDocument(doc4);

        TextDocument doc5 = new TextDocument("Software Engineering I ist eine Vorlesung in den Studiengaengen BIS und BCS", TextDocument.Encoding.UTF32);
        doc5.setID(5);
        doc3.addDocument(doc5);

        int ergebnis2 = 0;
        for (Document doc : doc0.documents) {
            if (doc.getClass().equals(TextDocument.class)) {
                ergebnis2 += ((TextDocument) doc).calculateBytes();
            } else if (doc.getClass().equals(GraficDocument.class)) {
                ergebnis2 += 1200;
            } else if (doc.getClass().equals(ComplexDocument.class)) {
                for (Document docTwo : ((ComplexDocument) doc).documents) {
                    if (docTwo.getClass().equals(TextDocument.class)) {
                        ergebnis2 += ((TextDocument) docTwo).calculateBytes();
                    } else if (docTwo.getClass().equals(GraficDocument.class)) {
                        ergebnis2 += 1200;
                    }
                }
            }
        }
        System.out.println(ergebnis2 + " Bytes");
    }
}

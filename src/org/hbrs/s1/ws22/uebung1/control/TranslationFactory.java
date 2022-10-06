package org.hbrs.s1.ws22.uebung1.control;

/*
 * Verwendung des Factory Method Design Pattern  (GoF)
 * Problem: Inkonsistente Objekterzeugung an potentiell n verschiedenen Stellen
 * Lösung: Bereitstellung einer zentralen Klasse zur konsistenten Objekterzeugung und -parametrisierung
 */

import java.util.Date;

public class TranslationFactory {

    public static GermanTranslator createGermanTranslator() {
        return new GermanTranslator(new Date());
    }
}

package org.hbrs.s1.ws22.uebung1.test;

import org.hbrs.s1.ws22.uebung1.control.TranslationFactory;
import org.hbrs.s1.ws22.uebung1.control.Translator;

import static org.junit.Assert.assertEquals;

class Test {

    @org.junit.jupiter.api.Test
    void testTranslation() {
        int[] testZahlen = {-500, 0, 800};
        Translator t = TranslationFactory.createGermanTranslator();

        for (int i : testZahlen) {
            assertEquals("Übersetzung der Zahl " + i + " nicht möglich " + Translator.version, t.translateNumber(i));
        }

        assertEquals("acht", t.translateNumber(8));
    }
}

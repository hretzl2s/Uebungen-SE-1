package org.hbrs.s1.ws22.uebung1.control;

import java.util.Date;

public class GermanTranslator implements Translator {

	public Date date;

	public GermanTranslator(Date pDate) {
		date = pDate;
	}

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber(int number) {
		// [ihr Source Code aus Übung 1-2]
		try {
			String[] zahlen = { "null", "eins", "zwei", "drei", "vier", "fuenf", "sechs", "sieben", "acht", "neun",
					"zehn" };

			if (number == 0) {
				throw new Exception();
			}

			return zahlen[number];
		} catch (Exception e) {
			return "Übersetzung der Zahl " + number + " nicht möglich " + version;
		}
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo() {
		System.out.println("GermanTranslator v1.9, erzeugt am " + this.date.toString());
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Wochentag Monat Tag Uhrzeit Zeitzone Jahr)
	 * (Beispiel: Thu Oct 06 15:31:59 CEST 2022) Das Datum sollte system-intern durch eine
	 * Control-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}

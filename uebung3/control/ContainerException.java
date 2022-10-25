package org.hbrs.s1.ws22.uebung3.control;

public class ContainerException extends Exception {
    public ContainerException() {
        super("Das Member-Objekt konnte nicht im Container verarbeitet werden.");
    }

  public ContainerException(String fehlermeldung) {
        super(fehlermeldung);
   }
}

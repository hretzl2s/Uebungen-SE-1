package org.hbrs.s1.ws22.uebung3.control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "C:/Eigene Dateien/Desktop/Wichtige Dokumente/Privat/Studium/Drittes Semester/SE-1/Übung 3/Ordner mit Dateien, in die Intellij schreibt/members.txt";
    private int counter;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }
    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {

    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {

    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {
            if (location.substring(location.length()-1).equals("/")) {
                throw new PersistenceException(PersistenceException.ExceptionType.FileNotFound, "Das Ende des Dateipfads darf nicht mit einem '/' enden!");
            }
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(member);

            oos.close();
            fos.close();
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FailedToSafe, "Beim Speichern der Member ist ein Fehler aufgetreten!");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-)!
     */
    public List<E> load() throws PersistenceException  {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)

        ObjectInputStream ois;
        FileInputStream fis;
        List<E> result = new ArrayList<E>();

        try {
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                result = (List) obj;
                return result;
            }

            fis.close();
            ois.close();
            return result;
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.FailedToLoad, "Beim Laden der Member aus dem Speicher ist ein Fehler aufgetreten.");
        }
    }
}

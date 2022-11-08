package org.hbrs.se1.ws22.uebung4.control;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von Mitarbeitern (engl. Employees).
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2022, Version 1.2
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse Employee
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: Employee)
 * - Anpassen der Methodennamen
 *
 * (Was ist ihre Strategie zur Wiederverwendung?)
 * - Anlegen der Klasse Employee
 * - Anpassen der Generic in der List-Klasse (ALT: Member, NEU: E)
 * - Keine Anpassung der Methodennamen
 */

public class Container{
	 
	// Interne ArrayList zur Abspeicherung der Objekte vom Type Employee
	private List<Employee> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Todo (Bewertung Thread-Safe) --> Das stimmt.
	// Nachteil dieser Variante:
	// Todo (Nachteile der Variante) --> Keine Nachteile
	private static Container instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "allemployees1.txt";

	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * Hier muss der Zugriffsoperator auf private gestellt werden, da der Konstruktor von außen nicht aufrufbar sein darf, sondern nur von der Methode getInstance()
	 */
	private Container(){
		liste = new ArrayList<>();
	}
	
	/**
	 * Start-Methoden zum Starten des Programms 
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws Exception {
		Container con = Container.getInstance();
		con.startEingabe(); 
	}
	
	/*
	 * Diese Methode realisiert eine Eingabe ueber einen Scanner
	 * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
	 * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
	 */
	public void startEingabe() throws ContainerException, Exception {

		String strInput = null;

		// Initialisierung des Eingabe-View
		Scanner scanner = new Scanner( System.in );

		// Ausgabe eines Texts zur Begruessung
		System.out.println("Employee-Tool V1.2 by Julius P. (dedicated to all my friends) & changed by Henry Retzlaff");

		while ( true ) {
			System.out.print( "> "  );

			strInput = scanner.nextLine();

			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");

			// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if (strings[0].equals("help")) {
				System.out.println("Folgende Befehle stehen zur Verfuegung: enter, store, load, dump, search, exit, help....");
			}
			// Auswahl der bisher implementierten Befehle:

			if (strings[0].equals("dump")) {
				startAusgabe();
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("enter") ) {
				Employee neuerMitarbeiter = new Employee();

				System.out.println("Geben Sie die gewünschte ID ein: ");
				neuerMitarbeiter.setPid(scanner.nextInt());
				scanner.nextLine();

				System.out.println("Geben Sie den Vornamen ein: ");
				neuerMitarbeiter.setVorname(scanner.nextLine());

				System.out.println("Geben Sie den Nachname ein: ");
				neuerMitarbeiter.setName(scanner.nextLine());

				System.out.println("Geben Sie die Abteilung ein: ");
				neuerMitarbeiter.setAbteilung(scanner.nextLine());

				System.out.println("Geben Sie die Rolle ein: ");
				neuerMitarbeiter.setRolle(scanner.next());
				scanner.nextLine();

				// UG: Expertisen bei der Eingabe durch ein Komma trennen
				System.out.println("Geben Sie die Expertise(n) ein: ");
				String expertisen = scanner.nextLine();

				String[] expertisenArray = expertisen.replace(" ", "").split(",", 0);

				List<String> ergebnis = new ArrayList<>();
				for (String expertise: expertisenArray) {
					ergebnis.add(expertise);
				}
				neuerMitarbeiter.addExpertisenAsList(ergebnis);
				this.addEmployee(neuerMitarbeiter);
			}

			if (strings[0].equals("store")) {
				try {
					this.store();
				} catch (Exception e) {
					throw new ContainerException("Fehler beim Abspreichern der Employee-Elemente");
				}
			}

			if(strings[0].equals("load")) {
				if (strings[1].equals("merge")) {
					try {
						load(true);
						System.out.println("Die Employee-Elemente wurden aus dem Speicher mit den bereits vorhanden Elementen gemerged.");
					} catch (Exception e) {
						throw new ContainerException("Fehler beim Laden der Employee-Elemente");
					}
				}

				if (strings[1].equals("force")) {
					try {
						load(false);
						System.out.println("Die bereits vorhanden Employee-Elemente wurden mit den Elementen aus dem Speicher überschrieben.");
					} catch (Exception e) {
						throw new ContainerException("Fehler beim Laden der Employee-Elemente");
					}
				}
			}

			if(strings[0].equals("search")) {
				search(strings[1]);
			}

			if(strings[0].equals("exit")) {
				scanner.close();
				System.out.println("Anwendung wurde geschlossen. Auf Wiedersehen!");
				return;
			}

			if(strings[0].equals("clear")) {
				this.liste = new ArrayList<>();
			}

		} // Ende der Schleife
	}

	private void search(String expertise) {
		for (Employee e: this.liste) {
			List<String> expertisen = e.getExpertisen();
			if (expertisen.contains(expertise)) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter

		// [Sortierung ausgelassen]
		Collections.sort(this.liste);

		// Klassische Ausgabe ueber eine For-Each-Schleife
		//for (Employee e : liste) {
		//	System.out.println(e.toString());
		//}

		// [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
		//  Gerne auch mit Beachtung der neuen US1
		// (Filterung Abteilung = "ein Wert (z.B. Marketing)"

		//Loesung Nr.1
		//liste.forEach(new Consumer<Employee>() {
		//	@Override
		//	public void accept(Employee employee) {
		//		System.out.println(employee.toString());
		//	}
		//});

		//Loesung Nr. 2
		liste.parallelStream().forEach( employee -> System.out.println(employee.toString()));

		//Loesung Nr. 3
		//List<Employee> newList =  liste.stream() //Parallelisierung hier moeglich mit .parallelStream()
		//		.filter(employee -> employee.getAbteilung().equals("Marketing"))
		//		.collect(Collectors.toList());

	}

	/**
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten Employee-Objekte gespeichert.
	 *
	 */
	public void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);

			oos.writeObject( this.liste );
			System.out.println( this.size() + " Employee wurde(n) erfolgreich gespeichert!");
		}
		catch (IOException e) {
			e.printStackTrace();
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility")
		  throw new ContainerException("Fehler beim Abspeichern");
		}
	}

	/**
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten Employee-Objekte geladen.
	 *
	 */
	public void load(boolean merge) {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		//Hier besser die Oberklasse 'Exception' verwenden und über den Parameter e, die Message bzw. den Typ der Exception auslesen
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);

		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  if (merge) {
				  List<Employee> hilfsListe = (List) obj;
				  for (Employee e1: hilfsListe) {
					  if (!contains(e1)) {
						  this.liste.add(e1);
					  }
				  }
			  } else {
				  this.liste = (List) obj;
			  }
		  }
		  System.out.println("Es wurden " + this.size() + " Mitarbeiter erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param employee
	 * @throws ContainerException
	 */
	public void addEmployee ( Employee employee ) throws ContainerException {
		if (contains(employee)) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(employee);
	}

	/**
	 * Prüft, ob ein Employee bereits vorhanden ist
	 * @param employee
	 * @return
	 */
	private boolean contains(Employee employee) {
		int ID = employee.getPid();
		for ( Employee emp : liste) {
			if ( emp.getPid() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen Employee-Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<Employee> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert einen bestimmten Employee zurück
	 * @param id
	 * @return
	 */
	private Employee getEmployee(int id) {
		for ( Employee employee : liste) {
			if (id == employee.getPid() ){
				return employee;
			}
		}
		return null;
	}
}

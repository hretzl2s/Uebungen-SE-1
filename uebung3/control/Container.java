package org.hbrs.s1.ws22.uebung3.control;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList();
    private PersistenceStrategy persistenceStrategy;
    private static Container instance = new Container(); //Instanz gemaeß dem Singleton-Pattern

    public void addMember(Member member) throws ContainerException {
        if (member == null) {
            throw new ContainerException("Das Member-Objekt darf nicht null sein!");
        }

        for (Member m : members) {
            if (m.getID().equals(member.getID())) {
                throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
            }
        }
        members.add(member);
    }

    /*
    Die Fehlermeldung sollte hier besser nicht über die Rückgabe angezeigt bzw. zurückgegeben werden,
    weil die Fehlermeldung sonst leicht übersehen oder vielleicht gar nicht als Fehler angesehen wird,
    da keine Exception geworfen wurde. Daher sollte man hier - wie bei addMember auch - eine Exception werfen.
     */
    public String deleteMember(Integer id) {
        Member toBeRemoved = null;
        boolean found = false;
        for (Member m: members) {
            if(m.getID().equals(id)) {
                toBeRemoved = m;
                found = true;
                break;
            }
        }
        if (found == true) {
            members.remove(toBeRemoved);
            return "Member mit ID " + id + " wurde entfernt!";
        }
        return "Im Container gibt es kein Member-Objekt mit der angegebenen ID " + id;
    }

    public int size() {
        int counter = 0;
        for (Member m: members) {
            counter++;
        }
        return counter;
    }

    /*
    Methode für das einmalige Erstellen eines Container-Objekts nach dem Singleton-Pattern
    */
    public static Container getInstance() {
        return instance;
    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy) {
        this.persistenceStrategy = persistenceStrategy;
    }

    public void store() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es ist keine PersistenceStrategy gesetzt!");
        }
        persistenceStrategy.save(members);
    }

    public void load() throws PersistenceException {
        if (persistenceStrategy == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Es ist keine PersistenceStrategy gesetzt!");
        }

        if (!members.isEmpty()) {
            members.clear();
        }
        this.members = persistenceStrategy.load();
    }

    public List<Member> getCurrentList() {
        return this.members;
    }
}

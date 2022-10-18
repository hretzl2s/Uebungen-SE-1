package org.hbrs.s1.ws22.uebung2;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> members = new ArrayList();

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

    public void dump() {
        for (Member m: members) {
            System.out.println(m.toString());
        }
    }

    public int size() {
        int counter = 0;
        for (Member m: members) {
            counter++;
        }
        return counter;
    }
}

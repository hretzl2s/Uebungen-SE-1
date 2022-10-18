package org.hbrs.s1.ws22.uebung2;

public class ConcreteMember implements Member{

    public Integer id;

    public ConcreteMember(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + this.id + " )";
    }
}

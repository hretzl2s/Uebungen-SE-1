package org.hbrs.s1.ws22.uebung3.control;

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {

    public final Integer id;

    public ConcreteMember(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Member (ID = " + this.id + ")";
    }
}

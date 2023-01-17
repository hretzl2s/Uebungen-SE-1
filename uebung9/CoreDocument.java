package org.hbrs.se1.ws22.uebung9;

public abstract class CoreDocument implements Document{
    protected int id;

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }
}

package org.hbrs.se1.ws22.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document{
    protected List<Document> documents = new ArrayList<Document>();
    private int id;

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public void removeDocument(int id) {
        for (Document document : documents) {
            if (id == document.getID()) {
                documents.remove(document);
                return;
            }
        }
    }
}

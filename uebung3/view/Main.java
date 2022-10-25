package org.hbrs.s1.ws22.uebung3.view;

import org.hbrs.s1.ws22.uebung3.control.*;

public class Main {
    public static void init(int anzahlMember) throws ContainerException {
        Container c = Container.getInstance();
        c.setPersistenceStrategy(new PersistenceStrategyStream<>());
        MemberView mv = new MemberView();
        try {
            for (int i=0; i<anzahlMember; i++) {
                c.addMember(new ConcreteMember(i));
            }
            mv.dump(c.getCurrentList());
        } catch (ContainerException e) {
            throw new ContainerException(e.getMessage());
        }
    }
}

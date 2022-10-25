package org.hbrs.s1.ws22.uebung3.test;

import org.hbrs.s1.ws22.uebung3.control.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    static Container memberContrainer;
    static PersistenceStrategyStream pps;

    @BeforeEach
    void beforeEach() {
        memberContrainer = Container.getInstance();
    }

    @AfterEach
    void afterEach() {
        pps = null;
    }

    @Test
    void fehlerhafteLocationDerFile() {
        pps = new PersistenceStrategyStream<>();
        pps.setLocation("C:/Eigene Dateien/Desktop/");
        memberContrainer.setPersistenceStrategy(pps);
        PersistenceException thrown = assertThrows(
                PersistenceException.class,
                () -> memberContrainer.store(),
                "Persistence hätte geschmissen werden sollen, wurde sie aber nicht!"
        );
    }

    @Test
    void verwendungMongoDB() {
        memberContrainer.setPersistenceStrategy(new PersistenceStrategyMongoDB<>());
        UnsupportedOperationException thrown = assertThrows(
                UnsupportedOperationException.class,
                () -> memberContrainer.store(),
                "UnsupportedOperationException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );
    }

    @Test
    void strategieNichtVonAußenSetzen() {
        pps = null;
        memberContrainer.setPersistenceStrategy(pps);
        PersistenceException thrown = assertThrows(
                PersistenceException.class,
                () -> memberContrainer.store(),
                "PersitenceException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );
    }

    @Test
    void generelleFunktionalitaet() throws ContainerException {
        try {
            pps = new PersistenceStrategyStream<>();
            memberContrainer.setPersistenceStrategy(pps);
            memberContrainer.addMember(new ConcreteMember(1));
            memberContrainer.addMember(new ConcreteMember(2));
            memberContrainer.addMember(new ConcreteMember(3));
            assertEquals(3,memberContrainer.size());

            memberContrainer.store();
            assertEquals(3,memberContrainer.size());

            memberContrainer.deleteMember(3);
            assertEquals(2,memberContrainer.size());
            memberContrainer.load();
            assertEquals(3,memberContrainer.size());
        } catch(Exception e) {
            throw new ContainerException(e.getMessage());
        }
    }
}
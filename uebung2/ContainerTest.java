package org.hbrs.s1.ws22.uebung2;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    static Container memberContrainer;

    @BeforeAll
    static void beforeEach() throws ContainerException {
        memberContrainer = new Container();
        memberContrainer.addMember(new ConcreteMember(1));
        memberContrainer.addMember(new ConcreteMember(2));
        memberContrainer.addMember(new ConcreteMember(3));
    }

    @Test
    void addMembers() throws ContainerException {
        memberContrainer.addMember(new ConcreteMember(-10000));
        memberContrainer.addMember(new ConcreteMember(10000));
    }

    @Test
    void addMemberWithSameID() {
        ContainerException thrown = assertThrows(
                ContainerException.class,
                () -> memberContrainer.addMember(new ConcreteMember(2)),
                "ContainerException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );

        assertTrue(thrown.getMessage().contains("Member-Objekt"));
    }

    @Test
    void addNullMember() {
        ContainerException thrown = assertThrows(
                ContainerException.class,
                () -> memberContrainer.addMember(null),
                "ContainerException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );

        assertTrue(thrown.getMessage().contains("Member-Objekt"));
    }

    @Test
    void deleteMember() {
        assertEquals("Member mit ID 1 wurde entfernt!", memberContrainer.deleteMember(1));
        assertEquals("Member mit ID 10000 wurde entfernt!", memberContrainer.deleteMember(10000));
        assertEquals("Member mit ID -10000 wurde entfernt!", memberContrainer.deleteMember(-10000));
        assertEquals("Im Container gibt es kein Member-Objekt mit der angegebenen ID 11", memberContrainer.deleteMember(11));
    }

    @Test
    void dump() {
        memberContrainer.dump();
    }

    @Test
    void size() {
        assertEquals(2, memberContrainer.size());
    }
}
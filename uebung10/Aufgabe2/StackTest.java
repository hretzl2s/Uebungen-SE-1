package org.hbrs.se1.ws22.uebung10.Aufgabe2;

import org.hbrs.se1.ws22.uebung4.control.ContainerException;
import org.hbrs.se1.ws22.uebung4.model.Employee;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack s = new Stack();

    @Test
    public void testEmptyStack() {
        EmptyStackException thrown = assertThrows(
                EmptyStackException.class,
                () -> s.pop(),
                "EmptyStackException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );

        assertEquals("empty", s.getZustand());
    }

    @Test
    public void testPush() {
        assertEquals("a", s.push("a"));
        assertEquals("filled", s.getZustand());
    }

    @Test
    public void testPop() {
        s.push("a");
        assertEquals("a", s.pop());
        assertEquals("empty", s.getZustand());
    }

    @Test
    public void testFilled() {
        s.push("a");
        assertEquals("filled", s.getZustand());
        s.push("b");
        assertEquals("filled", s.getZustand());
        s.push("c");
        s.push("d");
        assertNotEquals("filled", s.getZustand());
    }

    @Test
    public void testPopWhenFull() {
        s.push("a");
        s.push("b");
        s.push("c");
        s.push("d");
        assertEquals("full", s.getZustand());
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        assertEquals("empty", s.getZustand());
    }

    @Test
    public void testFull() {
        s.push("a");
        s.push("b");
        s.push("c");
        s.push("d");
        assertEquals("full", s.getZustand());
    }

    @Test
    public void testPushWhenAlreadyFull() {
        s.push("a");
        s.push("b");
        s.push("c");
        s.push("d");
        IndexOutOfBoundsException thrown = assertThrows(
                IndexOutOfBoundsException.class,
                () -> s.push("e"),
                "IndexOutOfBoundsException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );
    }

    @AfterEach
    public void afterEach() {
        s = new Stack();
    }
}

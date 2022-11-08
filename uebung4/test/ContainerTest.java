package org.hbrs.se1.ws22.uebung4.test;

import org.hbrs.se1.ws22.uebung4.control.Container;
import org.hbrs.se1.ws22.uebung4.control.ContainerException;
import org.hbrs.se1.ws22.uebung4.control.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContainerTest {
    static Container container;

    // Alle anderen Tests wurden manuell vorgenommen werden,
    // indem man die Klasse 'Container' ausführt und die Nutzereingaben testet.

    @BeforeAll
    static void beforeAll() throws ContainerException {
        container = Container.getInstance();
        try {
            Employee e = new Employee();
            e.setPid(1);
            e.setVorname("Henry");
            e.setName("Retzlaff");
            e.setRolle("MA");
            e.setAbteilung("IT");
            container.addEmployee(e);

            Employee e1 = new Employee();
            e1.setPid(2);
            e1.setVorname("Max");
            e1.setName("Mustermann");
            e1.setRolle("MA");
            e1.setAbteilung("Presales");
            container.addEmployee(e1);
        } catch (Exception e) {
            throw new ContainerException(e.getMessage());
        }
    }

    @Test
    void addEmployeeException() {
        Employee e = new Employee();
        e.setPid(1);
        e.setVorname("Henry");
        e.setName("Retzlaff");
        e.setRolle("MA");
        e.setAbteilung("IT");

        ContainerException thrown = assertThrows(
                ContainerException.class,
                () -> container.addEmployee(e),
                "ContainerException hätte geschmissen werden sollen, wurde sie aber nicht!"
        );
    }

    @Test
    void store() throws ContainerException {
        try {
            container.store();
        } catch (Exception e) {
            throw new ContainerException(e.getMessage());
        }

    }

    @Test
    void loadMerge() throws ContainerException {
        try {
            Employee e = new Employee();
            e.setPid(3);
            e.setVorname("Maxine");
            e.setName("Musterfrau");
            e.setRolle("MA");
            e.setAbteilung("IT");
            container.addEmployee(e);

            container.load(true);
            assertEquals(3, container.getCurrentList().size());
        } catch (Exception e) {
            throw new ContainerException(e.getMessage());
        }
    }

    @Test
    void loadForce() throws ContainerException {
        try {
            Employee e = new Employee();
            e.setPid(4);
            e.setVorname("Tony");
            e.setName("Stark");
            e.setRolle("Manager");
            e.setAbteilung("IT");
            container.addEmployee(e);

            container.load(false);
            assertEquals(2, container.getCurrentList().size());
        } catch (Exception e) {
            throw new ContainerException(e.getMessage());
        }
    }

}
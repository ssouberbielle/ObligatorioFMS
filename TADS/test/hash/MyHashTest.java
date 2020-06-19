package hash;


import org.junit.Test;

import static org.junit.Assert.*;


class MyHashTest {

    @Test
    public void insert() {

        MyHash<Long, Persona> myHashListPersona = new OpenHash<>(10);
        Persona p1 = new Persona(1234, "Manuel", 12);
        Persona p2 = new Persona(2345, "Pedro", 50);
        Persona p3 = new Persona(5632, "Javier", 34);
        Persona p4 = new Persona(3457, "Daniel", 23);
        Persona p5 = new Persona(8765, "Gervasio", 11);


        myHashListPersona.put(p1.getCedula(), p1);
        myHashListPersona.put(p2.getCedula(), p2);
        myHashListPersona.put(p3.getCedula(), p3);
        myHashListPersona.put(p4.getCedula(), p4);
        myHashListPersona.put(p5.getCedula(), p5);

        assertEquals(5, myHashListPersona.getSize());
        assertEquals("Manuel", myHashListPersona.get(p1.getCedula()).getNombre());
        // assertTrue(myHashListPersona.contains(p4.getCedula()));
        myHashListPersona.remove(p3.getCedula());
        System.out.println(myHashListPersona.getSize());
        // assertFalse(myHashListPersona.contains(p3.getCedula()));
        assertEquals(4, myHashListPersona.getSize());



        /*MyHash<Long, Persona> myClosedHashList = new ClosedHash<>(10);
        myClosedHashList.put(p1.getCedula(),p1);
        myClosedHashList.put(p2.getCedula(),p2);
        myClosedHashList.put(p3.getCedula(),p3);
        myClosedHashList.put(p4.getCedula(),p4);
        myClosedHashList.put(p5.getCedula(),p5);

*/





    }


}
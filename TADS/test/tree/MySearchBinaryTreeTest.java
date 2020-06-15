package tree;

import LinkedList.MyList;
import hash.Persona;
import org.junit.Test;

import static org.junit.Assert.*;

class MySearchBinaryTreeTest {

    @Test
    public void insert(){
        MySearchBinaryTree<Integer, Persona> myTreePersona = new SearchBinaryTree<>();
        Persona p1 = new Persona(1234, "Manuel", 12);
        Persona p2 = new Persona(2345, "Pedro", 50);
        Persona p3 = new Persona(5632, "Javier", 34);
        Persona p4 = new Persona(3457,"Daniel",23);
        Persona p5 = new Persona(8765,"Gervasio",11);

        myTreePersona.insert(p1.getEdad(),p1);
        myTreePersona.insert(p2.getEdad(),p2);
        myTreePersona.insert(p3.getEdad(),p3);
        myTreePersona.insert(p4.getEdad(),p4);
        myTreePersona.remove(23);
        myTreePersona.insert(p5.getEdad(),p5);


        assertEquals(p2, myTreePersona.find(50));
        assertTrue(myTreePersona.contains(34));
        assertFalse(myTreePersona.contains(32));
        assertFalse(myTreePersona.contains(23));

        MyList<Integer> listOrder = myTreePersona.level_Order();
        for(int i= 0; i<listOrder.getSize(); i++){
            System.out.println(listOrder.get(i));
        }

        MyList<Integer> inOrder = myTreePersona.in_Order();
        for(int i= 0; i<inOrder.getSize(); i++){
            System.out.println(inOrder.get(i));
        }


    }


}
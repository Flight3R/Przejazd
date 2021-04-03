//package klasyTestowe;
//
//import lokacja.Polozenie;
//
//import static org.junit.Assert.*;
//
//public class PolozenieTest {
//
//    Polozenie test = new Polozenie(0,0);
//
//    @org.junit.Test
//    public void przenies() {
//        test.przenies(10, 1, "prawo", new Polozenie(15,15));
//        assertEquals(10,test.getX(),0.1);
//        assertEquals(0,test.getY(),0.1);
//
//        test.przenies(10, 1, "gora", new Polozenie(15,15));
//        assertEquals(10,test.getX(),0.1);
//        assertEquals(10,test.getY(),0.1);
//
//        test.przenies(10, 2, "prawo", new Polozenie(15,15));
//        assertEquals(15,test.getX(),0.1);
//        assertEquals(10,test.getY(),0.1);
//    }
//}
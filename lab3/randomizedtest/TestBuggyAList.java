package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> expected = new AListNoResizing<>();
        BuggyAList<Integer> actual = new BuggyAList<>();
        for(int i = 1; i <= 3; ++i){
            expected.addLast(i + 3);
            actual.addLast(i + 3);
            assertEquals(expected.getLast(),actual.getLast());
        }
        for(int i = 1; i <= 3; ++i)
            assertEquals(expected.removeLast(),actual.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                assertEquals(L.getLast(),BL.getLast());
                System.out.println("addLast(" + randVal + ")");
            }else if(operationNumber == 1) {
                // size
                int expected = L.size();
                int actual = BL.size();
                assertEquals(expected, actual);
                System.out.println("size: " + expected);
            }else if (L.size() > 0 && operationNumber == 2){
                    int expected = L.removeLast();
                    int actual = BL.removeLast();
                    assertEquals(expected,actual);
                    System.out.println("removeLast(" + expected + ")");
            } else if (L.size() > 0 && operationNumber == 3) {
                int randIndex = StdRandom.uniform(0,L.size());
                int expected = L.get(randIndex);
                int actual = L.get(randIndex);
                assertEquals(expected,actual);
                System.out.println("get: " + L.get(randIndex));
            }
        }
    }
}

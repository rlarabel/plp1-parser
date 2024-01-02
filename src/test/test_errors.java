package test;

import org.junit.Test;

import scanner.PLp1Scanner;
import scanner.Token;

import static org.junit.Assert.assertEquals;


public class test_errors {
    private PLp1Scanner scanner = new PLp1Scanner();

    @Test
    public void err1Test() {
        assertEquals(scanner.addInput("91abc3").getNextToken().getValue(), Token.ERROR);
    }

    @Test
    public void err2Test() {
        assertEquals(scanner.addInput("_abc").getNextToken().getValue(), Token.ERROR);
    }

    @Test
    public void err3Test() {
        assertEquals(scanner.addInput("$").getNextToken().getValue(), Token.ERROR);
    }

    @Test
    public void err4Test() {
        assertEquals(scanner.addInput("^cd").getNextToken().getValue(), Token.ERROR);
    }

    @Test
    public void err5Test() {
        assertEquals(scanner.addInput("ab#").getNextToken().getValue(), Token.ERROR);
    }

}

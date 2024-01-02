package test;

import org.junit.Test;

import scanner.PLp1Scanner;
import scanner.Token;

import static org.junit.Assert.assertEquals;


public class test_adv {
    private PLp1Scanner scanner = new PLp1Scanner();

    @Test
    public void adv1Test() {
        int[] tokens = {Token.INTNUM, Token.PLUS, Token.INTNUM};
        scanner.addInput("2+3");
        int i = 0;
        for (Token nt = scanner.getNextToken();
             nt != null;
             i++)
            assertEquals(nt.getValue(), tokens[i]);
        assertEquals(i,tokens.length);
    }

    @Test
    public void adv2Test() {
        int[] tokens = {Token.INTNUM, Token.PLUS, Token.INTNUM, Token.MULT, 
                                Token.INTNUM, Token.MINUS,Token.INTNUM, Token.DIV,
                                Token.INTNUM};
        scanner.addInput("4 + 5 * 6 - 10 / 2");
        int i = 0;
        for (Token nt = scanner.getNextToken();
             nt != null;
             i++)
            assertEquals(nt.getValue(), tokens[i]);
        assertEquals(i, tokens.length);
    }

    @Test
    public void adv3Test() {
        int[] tokens = {Token.IDENTIFIER, Token.CALL, Token.LP, Token.IDENTIFIER,
                                Token.CALL, Token.LP, Token.LBK, Token.INTNUM,
                                Token.RBK, Token.RP, Token.RP};
        scanner.addInput("listp->(rest->([1]))");
        int i = 0;
        for (Token nt = scanner.getNextToken();
             nt != null;
             i++)
            assertEquals(nt.getValue(), tokens[i]);
        assertEquals(i,tokens.length);
    }

    @Test
    public void adv4Test() {
        int[] tokens = {Token.FUNCTION, Token.IDENTIFIER, Token.LP, Token.IDENTIFIER,
                                Token.RP, Token.LB, Token.LET, Token.LP,
                                Token.LBK, Token.IDENTIFIER, Token.INTNUM, Token.RBK,
                                Token.RP, Token.LB, Token.IDENTIFIER, Token.PLUS,
                                Token.IDENTIFIER, Token.RB, Token.RB, Token.IDENTIFIER,
                                Token.CALL, Token.LP, Token.INTNUM, Token.RP};
        scanner.addInput("function f(x) { \n" + //
                "\tlet ([y 7]) {\n" + //
                "\t  x + y\n" + //
                "\t}\n" + //
                "}\n" + //
                "f->(4)");
        int i = 0;
        for (Token nt = scanner.getNextToken();
             nt != null;
             i++)
            assertEquals(nt.getValue(), tokens[i]);
        assertEquals(i,tokens.length);
    }

}

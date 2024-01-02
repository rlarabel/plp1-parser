package test;

import org.junit.Test;

import scanner.PLp1Scanner;
import scanner.Token;

import static org.junit.Assert.assertEquals;


public class test_basic {
    private PLp1Scanner scanner = new PLp1Scanner();

    @Test
    public void andTest() {
        assertEquals(scanner.addInput("&").getNextToken().getValue(), Token.AND);
    }

    @Test
    public void assignTest() {
        assertEquals(scanner.addInput("=").getNextToken().getValue(), Token.ASSIGN);
    }

    @Test
    public void callTest() {
        assertEquals(scanner.addInput("->").getNextToken().getValue(), Token.CALL);
    }

    @Test
    public void classTest() {
        assertEquals(scanner.addInput("class").getNextToken().getValue(), Token.CLASS);
    }

    @Test
    public void commaTest() {
        assertEquals(scanner.addInput(",").getNextToken().getValue(), Token.COMMA);
    }

    @Test
    public void commentTest() {
        assertEquals(scanner.addInput("// a comment").getNextToken().getValue(), Token.COMMENT);
    }

    @Test
    public void colonTest() {
        assertEquals(scanner.addInput(":").getNextToken().getValue(), Token.COLON);
    }

    @Test
    public void createTest() {
        assertEquals(scanner.addInput("create").getNextToken().getValue(), Token.CREATE);
    }

    @Test
    public void defaultTest() {
        assertEquals(scanner.addInput("default").getNextToken().getValue(), Token.DEFAULT);
    }

    @Test
    public void divTest() {
        assertEquals(scanner.addInput("/").getNextToken().getValue(), Token.DIV);
    }

    @Test
    public void dotTest() {
        assertEquals(scanner.addInput(".").getNextToken().getValue(), Token.DOT);
    }

    @Test
    public void elseTest() {
        assertEquals(scanner.addInput("else").getNextToken().getValue(), Token.ELSE);
    }

    @Test
    public void endifTest() {
        assertEquals(scanner.addInput("endif").getNextToken().getValue(), Token.ENDIF);
    }

    @Test
    public void eqTest() {
        assertEquals(scanner.addInput("==").getNextToken().getValue(), Token.EQ);
    }

    @Test
    public void falseTest() {
        assertEquals(scanner.addInput("false").getNextToken().getValue(), Token.FALSE);
    }

    @Test
    public void floatTest() {
        assertEquals(scanner.addInput("3.01").getNextToken().getValue(), Token.FLOATNUM);
    }

    @Test
    public void functionTest() {
        assertEquals(scanner.addInput("function").getNextToken().getValue(), Token.FUNCTION);
    }

    @Test
    public void gtTest() {
        assertEquals(scanner.addInput(">").getNextToken().getValue(), Token.GT);
    }

    @Test
    public void geTest() {
        assertEquals(scanner.addInput(">=").getNextToken().getValue(), Token.GE);
    }

    @Test
    public void identifierTest() {
        assertEquals(scanner.addInput("xA_1").getNextToken().getValue(), Token.IDENTIFIER);
    }

    @Test
    public void ifTest() {
        assertEquals(scanner.addInput("if").getNextToken().getValue(), Token.IF);
    }

    @Test
    public void initTest() {
        assertEquals(scanner.addInput("init").getNextToken().getValue(), Token.INIT);
    }

    @Test
    public void intTest() {
        assertEquals(scanner.addInput("239").getNextToken().getValue(), Token.INTNUM);
    }

    @Test
    public void lambdaTest() {
        assertEquals(scanner.addInput("lambda").getNextToken().getValue(), Token.LAMBDA);
    }

    @Test
    public void lbTest() {
        assertEquals(scanner.addInput("{").getNextToken().getValue(), Token.LB);
    }

    @Test
    public void lbkTest() {
        assertEquals(scanner.addInput("[").getNextToken().getValue(), Token.LBK);
    }

    @Test
    public void leTest() {
        assertEquals(scanner.addInput("<=").getNextToken().getValue(), Token.LE);
    }

    @Test
    public void ltTest() {
        assertEquals(scanner.addInput("<").getNextToken().getValue(), Token.LT);
    }

    @Test
    public void letTest() {
        assertEquals(scanner.addInput("let").getNextToken().getValue(), Token.LET);
    }

    @Test
    public void lpTest() {
        assertEquals(scanner.addInput("(").getNextToken().getValue(), Token.LP);
    }

    @Test
    public void methodTest() {
        assertEquals(scanner.addInput("method").getNextToken().getValue(), Token.METHOD);
    }

    @Test
    public void minusTest() {
        assertEquals(scanner.addInput("-").getNextToken().getValue(), Token.MINUS);
    }

    @Test
    public void multTest() {
        assertEquals(scanner.addInput("*").getNextToken().getValue(), Token.MULT);
    }

    @Test
    public void neTest() {
        assertEquals(scanner.addInput("!=").getNextToken().getValue(), Token.NE);
    }

    @Test
    public void notTest() {
        assertEquals(scanner.addInput("!").getNextToken().getValue(), Token.NOT);
    }

    @Test
    public void nullTest() {
        assertEquals(scanner.addInput("null").getNextToken().getValue(), Token.NULL);
    }

    @Test
    public void orTest() {
        assertEquals(scanner.addInput("|").getNextToken().getValue(), Token.OR);
    }

    @Test
    public void plusTest() {
        assertEquals(scanner.addInput("+").getNextToken().getValue(), Token.PLUS);
    }

    @Test
    public void rbTest() {
        assertEquals(scanner.addInput("}").getNextToken().getValue(), Token.RB);
    }

    @Test
    public void rbkTest() {
        assertEquals(scanner.addInput("]").getNextToken().getValue(), Token.RBK);
    }

    @Test
    public void rpTest() {
        assertEquals(scanner.addInput(")").getNextToken().getValue(), Token.RP);
    }

    @Test
    public void stringTest() {
        assertEquals(scanner.addInput("'string'").getNextToken().getValue(), Token.STRING);
    }

    @Test
    public void switchTest() {
        assertEquals(scanner.addInput("switch").getNextToken().getValue(), Token.SWITCH);
    }

    @Test
    public void thenTest() {
        assertEquals(scanner.addInput("then").getNextToken().getValue(), Token.THEN);
    }

    @Test
    public void trueTest() {
        assertEquals(scanner.addInput("true").getNextToken().getValue(), Token.TRUE);
    }

}

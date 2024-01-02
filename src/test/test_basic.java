package test;

import org.junit.Test;

import parser.PLp1Parser;

import static org.junit.Assert.assertEquals;


public class test_basic {
    private PLp1Parser parser = new PLp1Parser();

    @Test
    public void addTest() {
        assertEquals(parser.addInput("2+3").parse(),"");
    }

    @Test
    public void addfTest() {
        assertEquals(parser.addInput("2.0+3.1").parse(),"");
    }

    @Test
    public void andTest() {
        assertEquals(parser.addInput("true & false").parse(),"");
    }

    @Test
    public void arithTest() {
        assertEquals(parser.addInput("4 + 5 * 6 - 10 / 2").parse(),"");
    }

    @Test
    public void divTest() {
        assertEquals(parser.addInput("2/3").parse(),"");
    }

    @Test
    public void divfTest() {
        assertEquals(parser.addInput("2.0/3.1").parse(),"");
    }

    @Test
    public void emptyTest() {
        assertEquals(parser.addInput("emptyp->([1,2,3])").parse(),"");
    }

    @Test
    public void eqTest() {
        assertEquals(parser.addInput("2 == 3").parse(),"");
    }
    
    @Test
    public void gtTest() {
        assertEquals(parser.addInput("2 > 3").parse(),"");
    }

    @Test
    public void geTest() {
        assertEquals(parser.addInput("2 >= 3").parse(),"");
    }

    @Test
    public void ifTest() {
        assertEquals(parser.addInput("if true then 1 else 2 endif").parse(),"");
    }

    @Test
    public void ltTest() {
        assertEquals(parser.addInput("2 < 3").parse(),"");
    }

    @Test
    public void leTest() {
        assertEquals(parser.addInput("2 <= 3").parse(),"");
    }

    @Test
    public void neTest() {
        assertEquals(parser.addInput("2 != 3").parse(),"");
    }

    @Test
    public void notTest() {
        assertEquals(parser.addInput("!true").parse(),"");
    }

    @Test
    public void orTest() {
        assertEquals(parser.addInput("true | false").parse(),"");
    }

    @Test
    public void switchTest() {
        assertEquals(parser.addInput("switch {\n" + //
                "  case true: 1\n" + //
                "  case true:  2\n" + //
                "  default : 3\n" + //
                "}\n" + //
                "").parse(),"");
    }

}

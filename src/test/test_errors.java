package test;

import org.junit.Test;

import parser.PLp1Parser;
import static org.junit.Assert.assertEquals;


public class test_errors {
    private PLp1Parser parser = new PLp1Parser();

    @Test
    public void err1Test() {
        assertEquals(parser.addInput("2+").parse(),"");
    }

    @Test
    public void err2Test() {
        assertEquals(parser.addInput("*3").parse(),"");
    }

    @Test
    public void err3Test() {
        assertEquals(parser.addInput("function f() { 1 }").parse(),"");
    }

    @Test
    public void err4Test() {
        assertEquals(parser.addInput("function f() { 1 }").parse(),"");
    }

    @Test
    public void err5Test() {
        assertEquals(parser.addInput("function f() { 1 }").parse(),"");
    }

}

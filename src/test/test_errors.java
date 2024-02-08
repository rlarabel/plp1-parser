package test;

import org.junit.Test;

import parser.PLp1Parser;
import static org.junit.Assert.assertEquals;


public class test_errors {
    private PLp1Parser parser = new PLp1Parser();

    @Test
    public void err1Test() {
        assertEquals(parser.addInput("(+ 2)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err2Test() {
        assertEquals(parser.addInput("(*)").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err3Test() {
        assertEquals(parser.addInput("function f()  1 ").parse().replaceAll("\\s",""),
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err4Test() {
        assertEquals(parser.addInput("function f(x y) { 1 }").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err5Test() {
        assertEquals(parser.addInput("(-> x (1 2))").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err6Test() {
        assertEquals(parser.addInput("(-> (1, 2))").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err7Test() {
        assertEquals(parser.addInput("(let [x 1] { x })").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err8Test() {
        assertEquals(parser.addInput("(let ([x ]) { x })").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err9Test() {
        assertEquals(parser.addInput("(let ([x 1 2]) { x })").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err10Test() {
        assertEquals(parser.addInput("(let ([x 1]) { x )").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "expression -> <ID>\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err11Test() {
        assertEquals(parser.addInput("(if x then 1 endif)").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err12Test() {
        assertEquals(parser.addInput("(if x then else 1 endif)").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err13Test() {
        assertEquals(parser.addInput("(if x 1 else 2 endif)").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err14Test() {
        assertEquals(parser.addInput("(if x then 1 else 2)").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err15Test() {
        assertEquals(parser.addInput("(switch { [(== x 0)] })").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <EQ> expression expression <RP>\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err16Test() {
        assertEquals(parser.addInput("(switch { [] })").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err17Test() {
        assertEquals(parser.addInput("(switch { })").parse().replaceAll("\\s",""),
        ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err18Test() {
        assertEquals(parser.addInput("(lambda (x y) { x })").parse().replaceAll("\\s",""),
            ("Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err19Test() {
        assertEquals(parser.addInput("(lambda (x, y) { x )").parse().replaceAll("\\s",""),
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }

    @Test
    public void err20Test() {
        assertEquals(parser.addInput("[1, 2, 3 4]").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "Parse Error").replaceAll("\\s",""));
    }
}

package test;

import org.junit.Test;

import parser.PLp1Parser;

import static org.junit.Assert.assertEquals;


public class test_basic {
    private PLp1Parser parser = new PLp1Parser();

    @Test
    public void addTest() {
        assertEquals(parser.addInput("(+ 2 3)").parse().replaceAll("\\s",""),
        ("constantExp -> <INTNUM>\n" + //
         "expression -> constantExp\n" + //
         "constantExp -> <INTNUM>\n" + //
         "expression -> constantExp\n" + //
         "expression -> <LP> <PLUS> expression expression <RP>\n" + //
         "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void addfTest() {
        assertEquals(parser.addInput("(+ 2.0 3.1)").parse().replaceAll("\\s",""),
                ("constantExp -> <FLOATNUM>\n" + //
                "expression -> constantExp\n" + //
                "constantExp -> <FLOATNUM>\n" + //
                "expression -> constantExp\n" + //
                "expression -> <LP> <PLUS> expression expression <RP>\n" +
                "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void andTest() {
        assertEquals(parser.addInput("(& true false)").parse().replaceAll("\\s",""),
                ("constantExp -> <TRUE>\n" + //
                "expression -> constantExp\n" + //
                "constantExp -> <FALSE>\n" + //
                "expression -> constantExp\n" + //
                "expression -> <LP> <AND> expression expression <RP>\n" + //
                "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void arithTest() {
        assertEquals(parser.addInput("(/ (- (+ 4 (* 5 6)) 10) 2)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                "expression -> constantExp\n" + //
                "constantExp -> <INTNUM>\n" + //
                "expression -> constantExp\n" + //
                "constantExp -> <INTNUM>\n" + //
                "expression -> constantExp\n" + //
                "expression -> <LP> <MULT> expression expression <RP>\n" + //
                "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                "constantExp -> <INTNUM>\n" + //
                "expression -> constantExp\n" + //
                "expression -> <LP> <MINUS> expression expression <RP>\n" + //
                "constantExp -> <INTNUM>\n" + //
                "expression -> constantExp\n" + //
                "expression -> <LP> <DIV> expression expression <RP>\n" + //
                "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void assignTest() {
        assertEquals(parser.addInput("(= y 7)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <ASSIGN> <ID> expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void callTest() {
        assertEquals(parser.addInput("(-> emptyp ([1,2,3]))").parse().replaceAll("\\s",""),
            ("expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "listExp -> <LBK> ( constantExp (<COMMA> constantExp)*)? <RBK>constantExp -> listExp\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void divTest() {
        assertEquals(parser.addInput("(/ 2 3)").parse().replaceAll("\\s",""),
                ("constantExp -> <INTNUM>\n" + //
                        "expression -> constantExp\n" + //
                        "constantExp -> <INTNUM>\n" + //
                        "expression -> constantExp\n" + //
                        "expression -> <LP> <DIV> expression expression <RP>\n" + //
                        "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void divfTest() {
        assertEquals(parser.addInput("(/ 2.0 3.1)").parse().replaceAll("\\s",""),
            ("constantExp -> <FLOATNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <FLOATNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <DIV> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void eqTest() {
        assertEquals(parser.addInput("(== 2 3)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <EQ> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }
    
    @Test
    public void gtTest() {
        assertEquals(parser.addInput("(> 2 3)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <GT> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void geTest() {
        assertEquals(parser.addInput("(>= 2 3)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <GE> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void ifTest() {
        assertEquals(parser.addInput("(if true then 1 else 2 endif)").parse().replaceAll("\\s",""),
            ("constantExp -> <TRUE>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <IF> expression <THEN> expression <ELSE> expression <ENDIF> <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void letTest() {
        assertEquals(parser.addInput("(let ([y 7]) { (+ x y)} )").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" +
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void listTest() {
        assertEquals(parser.addInput("[1,2,3]").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "listExp -> <LBK> ( constantExp (<COMMA> constantExp)*)? <RBK>constantExp -> listExp\n" + //
                    "expression -> constantExp\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void ltTest() {
        assertEquals(parser.addInput("(< 2 3)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <LT> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void leTest() {
        assertEquals(parser.addInput("(<= 2 3)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <LE> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void neTest() {
        assertEquals(parser.addInput("(!= 2 3)").parse().replaceAll("\\s",""),
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <NE> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void notTest() {
        assertEquals(parser.addInput("(!true)").parse().replaceAll("\\s",""),
            ("constantExp -> <TRUE>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <NOT> expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void orTest() {
        assertEquals(parser.addInput("(| true false)").parse().replaceAll("\\s",""),
            ("constantExp -> <TRUE>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <FALSE>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <OR> expression expression <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void switchTest() {
        assertEquals(parser.addInput("(switch { [false 1] [false 2] [true 3]})").parse().replaceAll("\\s",""),
            ("constantExp -> <FALSE>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <FALSE>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <TRUE>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "switchCases -> (<LBK> expression expression <RBK>)+expression -> <LP> <SWITCH> switchCases <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

}

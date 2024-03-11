package test;

import org.junit.Test;

import parser.PLp1Parser;
import static org.junit.Assert.assertEquals;

public class test_adv {
    private PLp1Parser parser = new PLp1Parser();

    @Test
    public void adv1Test() {
        assertEquals(parser.addInput("function f() { 1 }").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" +
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv2Test() {
        assertEquals(parser.addInput("function f() { 1 } (-> f ())").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv3Test() {
        assertEquals(parser.addInput("function f(x) { (if (== x 0) then 0 else (+ x (-> f ((- x 1)))) endif) } (-> f (4))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <EQ> expression expression <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <MINUS> expression expression <RP>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expression -> <LP> <IF> expression <THEN> expression <ELSE> expression <ENDIF> <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv4Test() {
        assertEquals(parser.addInput("function f(x) { (if (== x 0) then true else (-> g ((- x 1))) endif) } function g(x) { (if (== x 0) then false else (-> f ((- x 1))) endif) } (-> f (3))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <EQ> expression expression <RP>\n" + //
                    "constantExp -> <TRUE>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <MINUS> expression expression <RP>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expression -> <LP> <IF> expression <THEN> expression <ELSE> expression <ENDIF> <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <EQ> expression expression <RP>\n" + //
                    "constantExp -> <FALSE>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <MINUS> expression expression <RP>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expression -> <LP> <IF> expression <THEN> expression <ELSE> expression <ENDIF> <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv5Test() {
        assertEquals(parser.addInput("function f(x) { (let ([y 7]) { (= x (+ x y)) x}) } (-> f (4))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expression -> <LP> <ASSIGN> <ID> expression <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv6Test() {
        assertEquals(parser.addInput("function f(L,x) { (switch { [(-> emptyp (L)) 0] [(== (-> first (L)) x) (+ (-> f ((-> first (L)), x)) 1)] [true (-> f ((-> rest (L)), x))] }) } (-> f ([1,2,3,2,4,5,2], 2))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <EQ> expression expression <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "constantExp -> <TRUE>\n" + //
                    "expression -> constantExp\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "switchCases -> (<LBK> expression expression <RBK>)+\n" +
                    "expression -> <LP> <SWITCH> <LB> switchCases <RB> <RP>\n" + // Added <LB> and <RB> like in readme 
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "functionDef -> <FUNCTION> <ID> paramList expressionList\n" + //
                    "expression -> <ID>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "listExp -> <LBK> ( constantExp (<COMMA> constantExp)*)? <RBK>" + //
                    "constantExp -> listExp\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));

    }

    @Test
    public void adv7Test() {
        assertEquals(parser.addInput("(lambda () { 1 })").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LAMBDA> paramList expressionList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv8Test() {
        assertEquals(parser.addInput("(-> (lambda () { 1 }) ())").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LAMBDA> paramList expressionList <RP>\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv9Test() {
        assertEquals(parser.addInput("(-> (lambda (x) { x }) (5))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LAMBDA> paramList expressionList <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv10Test() {
        assertEquals(parser.addInput("(-> (lambda (x,y,z) { (+ x (+ y z)) }) (5,6,7))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LAMBDA> paramList expressionList <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv11Test() {
        assertEquals(parser.addInput("(-> (lambda (x) { (-> (lambda (y) { (+ x y) }) (6)) }) (5))").parse().replaceAll("\\s",""), 
            ("paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LAMBDA> paramList expressionList <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LAMBDA> paramList expressionList <RP>\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n" + //
                    "expression -> <LP> <CALL> expression argumentList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv12Test() {
        assertEquals(parser.addInput("(let () { 1 })").parse().replaceAll("\\s",""), 
            ("letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv13Test() {
        assertEquals(parser.addInput("(let ([x 4]) { x })").parse().replaceAll("\\s",""), 
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "expression -> <ID>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv14Test() {
        assertEquals(parser.addInput("(let ([x 5] [y 7]) { (+ x y)})").parse().replaceAll("\\s",""), 
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

    @Test
    public void adv15Test() {
        assertEquals(parser.addInput("(let ([x 4] [y 7] [z 10]) { (let ([x 3] [y 10]) { (+ x (+ y z))})})").parse().replaceAll("\\s",""), 
            ("constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "constantExp -> <INTNUM>\n" + //
                    "expression -> constantExp\n" + //
                    "letDecls -> (<LBK> <ID> expression <RBK>)*\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <ID>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expression -> <LP> <PLUS> expression expression <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "expressionList -> <LB> (expression)+ <RB>\n" + //
                    "expression -> <LP> <LET> <LP> letDecls <RP> expressionList <RP>\n" + //
                    "program -> (functionDef | expression)+").replaceAll("\\s",""));
    }

}

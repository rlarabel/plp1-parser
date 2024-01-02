package test;

import org.junit.Test;

import parser.PLp1Parser;
import static org.junit.Assert.assertEquals;

public class test_adv {
    private PLp1Parser parser = new PLp1Parser();

    @Test
    public void adv1Test() {
        assertEquals(parser.addInput("function f() { 1 }").parse(), "");
    }

    @Test
    public void adv2Test() {
        assertEquals(parser.addInput("function f() { 1 }\n" + //
                "f->()\n" + //
                "").parse(), "");
    }

    @Test
    public void adv3Test() {
        assertEquals(parser.addInput("function f(x) { if (x==0) then 0 else x + f->(x-1) endif }\n" + //
                "f->(4)").parse(), "");
    }

    @Test
    public void adv4Test() {
        assertEquals(parser.addInput("function f(x) { if (x==0) then true else g->(x-1) endif }\n" + //
                "function g(x) { if (x==0) then false else f->(x-1) endif }\n" + //
                "f->(3)").parse(), "");
    }

    @Test
    public void adv5Test() {
        assertEquals(parser.addInput("function f(x) { \n" + //
                "        let ([y 7]) {\n" + //
                "          x + y\n" + //
                "        }\n" + //
                "}\n" + //
                "f->(4)").parse(), "");
    }

    @Test
    public void adv6Test() {
        assertEquals(parser.addInput("function f(L,x) {\n" + //
                "  switch {\n" + //
                "    case emptyp->(L): 0\n" + //
                "    case first->(L) == x: f->(rest->(L),x) + 1\n" + //
                "    default : f->(rest->(L),x)\n" + //
                "  }\n" + //
                "}\n" + //
                "\n" + //
                "f->([1,2,3,2,4,5,2],2)").parse(), "");
    }

    @Test
    public void adv7Test() {
        assertEquals(parser.addInput("lambda () { 1 }").parse(), "");
    }

    @Test
    public void adv8Test() {
        assertEquals(parser.addInput("(lambda () { 1 })->()").parse(), "");
    }

    @Test
    public void adv9Test() {
        assertEquals(parser.addInput("(lambda (x) { x })->(5)").parse(), "");
    }

    @Test
    public void adv10Test() {
        assertEquals(parser.addInput("(lambda (x,y,z) { x + y + z })->(5,6,7)").parse(), "");
    }

    @Test
    public void adv11Test() {
        assertEquals(parser.addInput("(lambda (x,y,z) { (lambda (x,y) { x + y + z })->(10,12) })->(5,6,7)").parse(),
                "");
    }

    @Test
    public void adv12Test() {
        assertEquals(parser.addInput("(lambda (x) { (lambda (y) { x + y })->(6) })->(5)").parse(), "");
    }

    @Test
    public void adv13Test() {
        assertEquals(parser.addInput("lambda (x) { lambda (y) { x + lambda (y) { x + y }->(7) }->(6) }->(5)").parse(),
                "");
    }

    @Test
    public void adv14Test() {
        assertEquals(parser.addInput("let () { 1 }").parse(), "");
    }

    @Test
    public void adv15Test() {
        assertEquals(parser.addInput("let ([x 4]) { x }").parse(), "");
    }

    @Test
    public void adv16Test() {
        assertEquals(parser.addInput("let ([x 5] [y 7]) {\n" + //
                "  x + y\n" + //
                "}").parse(), "");
    }

    @Test
    public void adv17Test() {
        assertEquals(parser.addInput("let ([x 4] [y 7] [z 10]) {\n" + //
                "  let ([x 3] [y 10]) {\n" + //
                "      x + y + z\n" + //
                "  }\n" + //
                "}").parse(), "");
    }

    @Test
    public void adv18Test() {
        assertEquals(parser.addInput("let ([x 4]) { \n" + //
                "  let ([y 10]) {\n" + //
                "        let ([z 15]) {\n" + //
                "      x + y + z\n" + //
                "    }\n" + //
                "  }\n" + //
                "}").parse(), "");
    }

    @Test
    public void adv19Test() {
        assertEquals(parser.addInput("let ([x let ([y 3]) { y + 7 }]\n" + //
                "      [z let ([y 4]) { y + 7 }]) {\n" + //
                "        let ([y x + z]) {\n" + //
                "          x + y + z\n" + //
                "        }\n" + //
                "} ").parse(), "");
    }

}

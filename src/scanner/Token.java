package scanner;

import java.util.HashMap;

public class Token {
    public static final int AND         = 0; 
    public static final int ASSIGN      = 1; 
    public static final int CALL        = 2; 
    public static final int CLASS       = 3; 
    public static final int COMMA       = 4; 
    public static final int COMMENT     = 5; 
    public static final int COLON       = 6; 
    public static final int CREATE      = 7; 
    public static final int DEFAULT     = 8; 
    public static final int DIV         = 9; 
    public static final int DOT         = 10; 
    public static final int ELSE        = 11; 
    public static final int ENDIF       = 12; 
    public static final int EQ          = 13; 
    public static final int ERROR       = 14; 
    public static final int FALSE       = 15; 
    public static final int FLOATNUM    = 16; 
    public static final int FUNCTION    = 17; 
    public static final int GT          = 18; 
    public static final int GE          = 19; 
    public static final int ID          = 20; 
    public static final int IF          = 21; 
    public static final int INIT        = 22; 
    public static final int INTNUM      = 23; 
    public static final int LAMBDA      = 24; 
    public static final int LB          = 25; 
    public static final int LBK         = 26; 
    public static final int LE          = 27; 
    public static final int LET         = 28; 
    public static final int LT          = 29; 
    public static final int LP          = 30; 
    public static final int METHOD      = 31; 
    public static final int MINUS       = 32; 
    public static final int MULT        = 33; 
    public static final int NE          = 34; 
    public static final int NOT         = 35; 
    public static final int NULL        = 36; 
    public static final int OR          = 37; 
    public static final int PLUS        = 38; 
    public static final int RB          = 39; 
    public static final int RBK         = 40; 
    public static final int RP          = 41; 
    public static final int STRING      = 42; 
    public static final int SWITCH      = 43; 
    public static final int THEN        = 44; 
    public static final int TRUE        = 45;
    public static final int EOF         = 46;
    
    private static HashMap<String,Integer> tokenMap;

    private static void initializeTokenMap() {
        tokenMap = new HashMap<String,Integer>();
        tokenMap.put("&",AND);
        tokenMap.put("=",ASSIGN);
        tokenMap.put("->",CALL);
        tokenMap.put("class",CLASS);
        tokenMap.put(",",COMMA);
        tokenMap.put("//",COMMENT);
        tokenMap.put(":",COLON);
        tokenMap.put("create",CREATE);
        tokenMap.put("default",DEFAULT);
        tokenMap.put("/",DIV);
        tokenMap.put(".",DOT);
        tokenMap.put("else",ELSE);
        tokenMap.put("endif",ENDIF);
        tokenMap.put("==",EQ);
        tokenMap.put("ERROR",ERROR);
        tokenMap.put("false",FALSE);
        tokenMap.put("FLOATNUM",FLOATNUM);
        tokenMap.put("function",FUNCTION);
        tokenMap.put(">",GT);
        tokenMap.put("<",GE);
        tokenMap.put("IDENTIFIER",ID);
        tokenMap.put("if",IF);
        tokenMap.put("init",INIT);
        tokenMap.put("INTNUM",INTNUM);
        tokenMap.put("lambda",LAMBDA);
        tokenMap.put("{}",LB);
        tokenMap.put("[]",LBK);
        tokenMap.put("<=",LE);
        tokenMap.put("let",LET);
        tokenMap.put("<",LT);
        tokenMap.put("(",LP);
        tokenMap.put("method",METHOD);
        tokenMap.put("-",MINUS);
        tokenMap.put("*",MULT);
        tokenMap.put("!=",NE);
        tokenMap.put("!",NOT);
        tokenMap.put("null",NULL);
        tokenMap.put("|",OR);
        tokenMap.put("+",PLUS);
        tokenMap.put("}",RB);
        tokenMap.put("]",RBK);
        tokenMap.put(")",RP);
        tokenMap.put("STRING",STRING);
        tokenMap.put("switch",SWITCH);
        tokenMap.put("then",THEN);
        tokenMap.put("true",TRUE);
    }

    public static int getTokenValue(String lexeme)  {
        if (tokenMap == null)
            initializeTokenMap();
        return tokenMap.get(lexeme);
    }

    private int value;
    private String lexeme;
    
    public Token() { }

    public Token addValue(int value) {
        this.value = value;
        return this;
    }

    public Token addLexeme(String lexeme) {
        this.lexeme = lexeme;
        return this;
    }

    public int getValue() { return value; }
    public String getLexeme() { return lexeme; }

}

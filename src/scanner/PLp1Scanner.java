package scanner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PLp1Scanner {

        private int numClasses = 0;
        private String input;
        private int charIndex;
        private final char[] classAlpha = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
                        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                        'X', 'Y', 'Z' };

        private final char[] classDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        private final char[] classWS = { ' ', '\t' };

        private final char classNL = '\n';

        private final char[] otherClasses = { '&', '=', ',', ':', '/', '>', '-', '{', '[', '<', '(', '*', '!', '|', '+',
                        '}', ']',
                        ')', '\'', '.' };

        private final List<String> keywords = Arrays.asList("case", "class", "create", "default", "endif", "else",
                        "false",
                        "function", "if", "init", "lambda", "let", "method", "null", "switch", "then", "true");

        private final int EndToken = -1;
        private final int SkpToken = -2;
        private final int ErrToken = -3;
        private final int InitialState = 0;
        private final int IdentifierState = 1;

        private final int[][] stateTable =
                        /* 0,initial */ {
                                        { 1, 2, SkpToken, SkpToken, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                                                        18, 19, 20, 21,
                                                        22 },
                                        /* 1,IDENT */ { 1, 1, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken },
                                        /* 2,INTNUM */ { ErrToken, 2, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, 30 },
                                        /* 3,& */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 4,= */ { EndToken, EndToken, EndToken, EndToken, EndToken, 23, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken },
                                        /* 5,, */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 6,: */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 7,/ */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, 24,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken },
                                        /* 8,> */ { EndToken, EndToken, EndToken, EndToken, EndToken, 28, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken },
                                        /* 9,- */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, 25, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 10,{ */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 11,[ */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 12,< */ { EndToken, EndToken, EndToken, EndToken, EndToken, 27, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken },
                                        /* 13,( */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 14,* */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 15,! */ { EndToken, EndToken, EndToken, EndToken, EndToken, 26, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken },
                                        /* 16,| */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 17,+ */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 18,} */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 19,] */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 20,) */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 21,' */ { 21, 21, ErrToken, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21,
                                                        21, 21, 21, 21, 21,
                                                        21, 21, 29, 21 },
                                        /* 22,. */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 23,== */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 24,// */ { 24, 24, EndToken, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
                                                        24, 24, 24, 24, 24,
                                                        24, 24, 24, 24 },
                                        /* 25,-> */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 26,!= */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 27,<= */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 28,>= */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 29,str */ { EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken },
                                        /* 30,flt */ { ErrToken, 30, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken,
                                                        EndToken, EndToken, EndToken, EndToken, EndToken, EndToken }
                        };

        // map state to token value being returned.
        private final int tokenValueState[] = { Token.ERROR, Token.ID, Token.INTNUM, Token.AND, Token.ASSIGN,
                        Token.COMMA, Token.COLON, Token.DIV, Token.GT, Token.MINUS, Token.LB, Token.LBK, Token.LT,
                        Token.LP, Token.MULT, Token.NOT, Token.OR, Token.PLUS, Token.RB, Token.RBK, Token.RP,
                        Token.ERROR,
                        Token.DOT, Token.EQ, Token.COMMENT, Token.CALL, Token.NE,
                        Token.LE, Token.GE, Token.STRING, Token.FLOATNUM };
        private HashMap<Character, Integer> charClass = new HashMap<Character, Integer>();

        private LinkedList<Token> unputStack = new LinkedList<Token>();

        public PLp1Scanner() {
                buildCharTable();
        }

        private void buildCharTable() {
                for (int i = 0; i < classAlpha.length; i++)
                        charClass.put(classAlpha[i], numClasses);
                numClasses++;

                for (int i = 0; i < classDigit.length; i++)
                        charClass.put(classDigit[i], numClasses);
                numClasses++;

                charClass.put(classNL, numClasses++);

                for (int i = 0; i < classWS.length; i++)
                        charClass.put(classWS[i], numClasses);
                numClasses++;

                for (int i = 0; i < otherClasses.length; i++)
                        charClass.put(otherClasses[i], numClasses++);
        }

        public PLp1Scanner addInput(String input) {
                this.input = input;
                charIndex = 0;
                return this;
        }

        public String getInput() {
                return input;
        }

        public Token getNextToken() {
                if (!unputStack.isEmpty()) {
                        return unputStack.pop();
                } else if (charIndex == input.length())
                        return (new Token()).addValue(Token.EOF);
                else {
                        int state = InitialState;
                        int nextState = InitialState;
                        String lexeme = "";
                        while (nextState != EndToken && nextState != ErrToken) {
                                state = nextState;
                                if (charIndex < input.length()) {
                                        Integer cClass = charClass.get(input.charAt(charIndex));
                                        if (cClass == null) {
                                                nextState = ErrToken;
                                        } else {
                                                nextState = stateTable[state][cClass];
                                                if (nextState != EndToken)
                                                        if (nextState != SkpToken)
                                                                lexeme += input.charAt(charIndex++);
                                                        else {
                                                                nextState = InitialState;
                                                                charIndex++;
                                                        }
                                        }
                                } else
                                        nextState = EndToken;
                        }

                        Token retToken = (new Token()).addLexeme(lexeme);
                        if (nextState == ErrToken)
                                retToken.addValue(Token.ERROR);
                        else if (state == IdentifierState) {
                                int index = keywords.indexOf(lexeme);
                                if (index == -1)
                                        retToken.addValue(Token.ID);
                                else
                                        retToken.addValue(Token.getTokenValue(lexeme));
                        } else
                                retToken.addValue(tokenValueState[state]);

                        return retToken;
                }

        }

        public void unput(Token token) {
                unputStack.push(token);
        }

        public boolean hasMoreInput() {
                return !unputStack.isEmpty() || charIndex != input.length();
        }
        public static void main(String[] args) {
                String input = String.join(" ", args);
                PLp1Scanner scanner = (new PLp1Scanner()).addInput(input);

                for (Token token = scanner.getNextToken(); token.getValue() != Token.EOF; token = scanner
                                .getNextToken())
                        System.out.println("Token value = " + token.getValue() + ", lexeme = " + token.getLexeme());
        }
}

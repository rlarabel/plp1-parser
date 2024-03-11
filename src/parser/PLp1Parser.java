package parser;

import scanner.PLp1Scanner;
import scanner.Token;

public class PLp1Parser {

	private PLp1Scanner scanner;
	private String ruleString = "";

	public PLp1Parser() {
		scanner = new PLp1Scanner();
	}

	public PLp1Parser addInput(String input) {
		this.scanner = scanner.addInput(input);
		return this;
	}

	public static void main(String[] args) {
		String input = String.join(" ", args);
		PLp1Parser parser = (new PLp1Parser()).addInput(input);
		System.out.println("Rules: "+parser.parse());
	}


	/**
	 * Match the next token in input. If token is required and does not match, ParseError is thrown. If the token is
	 * not required, the token is unput. 
	 * 
	 * @param tokenValue the value of the token to be matched
	 * @param required the token value must match if this is true
	 * @return return true if the token in matched to the next input token, othewise false
	 * @throws ParseError
	 */
	private boolean match(int tokenValue, boolean required) throws ParseError {
		Token token = scanner.getNextToken();
		boolean retValue = token.getValue() == tokenValue;
		if (!retValue) {
			if (!required) {
				scanner.unput(token);
				return false;
			} else 
				throw new ParseError();
		} else
			return true;

	}

	private boolean match(int tokenValue) throws ParseError {
		return match(tokenValue, true);
	}

	public String parse() {
		try {
			program();
			return this.ruleString;
		} catch (ParseError e) {
			return this.ruleString + "Parse Error";
		}
	}

	private void program() throws ParseError {
		while (this.scanner.hasMoreInput()) {
			if(!functionDef()) {
				expression();
			}
		}
		this.ruleString += "program -> (functionDef|expression)+\n";
	}

	private boolean functionDef() throws ParseError {
		if (match(Token.FUNCTION, false)){
			match(Token.ID);
			paramList();
			expressionList();
			this.ruleString += "functionDef -> <FUNCTION> <ID> paramList expressionList\n";
			return true;
		} else {
			return false;
		}
	}

	private void expression() throws ParseError {
		String tempString = "expression -> ";
		if (match(Token.LP, false)){
			tempString += "<LP> "; 
			if (match(Token.MULT, false)) {
				expression();
				expression();
				tempString += "<MULT> expression expression ";
			} else if (match(Token.DIV, false)) {
				expression();
				expression();
				tempString += "<DIV> expression expression ";
			} else if (match(Token.PLUS, false)) {
				expression();
				expression();
				tempString += "<PLUS> expression expression ";
			} else if (match(Token.MINUS, false)) {
				expression();
				expression();
				tempString += "<MINUS> expression expression ";
			} else if (match(Token.EQ, false)) {
				expression();
				expression();
				tempString += "<EQ> expression expression ";
			} else if (match(Token.NE, false)) {
				expression();
				expression();
				tempString += "<NE> expression expression ";
			} else if (match(Token.LT, false)) {
				expression();
				expression();
				tempString += "<LT> expression expression ";
			} else if (match(Token.LE, false)) {
				expression();
				expression();
				tempString += "<LE> expression expression ";
			} else if (match(Token.GT, false)) {
				expression();
				expression();
				tempString += "<GT> expression expression ";
			} else if (match(Token.GE, false)) {
				expression();
				expression();
				tempString += "<GE> expression expression ";
			} else if (match(Token.OR, false)) {
				expression();
				expression();
				tempString += "<OR> expression expression ";
			} else if (match(Token.AND, false)) {
				expression();
				expression();
				tempString += "<AND> expression expression ";
			} else if (match(Token.NOT, false)) {
				expression();
				tempString += "<NOT> expression ";
			} else if (match(Token.IF, false)) {
				expression();
				match(Token.THEN);
				expression();
				match(Token.ELSE);
				expression();
				match(Token.ENDIF);
				tempString += "<IF> expression <THEN> expression <ELSE> expression <ENDIF> ";
			} else if (match(Token.ASSIGN, false)) {
				match(Token.ID);
				expression();
				tempString += "<ASSIGN> <ID> expression ";
			} else if (match(Token.LAMBDA, false)) {
				paramList();
				expressionList();
				tempString += "<LAMBDA> paramList expressionList ";
			} else if (match(Token.SWITCH, false)) {
				match(Token.LB);
				switchCases();
				match(Token.RB);
				tempString += "<SWITCH> <LB> switchCases <RB> ";
			} else if (match(Token.LET, false)) {
				match(Token.LP);
				letDecls();
				match(Token.RP);
				expressionList();
				tempString += "<LET> <LP> letDecls <RP> expressionList ";
			} else if (match(Token.CALL, false)) {
				expression();
				argumentList();
				tempString += "<CALL> expression argumentList ";
			}
			match(Token.RP);
			this.ruleString += tempString + "<RP>\n";
		} else if (match(Token.ID, false)) {
			this.ruleString += tempString + "<ID>\n";
		} else {
			constantExp();
			this.ruleString += tempString + "constantExp\n";
		}
	}

	private void constantExp() throws ParseError {
		String tempString = "constantExp -> "; 
		if (match(Token.INTNUM, false)) {
			tempString += "<INTNUM>";
		} else if (match(Token.FLOATNUM, false)) {
			tempString += "<FLOATNUM>";
		} else if (match(Token.STRING, false)) {
			tempString += "<STRING>";
		} else if (match(Token.TRUE, false)) {
			tempString += "<TRUE>";
		} else if (match(Token.FALSE, false)) {
			tempString += "<FALSE>";
		} else if (match(Token.NULL, false)) {
			tempString += "<NULL>";
		} else {
			listExp();
			tempString += "listExp";
		}

		this.ruleString += tempString + "\n";
	}

	private void listExp() throws ParseError {
		String tempString = "expression -> constantExp\n";
		match(Token.LBK);
		if(!match(Token.RBK, false)) {
			constantExp();
			this.ruleString += tempString;
			while(!match(Token.RBK, false)) {
				if(!scanner.hasMoreInput()) {
					throw new ParseError();
				} else {
					match(Token.COMMA);
					constantExp();
					this.ruleString += tempString;
				}		
			}
		}
		this.ruleString +=  "listExp -> <LBK> (constantExp (<COMMA> constantExp)*)? <RBK>\n";
	}

	private void paramList() throws ParseError {
		//String tempString = "";
		//String idString = "expression -> <ID>\n";
		match(Token.LP);
		if(!match(Token.RP, false)) {
			match(Token.ID);
			//tempString += idString;
			while(!match(Token.RP, false)) {
				if(!scanner.hasMoreInput()) {
					throw new ParseError();
				} else {
					match(Token.COMMA);
					match(Token.ID);
					//tempString += idString;
				}		
			}
		}
		this.ruleString += "paramList -> <LP> (<ID> (<COMMA> <ID>)*)? <RP>\n"; //+ tempString + "\n";
	}

	private void expressionList() throws ParseError {
		match(Token.LB);
		expression();
		while(!match(Token.RB, false)) {
			if(!scanner.hasMoreInput()) {
				throw new ParseError();
			} else {
				expression();
			}
		}
		this.ruleString += "expressionList -> <LB> (expression)+ <RB>\n";
	}
	
	private void switchCases() throws ParseError {
		match(Token.LBK);
		do {
			expression();
			expression();
			match(Token.RBK);
		} while (match(Token.LBK, false));
		this.ruleString += "switchCases -> ( <LBK> expression expression <RBK> )+\n";
	}

	private void letDecls() throws ParseError {
		while (match(Token.LBK, false)) {
			match(Token.ID);
			expression();
			match(Token.RBK);
		} 
		this.ruleString += "letDecls -> (<LBK> <ID> expression <RBK>)*\n";

	}

	private void argumentList() throws ParseError {
		match(Token.LP);
		if(!match(Token.RP, false)) {
			expression();
			while(!match(Token.RP, false)) {
				if(!scanner.hasMoreInput()) {
					throw new ParseError();
				} else {
					match(Token.COMMA);
					expression();
				}		
			}
		}
		this.ruleString += "argumentList -> <LP> (expression (<COMMA> expression)*)? <RP>\n";
	}
}

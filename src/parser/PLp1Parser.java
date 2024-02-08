package parser;

import scanner.PLp1Scanner;
import scanner.Token;

public class PLp1Parser {

	private PLp1Scanner scanner;

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
			return "Parse";
		} catch (ParseError e) {
			return "Parse Error";
		}
	}

	private void program() throws ParseError {
	}

}

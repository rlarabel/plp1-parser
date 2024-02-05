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

	/**
	 * Parse the input that has been added to this object
	 * @return the rules that are applied ot parse the input string
	 */
	public String parse() {
		return null;
	}
}

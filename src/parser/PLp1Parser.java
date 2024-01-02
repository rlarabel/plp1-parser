package parser;

import scanner.PLp1Scanner;
import scanner.Token;

public class PLp1Parser {

	private PLp1Scanner scanner;
	private String input;

	public PLp1Parser() {
		scanner = new PLp1Scanner();
	}

	public PLp1Parser addInput(String input) {
		this.input = input;
		this.scanner = scanner.addInput(input);
		return this;
	}

	public String parse() {
		return null;
	}
}

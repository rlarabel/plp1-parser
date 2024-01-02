package scanner;

public class PLp1Scanner {

    private String input;

    public PLp1Scanner() {}

    public PLp1Scanner addInput(String input) {
        this.input = input;
        return this;
    }

    public String getInput() {
        return input;
    }

    public Token getNextToken() {
        return null;
    }

    public static void main(String[] args) {

    }
}

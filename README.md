# CS 4850: PLp1 Parser
Due Date: *Thursday, February 29, 2024 @ 11:59pm*

## Purpose

This project is intended to give you experience constructing a
top-down, recursive-descent parser.  

## Project Summary

Your task is to construct a parser that accepts
the PLp1 programming language. Your code will
emit the rules that are matched in the code in the order in which they are matched.

## The Parser

I have provided a Java method `match` in `parser/Plp1Parser.java`. That routine will match the next input token to the token value passed as a parameter. If the token is required to match, a `ParseError` is thrown if the input is not the provided token. If the token is not required to match, then the input is `unput` so that it may be read again.

The interface to the parser is

```Java
/**
  * Parse the input that has been added to this object
  * @return the rules that are applied to parse the input string
 */
public String parse();
```

I also have provided a `main()` method in `Plp1Parser.java`. This may be used to invoke the parser from the command line or via the debugger. The input parameters are concatenated to form the input string.

### Your Task 

Construct a top-down, recursive-descent parser from the PLp1 grammar found in the PLp1 [language description](plp1.md). Your code must emit the rules used to derive the input. As you determine which production to apply, you need to emit a string representation of the rule. You may compute FIRST sets to allow you to deterministically determine what rule is to be expanded at each point in the derivation. As an example, the output for 

```
(+ 2 3)
```

is 

```
constantExp -> <INTNUM>
expression -> constantExp
constantExp -> <INTNUM>
expression -> constantExp
expression -> <LP> <PLUS> expression expression <RP>
program -> (functionDef | expression)+
```

When an error is discovered, the words "Parse Error" should be appended to the output string and parsing should stop. For example, the output for

```
(+ 2)
```

is

```
constantExp -> <INTNUM>
expression -> constantExp
Parse Error
```


## Requirements

Your code must be written in Java. Put all of your code in `Plp1Parser.java`.

### Input

There are no hidden data for this project. All input is provided as JUnit 4 test files in the `src/test` directory. As in the previous assignment, the commands `make test_basic`, `make test_errors`, and `make test_adv` will execute the test cases.

### Submission

Submit your code via `git` as in the previous assignment.


# CS 4850: PLp1 Parser
Due Date: *Monday, October 9, 2017 @ 8am*

## Purpose

This project is intended to give you experience constructing a
parser in ANTLR v4.  

## Project Summary

Your task is to construct a parser that accepts
the PLp1 programming language. Your code will
emit the text "Success!" upon completion of parsing.

## The Parser

You are to use the scanner developed in the first project or the one provided with the parser starter code.

### Your Task 

Construct a top-down, recursive-descent parser from the PLp1 grammar found in the PLp1 [language description](plp1.md). Your code must emit the derivation used to derive the input. As you determine which production to apply, you need to emit a string representation of the rule. You may compute FIRST sets to allow you to deterministically determine what rule is to be expanded at each point in the derivation.

## Requirements

Your code must be written in Java. 

### Input

There are no hidden data for this project. All input is provided as JUnit 4 test files in the `src/test` directory. As in the previous assignment, the commands `make test_basic`, `make test_errors`, and `make test_adv` will execute the test cases.

### Submission

Submit your code via `git` as in the previous assignment.


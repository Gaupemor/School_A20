# Mandatory 2
### Interpreter w/ functional programming language

## Files
- Oblig.sml
- Test.sml
- README.md

## Run test programs

```bash
$ sml Test.sml
```

## About program

Calls function 'run' on Program to run program.  
Calls 'interpret' on each statement in the statement list.  
Expressions within statements are evaluated in 'evalExp'.  
'assocList' type holds the variable bindings. Grid and robot position information are also passed.  

- Implementation uses Identifier datatype for evaluation of the associated value, and uses string literals for declaration and assignment of values.
- Implemented interpret function differently from program sketch in assignment specification: binding declarations are defined as string * exp in the grammar, and therefore need to be evaluated to string * int and converted to assocList to be passed on.
- Pass grid, positions and bindings between 'interpret' function calls as an 'info' record - interpreter needs to evaluate bindings, move relative to current pos, and grid to check OutOfBounds.
- 'place' function generates 'info' record and checks OutOfBounds.
- Implementation does not check that arithmetic expression list ('args' in grammar) has at least two elements.

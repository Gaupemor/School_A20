package Robol;

import Expression.NumberExp;

import java.util.HashMap;

/*
    (* size of the grid given as a bound for the x axis and the y axis.
    both axes start at 0, <number> is a positive integer. *)
    <grid> ::= "size" "(" <number> "*" <number> ")" ;
 */

public class Grid {
    int xSize;
    int ySize;

    public Grid(NumberExp x, NumberExp y) {
        xSize = x.value(new HashMap<>()); //No initial variables when creating Grid
        ySize = y.value(new HashMap<>());
    }

    //Check if given coordinates are out of bound for this grid
    public boolean outOfBounds(int x, int y) {
        return x >= xSize || y >= ySize;
    }
}
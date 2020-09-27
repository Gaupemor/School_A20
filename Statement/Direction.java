package Statement;

/*
    (* on the grid, moving north means up along the y axis, east means to the right
       along the x axis, etc. *)
    <move> ::= "north" | "south" | "east" | "west" ;
 */

public enum Direction {
    north,  // y+
    south,  // y-
    east,   // x+
    west    // x-
}

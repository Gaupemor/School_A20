use "Oblig2.sml";

val test_programs = [
    Program(
        Size(64,64),
        Robot(
                nil,
                Start(Number(23), Number(30)),
                [
                    Move(west, Number(15)),
                    Move(south, Number(15)),
                    Move(east, Arithmetic(Add([Number(2), Number(3)]))),
                    Move(north, Arithmetic(Add([Number(17), Number(20)]))),
                    Stop
                ])),
    Program(
        Size(64, 64),
        Robot(
                [
                    Declaration("i", Number(5)),
                    Declaration("j", Number(3))
                ],
                Start(Number(23), Number(6)),
                [
                    Move(north, Arithmetic(Multiply([Number(3), Identifier("i")]))),
                    Move(east, Number(15)),
                    Move(south, Arithmetic(Subtract([Number(12), Identifier("i"), Identifier("j")]))),
                    Move(west, Arithmetic(Add([Arithmetic(Multiply([Number(2), Identifier("i")])), Arithmetic(Multiply([Number(3), Identifier("j")])), Number(1)]))),
                    Stop
                ])),
    Program(
        Size(64, 64),
        Robot(
                [
                    Declaration("i", Number(5)),
                    Declaration("j", Number(3))
                ],
                Start(Number(23), Number(6)),
                [
                    Move(north, Arithmetic(Multiply([Number(3), Identifier("i")]))),
                    Move(west, Number(15)),
                    Move(east, Number(4)),
                    Loop([Move(south, Identifier("j")), Assignment(Decrement("j"))], LessThan(Identifier("j"), Number(1))),
                    Stop
                ])),
    Program(
        Size(64, 64),
        Robot(
                [
                    Declaration("i", Number(8))
                ],
                Start(Number(1), Number(1)),
                [
                    Loop([Move(north, Identifier("i"))], BiggerThan(Identifier("i"), Number(100))),
                    Stop
                ]))
];

map (fn test_prog => run(test_prog) handle OutOfBounds => (~1, ~1)) test_programs;
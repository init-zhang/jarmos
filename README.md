# jarmos

CLI graphing calculator in Java

## Caveats

- No formula validation, the program will crash instead
- No zoom or scale controls
- No functions
- `x` must be preceded by a 1 if used after an operand, e.g. `5-1x`

## Usage

Compile Jarmos with the following command:

`javac Jarmos.java`

(Tested with version `11.0.28`.)

Then run using:

`java Jarmos [formula] <accuracy> <graph width> <graph height>

Jarmos accepts 4 arguments:

- the formula
- graph accuracy
- graph width
- graph height

Jarmos calculates the y coordinate for each x coordinate by passing x into the formula.

Formulas support:

- addition, subtraction, multiplication, division, and powers
- brackets
- decimals

The accuracy determines how many decimals in between whole x coordinates it should also plot. This removes gaps in certain formulas.

Graph width and graph height determine the dimensions of the graph. Numerical markers are placed in intervals of 5.

Example plot for `java Jarmos "((0.5x)^3)/10" 5 30 30`:

```
                              15                  **
                              │                   **
                              │                   **
                              │                   **
                              │                 **
                              10                **
                              │                 **
                              │               **
                              │               **
                              │               **
                              5             **
                              │             **
                              │           **
                              │         ****
                              │       ****
15────────10────────********************5 ────────10────────
                  ****        │
                ****          │
                **            │
              ****            │
              **              5
            ****              │
            **                │
            **                │
          ****                │
          **                  10
          **                  │
          **                  │
        **                    │
        **                    │
```


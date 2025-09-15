class Renderer {
    public static double[] eval(Token[] stream, double xVal) {
        double[] outputStack = new double[stream.length];
        int stackCount = 0;
        Token token;
        double temp;

        for (int i = 0; i < stream.length; i++) {
            token = stream[i];

            switch (token.type) {
                case "value":
                    outputStack[stackCount++] = Double.parseDouble(token.data);
                    break;
                case "var":
                    if (stackCount == 0) {
                        outputStack[stackCount++] = xVal;
                    } else {
                        outputStack[stackCount-1] = outputStack[stackCount-1] * xVal;
                    }
                    break;
                case "op":
                    switch (token.data) {
                        case "+":
                            stackCount -= 2;
                            temp = outputStack[stackCount] + outputStack[stackCount+1];
                            outputStack[stackCount] = temp;
                            outputStack[++stackCount] = 0;
                            break;
                        case "-":
                            stackCount -= 2;
                            temp = outputStack[stackCount] - outputStack[stackCount+1];
                            outputStack[stackCount] = temp;
                            outputStack[++stackCount] = 0;
                            break;
                        case "^":
                            stackCount -= 2;
                            temp = Math.pow(outputStack[stackCount], outputStack[stackCount+1]);
                            outputStack[stackCount] = temp;
                            outputStack[++stackCount] = 0;
                            break;
                        case "*":
                            stackCount -= 2;
                            temp = outputStack[stackCount] * outputStack[stackCount+1];
                            outputStack[stackCount] = temp;
                            outputStack[++stackCount] = 0;
                            break;
                        case "/":
                            stackCount -= 2;
                            temp = outputStack[stackCount] / outputStack[stackCount+1];
                            outputStack[stackCount] = temp;
                            outputStack[++stackCount] = 0;
                            break;

                    }
            }
        }
        return outputStack;
    }

    public static String addPadding(int n) {
        if (n < 10) {
            return n + " ";
        } else {
            return "" + n;
        }
    }

    public static String[][] createGrid(int width, int height) {
        String[][] grid = new String[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = "  ";
            }
        }

        return grid;
    }

    public static void printRow(String[] row, int y, int height) {
        String out = "";
        int midX = (int)(row.length/2);
        int midY = (int)(height/2);

        for (int x = 0; x < row.length; x++) {
            if (!row[x].equals("  ")) {
                out += row[x];
                continue;
            }

            if (y == midY) {
                if (x % 5 ==0) {
                    out += addPadding(Math.abs(x-midX));
                } else {
                    out += "──" ;
                }
                continue;
            }

            if (x == midX) {
                if (y % 5 == 0) {
                    out += addPadding(Math.abs(y-midY));
                }
                else {
                    out += "│ ";
                }
                continue;
            }
            
            out += "  ";
        }

        System.out.println(out);
    }

    public static void printGrid(String[][] grid, int width, int height) {
        for (int y = 0; y < height; y++) {
            printRow(grid[y], y, height);
        }
    }

    public static void drawFormula(Token[] formula, int width, int height, int accuracy) {
        String[][] grid = createGrid(width, height);
        int midX = (int)(width/2);
        int midY = (int)(height/2);

        int y;
        for (int x = 0; x < width*accuracy; x++) {
            y = (int)eval(formula, (x/(float)accuracy)-midX)[0];
            y *= -1;
            y += midY;
            if (y >= 0 && y < height) {
                grid[y][(int)(x/accuracy)] = "**";
            }
        }

        printGrid(grid, width, height);
    }
}

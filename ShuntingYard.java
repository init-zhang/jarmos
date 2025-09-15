class ShuntingYard {
    public static int getPrecedence(String token) {
        return getPrecedence(token, new String[] {"^", "*", "/", "+", "-"}, new int[] {4, 3, 3, 2, 2});
    }

    public static int getPrecedence(String token, String[] operators, int[] precedences) {
        for (int i = 0; i < operators.length; i++) {
            if (token.equals(operators[i])) {
                return precedences[i];
            }
        }
        return -1;
    }

    public static Token[] shunt(Token[] stream) {
        Token[] outputQueue = new Token[stream.length];
        Token[] opStack = new Token[stream.length];
        int queueCount = 0;
        int stackCount = 0;
        Token token;

        for (int i = 0; i < stream.length; i++) {
            token = stream[i];
            if (token.type.equals("value") || token.type.equals("var")) {
                outputQueue[queueCount++] = token;
            } else if (token.data.equals("(")) {
                opStack[stackCount++] = token;
            } else if (token.data.equals(")")) {
                while (!opStack[stackCount-1].data.equals("(")) {
                    outputQueue[queueCount++] = opStack[--stackCount];
                }
                stackCount--;
            } else if (token.type.equals("op")) {
                while (stackCount > 0 && !opStack[stackCount-1].data.equals("(") && getPrecedence(opStack[stackCount-1].data) >= getPrecedence(token.data)) {
                    outputQueue[queueCount++] = opStack[--stackCount];
                }
                opStack[stackCount++] = token;
            }
        }

        while (stackCount > 0) {
            outputQueue[queueCount++] = opStack[--stackCount];
        }

        if (queueCount != stream.length) {
            Token[] out = new Token[queueCount];
            for (int i = 0; i < queueCount; i++) {
                out[i] = outputQueue[i];
            }
            return out;
        }

        return outputQueue;
    }
}
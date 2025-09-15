class Tokenizer {
    public static boolean charInArray(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]) {   
                return true;
            }
        }
        return false;
    }

    public static Token[] tokenize(String stream) {
        return tokenize(stream, new char[] {'(', ')', '^', '*', '/', '+', '-'});
    }
    
    public static Token[] tokenize(String stream, char[] singluarTokens) {
        char currChar;
        Token[] tokens = new Token[stream.length()];
        int tokenCount = 0;
        String currToken = "";

        for (int i = 0; i < stream.length(); i++) {
            currChar = stream.charAt(i);
            if (currChar >= '0' && currChar <= '9' || currChar == '.') {
                currToken += "" + currChar;
            } else if (!currToken.isEmpty()) {
                tokens[tokenCount++] = new Token("value", currToken);
                currToken = "";
            }

            if (charInArray(singluarTokens, currChar)) {
                tokens[tokenCount++] = new Token("op", "" + currChar);
            }

            if (currChar >= 'a' && currChar <= 'z') {
                tokens[tokenCount++] = new Token("var", "" + currChar);
            }
        }

        if (!currToken.isEmpty()) {
            tokens[tokenCount++] = new Token("value", currToken);
        }

        Token[] out = new Token[tokenCount];
        for (int i = 0; i < tokenCount; i++) {
            out[i] = tokens[i];
        }
        return out;
    }

    public static void printTokens(Token[] tokens) {
        for (int i = 0; i < tokens.length-1; i++) {
            System.out.print(tokens[i] + ", ");
        }
        System.out.println(tokens[tokens.length-1]);
    }
}
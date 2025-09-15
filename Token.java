class Token {
    String type;
    String data;
    
    Token(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String toString() {
        return "Token(" + type + ", " + data + ")";
    }
}
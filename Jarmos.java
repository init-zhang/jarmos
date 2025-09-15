import java.util.Arrays;

public class Jarmos {
    public static void main(String[] args) {
        Token[] out = Tokenizer.tokenize(args[0]);
        Tokenizer.printTokens(out);

        out = ShuntingYard.shunt(out);
        Tokenizer.printTokens(out);

        int width = 50;
        int height = 50;
        int accuracy = 1;

        if (args.length >= 2) {
            accuracy = Integer.parseInt(args[1]);
        }

        if (args.length >= 4) {
            width = Integer.parseInt(args[2]);
            height = Integer.parseInt(args[3]);
        }

        Renderer.drawFormula(out, width, height, accuracy);
    }
}

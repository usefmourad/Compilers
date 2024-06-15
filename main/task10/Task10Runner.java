package csen1002.main.task10;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Task10Runner {

    /**
     * Parses a provided string using Task 10's grammar
     * and gets the value of the attribute "val" of the variable "s"
     *
     * @param input a string to parse
     * @return the value of the attribute "val" of the variable "s"
     */
    public static int sValValue(String input) {
        Task10Lexer lexer = new Task10Lexer(CharStreams.fromString(input));
        Task10Parser parser = new Task10Parser(new CommonTokenStream(lexer));
        return parser.s().val;
    }

    public static void main(String[] args) {
        System.out.println(sValValue("1,4,4,4,2,2,3"));
        System.out.println(sValValue("23,24,25"));
        System.out.println(sValValue("1"));
        System.out.println(sValValue("546,0,0,7"));
    }
}

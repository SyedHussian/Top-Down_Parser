public class Exp_Nil extends Exp {

    void printParseTree(String indent)
    {
        indent = indent+" ";
        IO.displayln(indent + indent.length() + " nil ");
    }
}

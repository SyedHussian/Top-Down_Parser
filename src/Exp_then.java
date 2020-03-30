public class Exp_then extends Exp{

    Exp exp;
    Exp_then(Exp exp)
    {
        this.exp = exp;
    }
    void printParseTree(String indent)
    {
        if (indent.length()<2)
            indent = indent + " ";

        String indent2 = indent + " ";
        String indent3 = indent2 + " ";

        IO.displayln(indent2 + indent2.length() + " then ");
        IO.displayln(indent3 + indent3.length() + " <exp> ");

        exp.printParseTree(indent3);

    }
}

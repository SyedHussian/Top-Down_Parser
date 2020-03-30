public class Exp_else extends Exp{
    Exp_if exp_if;
    Exp_then exp_then;
    Exp exp;
    Exp_else(Exp_if exp_if, Exp_then exp_then, Exp exp)
    {
        this.exp_if = exp_if;
        this.exp_then = exp_then;
        this.exp = exp;
    }
    void printParseTree(String indent)
    {
        String indent1 = indent + " ";

        exp_if.printParseTree(indent);
        exp_then.printParseTree(indent);

        String indent2 = indent1;
        if ( indent2.length() < 3)
            indent2 = indent1 + " ";

        String indent3 = indent2 + " ";
        IO.displayln(indent2 + indent2.length() + " else ");
        IO.displayln(indent3 + indent3.length() + " <exp> ");

        exp.printParseTree(indent3);

    }
}

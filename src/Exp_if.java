public class Exp_if extends Exp{
    Exp exp;
    Exp_if(Exp exp)
    {
        this.exp = exp;
    }
    void printParseTree(String indent)
    {
        if (indent.length() < 2 ){
            indent = indent + " ";
            IO.displayln(indent + indent.length() + " <exp> ");
        }

        String indent1 = indent + " ";
        String indent2 = indent1 + " ";

        IO.displayln(indent1 + indent1.length() + " if ");
        IO.displayln(indent2 + indent2.length() + " <exp> ");

        exp.printParseTree(indent2);

    }
}

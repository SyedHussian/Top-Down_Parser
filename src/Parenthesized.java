public class Parenthesized extends Exp{

    FunExp funExp;

    Parenthesized(FunExp funExp)
    {
        this.funExp = funExp;
    }

    void printParseTree(String indent)
    {
        String indent1 = indent + " ";
        if (indent1.length() == 1) {
            IO.displayln(indent1 + indent1.length() + " <exp> ");
            funExp.printParseTree(indent1 + " ");
        }
        else {
            funExp.printParseTree(indent + " ");
        }
    }
}

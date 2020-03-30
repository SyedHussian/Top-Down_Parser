public class Exp_Int extends Exp {
    int val;

    Exp_Int(int i)
    {
        val = i;
    }

    void printParseTree(String indent)
    {
        if (indent.length() < 2 ){
            indent = indent + " ";
            IO.displayln(indent + indent.length() + " <exp> ");
        }

        indent = indent +" ";
        IO.displayln(indent + indent.length() + " " + val);
    }
}

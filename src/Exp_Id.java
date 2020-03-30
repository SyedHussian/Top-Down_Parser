public class Exp_Id extends Exp {
    String id;

    Exp_Id(String ident)
    {
        id = ident;
    }

    void printParseTree(String indent)
    {
        if (indent.length() < 2 ){
            indent = indent + " ";
            IO.displayln(indent + indent.length() + " <exp> ");
        }

        indent = indent+" ";
        IO.displayln(indent + indent.length() +" "+ id);
    }
}

public class FunName {
    String id;

    FunName(String ident)
    {
        id = ident;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <fun name> " + id);
    }
}

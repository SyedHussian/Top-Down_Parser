public class FunDef extends FunDefList {
    Header header;
    Exp exp;

    FunDef(Header header, Exp exp)
    {
        this.header = header;
        this.exp = exp;
    }

    void printParseTree(String indent)
    {
        //IO.displayln(" ");
        IO.displayln(indent + indent.length() + " <fun def>");
        header.printParseTree(indent+" ");
        exp.printParseTree(indent);
    }
}
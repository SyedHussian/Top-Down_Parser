public class FunOp_Id extends FunOp {
    String id;

    FunOp_Id(String id)
    {
        this.id = id;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " "+id);
    }
}

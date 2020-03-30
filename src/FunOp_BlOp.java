public class FunOp_BlOp extends FunOp {
    BoolOp boolOp;

    FunOp_BlOp(BoolOp boolOp){
        this.boolOp = boolOp;
    }

    void printParseTree(String indent)
    {
        boolOp.printParseTree(indent);
    }
}

public class FunOp_ArOp extends FunOp{
    ArithOp arithOp;

    FunOp_ArOp(ArithOp arithOp){
        this.arithOp = arithOp;
    }

    void printParseTree(String indent)
    {
        arithOp.printParseTree(indent);
    }
}

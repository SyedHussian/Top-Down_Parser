public class FunOp_CpOp extends FunOp {
    CompOp compOp;

    FunOp_CpOp(CompOp compOp){
        this.compOp = compOp;
    }

    void printParseTree(String indent)
    {
        compOp.printParseTree(indent);

    }
}

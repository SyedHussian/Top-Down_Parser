public class FunExp {
    FunOp funOp;
    ExpList expList;

    FunExp(FunOp funOp, ExpList expList){
        this.funOp = funOp;
        this.expList = expList;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <fun exp> ");
        funOp.printParseTree(indent+" ");
        String indent1 = indent + " ";
        IO.displayln(indent1 + indent1.length() + " <exp list> ");
        expList.printParseTree(indent+" ");

    }

}

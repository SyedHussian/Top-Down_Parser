public class ExpList_NonEmpty extends ExpList {

    Exp exp;
    ExpList expList;


    ExpList_NonEmpty(Exp exp, ExpList expList){
        this.exp = exp;
        this.expList = expList;
    }

    void printParseTree(String indent)
    {
        String indent1 = indent + " ";
        IO.displayln(indent1 + indent1.length() + " <exp> ");

        exp.printParseTree(indent1);
        expList.printParseTree(indent);
    }
}

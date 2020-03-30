public class Header {
    FunName funName;
    ParameterList parameterList;

    Header(FunName funName, ParameterList parameterList)
    {
        this.funName = funName;
        this.parameterList = parameterList;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <header>");
        funName.printParseTree(indent+" ");
        if (parameterList != null)
            parameterList.printParseTree(indent+" ");
    }
}

public class Parameters extends ParameterList{

    ParameterList_NonEmpty parameterList_nonEmpty;

    Parameters(ParameterList_NonEmpty parameterList_nonEmpty) {
        this.parameterList_nonEmpty = parameterList_nonEmpty;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " <parameter list> ");
        parameterList_nonEmpty.printParseTree(indent+" ");
    }

    @Override
    void printParameters(String indent, ParameterList_NonEmpty parameterList_nonEmpty)
    {
        parameterList_nonEmpty.printParameters(indent, parameterList_nonEmpty);
    }

}

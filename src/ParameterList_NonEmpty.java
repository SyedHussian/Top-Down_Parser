public class ParameterList_NonEmpty
{
    String id;
    ParameterList parameterList;

    ParameterList_NonEmpty(String id, ParameterList parameterList)
    {
        this.id = id;
        this.parameterList = parameterList;
    }

    void printParseTree(String indent)
    {
        IO.displayln(indent + indent.length() + " " + id);

        if(parameterList instanceof ParameterList_Empty)
            return;

        if (parameterList != null)
        {
            Parameters parameters = (Parameters) parameterList;
            parameterList.printParameters(indent, parameters.parameterList_nonEmpty);
        }
    }

    void printParameters(String indent, ParameterList_NonEmpty parameterList_nonEmpty)
    {
        parameterList_nonEmpty.printParseTree(indent);
    }

}

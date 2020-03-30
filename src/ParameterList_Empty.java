public class ParameterList_Empty extends ParameterList{

    ParameterList_Empty(){}

    void printParseTree(String indent)
    {
        String indent1 = indent + " ";
        IO.displayln(indent + indent.length() + " <parameter list> ");
    }

    @Override
    void printParameters(String indent, ParameterList_NonEmpty id) {

    }

}

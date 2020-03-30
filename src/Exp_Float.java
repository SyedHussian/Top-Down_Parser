public class Exp_Float extends Exp {
    float val;




    Exp_Float(float val)
    {
        this.val = val;
    }

    void printParseTree(String indent)
    {
        if (indent.length() < 2 ){
            indent = indent + " ";
            IO.displayln(indent + indent.length() + " <exp> ");
        }

        indent = indent+" ";
        IO.displayln(indent + indent.length() +" "+ val);

    }
}

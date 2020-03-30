public class FunDefList_Multiple extends FunDefList {
    FunDefList defList;
    FunDef def;

    FunDefList_Multiple(FunDefList defList, FunDef def)
    {
        this.defList = defList;
        this.def = def;
    }

    void printParseTree(String indent)
    {
        def.printParseTree(indent);
        IO.displayln("");
        defList.printParseTree(indent);
    }
}

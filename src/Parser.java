/*

⟨fun def list⟩ → ⟨fun def⟩ | ⟨fun def⟩ ⟨fun def list⟩
⟨fun def⟩ → ⟨header⟩ "{" ⟨exp⟩ "}"
⟨header⟩ → ⟨fun name⟩ ⟨parameter list⟩
⟨fun name⟩ → ⟨id⟩
⟨parameter list⟩ → ε | ⟨id⟩ ⟨parameter list⟩
⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | ⟨floatF⟩ | "nil" | "(" ⟨fun exp⟩ ")" | "if" ⟨exp⟩ "then" ⟨exp⟩ "else" ⟨exp⟩
⟨fun exp⟩ → ⟨fun op⟩ ⟨exp list⟩
⟨fun op⟩ → ⟨id⟩ | "pair" | "first" | "second" | ⟨arith op⟩ | ⟨bool op⟩ | ⟨comp op⟩
⟨arith op⟩ → + | − | * | /
⟨bool op⟩ → "or" | "and" | "not"
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="
⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩

*/


public abstract class Parser extends LexArithArray {
    static boolean errorFound = false;

    public static FunDefList funDefList()
    // <fun Def List> --> <fun Def> | <fun Def> <fun Def List>
    {
        FunDef fdef = def();
        if ( state == State.Id )
        {
            FunDefList defList = funDefList();
            return new FunDefList_Multiple(defList, fdef);
        }
        else {
            return fdef;
        }
    }

    public static FunDef def()
    // <fun Def List> --> <Header> "{" <Exp> "}"
    {
        Header header = header();

        if (state == State.LBrace) {
            getToken();
            Exp exp = exp();
            if (state == State.RBrace){
                getToken();
                return new FunDef(header, exp);
            }
            else {
                errorMsg(8);
                return null;
            }
        }
        else {
            return null;
        }
    }

    public static Header header()
    //⟨header⟩ → ⟨fun name⟩ ⟨parameter list⟩
    {
        FunName funName = funName();
        ParameterList parameterList = parameterList();
        return new Header(funName, parameterList);
    }

    public static FunName funName()
    //⟨fun name⟩ → ⟨id⟩
    {
        if (state == State.Id) {
            String id = t;
            getToken();
            return new FunName(id);
        }
        errorMsg(6);
        return null;
    }

    public static ParameterList parameterList()
    //⟨parameter list⟩ → ε | ⟨id⟩ ⟨parameter list⟩
    {
        if ( state == State.LBrace) {
            return new ParameterList_Empty();
        }
        else if (state == State.Id) {
            String id = t;
            getToken();
            ParameterList parameter = parameterList();
            if (parameter instanceof ParameterList_Empty) {
                ParameterList_NonEmpty parameterList_nonEmpty = new ParameterList_NonEmpty(id, parameter);
                return new Parameters(parameterList_nonEmpty);
            }
            ParameterList_NonEmpty parameterList_nonEmpty = new ParameterList_NonEmpty(id, parameter);
            return new Parameters(parameterList_nonEmpty);
        }
        else {
            errorMsg(7);
            return null;
        }
    }

    public static Exp exp()
    //⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | ⟨floatF⟩ | "nil" | "(" ⟨fun exp⟩ ")" | "if" ⟨exp⟩ "then" ⟨exp⟩ "else" ⟨exp⟩
    {
        switch ( state )
        {
            case Id:
                Exp_Id id = new Exp_Id(t);
                getToken();
                return id;

            case Int:
                Exp_Int intElem = new Exp_Int(Integer.parseInt(t));
                getToken();
                return intElem;

            case Float: case FloatE: case FloatF:
                Exp_Float exp_float = new Exp_Float(Float.parseFloat(t));
                getToken();
                return exp_float;
            case Keyword_nil:
                Exp_Nil exp_nil = new Exp_Nil();
                getToken();
                return exp_nil;
            case LParen:
                getToken();
                FunExp funExp = funExp();
                if ( state == State.RParen )
                {
                    getToken();
                    Parenthesized parenthesized = new Parenthesized(funExp);
                    return parenthesized;
                }
                else
                {
                    errorMsg(1);
                    return null;
                }
            case Keyword_if:
                getToken();
                Exp exp1 = exp();
                Exp_if exp_if = new Exp_if(exp1);
                if (state == State.Keyword_then)
                {
                    getToken();
                    Exp exp2 = exp();
                    Exp_then exp_then = new Exp_then(exp2);
                    if (state == State.Keyword_else)
                    {
                        getToken();
                        Exp exp3 = exp();
                        Exp_else exp_else = new Exp_else(exp_if, exp_then, exp3);
                        return exp_else;
                    }
                    else{
                        errorMsg(9);
                        return null;
                    }
                }
                else {
                    errorMsg(10);
                    return null;
                }
            default:
                errorMsg(2);
                return null;
        }
    }

    public static FunExp funExp()
    //⟨fun exp⟩ → ⟨fun op⟩ ⟨exp list⟩
    {
        FunOp funOp = funOp();
        ExpList expList = expList();
        return new FunExp(funOp, expList);
    }

    public static FunOp funOp()
    //⟨fun op⟩ → ⟨id⟩ | "pair" | "first" | "second" | ⟨arith op⟩ | ⟨bool op⟩ | ⟨comp op⟩
    {
        if (state == State.Id) {
            FunOp_Id id = new FunOp_Id(t);
            getToken();
            return id;
        }
        else if (state == State.Keyword_pair){
            FunOp_Pair funOp_pair = new FunOp_Pair();
            getToken();
            return funOp_pair;
        }
        else if (state == State.Keyword_first) {
            FunOp_First funOp_first = new FunOp_First();
            getToken();
            return funOp_first;
        }
        else if (state == State.Keyword_second) {
            FunOp_Second funOp_second = new FunOp_Second();
            getToken();
            return funOp_second;
        }
        else if (state.isArithOp()) {
            FunOp_ArOp funOp_arOp = funOp_arOp();
            getToken();
            return funOp_arOp;
        }
        else if (state.isBoolOp()) {
            FunOp_BlOp funOp_blOp = funOp_blOp();
            getToken();
            return funOp_blOp;
        }
        else if (state.isCompOp()){
            FunOp_CpOp funOp_cpOp = funOp_cpOp();
            getToken();
            return funOp_cpOp;
        }

        else {
            errorMsg(3);
            return null;
        }

    }

    public static FunOp_ArOp funOp_arOp()
    //⟨arith op⟩ → + | − | * | /
    {
        if (state == State.Add)
        {
            return new FunOp_ArOp(new ArithOp_Add());
        }
        else if (state == State.Sub)
        {
            return new FunOp_ArOp(new ArithOp_Sub());
        }
        else if (state == State.Mul)
        {
            return new FunOp_ArOp(new ArithOp_Mul());
        }
        else if (state == State.Div)
        {
            return new FunOp_ArOp(new ArithOp_Div());
        }
        else {
            errorMsg(11);
            return null;
        }
    }

    public static FunOp_BlOp funOp_blOp()
    //⟨bool op⟩ → "or" | "and" | "not"
    {
        if (state == State.Keyword_or)
        {
            return new FunOp_BlOp(new BoolOp_Or());
        }
        else if (state == State.Keyword_and)
        {
            return new FunOp_BlOp(new BoolOp_And());
        }
        else if (state == State.Keyword_not)
        {
            return new FunOp_BlOp(new BoolOp_Not());
        }
        else {
            errorMsg(12);
            return null;
        }
    }

    public static FunOp_CpOp funOp_cpOp()
    //⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="
            //    lt    le     Gt    Ge     Eq
    {
        if (state == State.Lt)
        {
            return new FunOp_CpOp(new CompOp_LessThan());
        }
        else if (state == State.Le)
        {
            return new FunOp_CpOp(new CompOp_LessThanEq());
        }
        else if (state == State.Gt)
        {
            return new FunOp_CpOp(new CompOp_GreaterThan());
        }
        else if (state == State.Ge)
        {
            return new FunOp_CpOp(new CompOp_GreaterThanEq());
        }
        else if (state == State.Eq)
        {
            return new FunOp_CpOp(new CompOp_Equals());
        }
        else {
            errorMsg(4);
            return null;
        }
    }

    public static ExpList expList()
    //⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩
    {
        switch (state)
        {
            case Id: case Int: case Float: case FloatE: case FloatF: case Keyword_nil: case LParen: case Keyword_if:
                Exp exp = exp();
                ExpList expList = expList();
                return new ExpList_NonEmpty(exp, expList);
            default:
                return new ExpList_Empty();
        }
    }



    public static void errorMsg(int i)
    {
        errorFound = true;

        display(t + " : Syntax Error, unexpected symbol where");

        switch( i )
        {
            case 1:	displayln(" ) expected"); return;
            case 2: displayln(" id, int, float, nil, (, or if expected"); return;
            case 3:	displayln(" fun op expected"); return;
            case 4:	displayln(" comp op expected"); return;
            case 5:	displayln(" id expected"); return;
            case 6:	displayln(" fun name expected"); return;
            case 7: displayln(" { expected"); return;
            case 8: displayln(" } expected"); return;
            case 9: displayln(" else expected"); return;
            case 10: displayln(" then expected"); return;
            case 11: displayln(" arith op expected"); return;
            case 12: displayln(" bool op expected"); return;
        }
    }

    public static void main(String argv[])
    {
        setIO( argv[0], argv[1] );
        //setIO("C:\\Users\\tah94\\Desktop\\QC_Classes\\Spring_2020\\CS_316\\Projects\\Project_2\\src\\input_5", "C:\\Users\\tah94\\Desktop\\QC_Classes\\Spring_2020\\CS_316\\Projects\\Project_2\\src\\output_5");

        setLex();

        getToken();

        FunDefList funDefList = funDefList(); // build a parse tree
        try {
            if (!t.isEmpty())
                errorMsg(6);
            else if (!errorFound)
                funDefList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file
        }
        catch (NullPointerException e)
        {
            IO.displayln(" : Syntax Error, unexpected symbol where } expected");
        }

        closeIO();
    }

}

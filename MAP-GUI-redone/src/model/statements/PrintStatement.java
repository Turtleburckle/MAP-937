package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.type.Type;

public class PrintStatement implements Statement{
    private Expression expression;

    public PrintStatement(Expression expression) {this.expression = expression;}

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions {
        programState.getOutput().add(expression.eval(programState.getSymbolicTable(), programState.getHeap()));
        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){return "Print (" + expression.toString() + ")";}
}

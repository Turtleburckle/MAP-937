package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.type.BOOLtype;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class IfStatement implements Statement{
    private Expression expression;
    private Statement thenStatement;
    private Statement elseStatement;

    public IfStatement(Expression expression,Statement thenStatement, Statement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public IfStatement(Expression expression, Statement thenStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions{
        Value result = expression.eval(programState.getSymbolicTable(), programState.getHeap());
        if(result.getType().getClass().equals(BOOLtype.class)){
            if(((BoolValue)result).getValue()){
                thenStatement.execute(programState);
                return null;
            }
            else if(elseStatement != null){
                elseStatement.execute(programState);
                return null;
            }
        }
        throw new MyExceptions("The result {1} of the expression {0} is not of type bool!",expression,result);
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        Type typeExp = expression.typeCheck(typeEnv);
        if(typeExp.equals(new BOOLtype())){
            thenStatement.typeCheck(typeEnv.clone());
            elseStatement.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else {
            throw new MyExceptions("The condition of IF has not the type bool!");
        }
    }

}

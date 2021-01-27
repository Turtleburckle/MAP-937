package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.myStack.MyStack;
import model.myStack.MyStackImplementation;
import model.type.BOOLtype;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class WhileStatement implements Statement {
    private Expression expression;
    private Statement statement;
    public WhileStatement(Expression expression,Statement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions {
        MyDictionary<String, Value> symbolicTable = programState.getSymbolicTable();
        Value eval = expression.eval(programState.getSymbolicTable(), programState.getHeap());
        if(eval.getType().getClass().equals(BOOLtype.class)){
            if(!((BoolValue)eval).getValue()){
                return null;
            }
            else{
                programState.getExecutableStack().push(this);
                statement.execute(programState);
            }

        }
        else{throw new MyExceptions("Condition is not of type BOOL!");}
        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        Type typeExp = expression.typeCheck(typeEnv);
        if(!(typeExp instanceof BOOLtype)){
            throw new MyExceptions("The result is not of type BOOL in WHILE!");
        }
        else{
            return typeEnv;
        }

    }




}

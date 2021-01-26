package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.type.STRINGtype;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements Statement {
    private Expression expression;

    public CloseFileStatement(Expression expression){this.expression=expression;}

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions{
        Value result = expression.eval(programState.getSymbolicTable(), programState.getHeap());
        if(result.getType().getClass().equals(STRINGtype.class)){
            MyDictionary<String, BufferedReader> fileTable = programState.getFileTable();
            StringValue value = (StringValue) result;
            if(fileTable.isDefined(value.getValue())){
                try {
                    fileTable.get(value.getValue()).close();
                    fileTable.remove(value.getValue());
                } catch (IOException e) {
                    throw new MyExceptions("The file doesn't have an entry!");
                }
            }
            else
            {
                throw new MyExceptions("This file doesn't have an entry!");
            }
        }
        else{
            throw new MyExceptions("Value must be a string!");
        }
        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        return typeEnv;
    }
}

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
import java.io.FileReader;
import java.io.IOException;


public class OpenFileStatement implements Statement {
    private Expression expression;

    public OpenFileStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions {
        Value result = expression.eval(programState.getSymbolicTable(), programState.getHeap());
        MyDictionary<String, BufferedReader> fileTable = programState.getFileTable();

        if(result.getType().getClass().equals(STRINGtype.class)){
            StringValue value = (StringValue) result;
            if(!programState.getFileTable().isDefined(value.getValue())){
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(value.getValue()));
                    fileTable.put(value.getValue(),reader);
                }
                catch(IOException error){
                    throw new MyExceptions(error.getMessage());
                }
            }
            else{
                throw new MyExceptions("The used file name {0} is already opened",value.getValue());
            }
        }
        else{
            throw new MyExceptions("The result {1} of the expression {0} is not of type string!",expression,result);
        }
        return null;
    }


    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        return typeEnv;
    }
}

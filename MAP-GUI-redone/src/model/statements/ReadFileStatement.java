package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.type.INTtype;
import model.type.STRINGtype;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;


public class ReadFileStatement implements Statement {
    Expression expression;
    String variableName;

    public ReadFileStatement(Expression expression, String variableName){
    this.expression = expression;
    this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions{
        Value result = expression.eval(programState.getSymbolicTable(), programState.getHeap());
        MyDictionary<String,Value> symbolicTable =programState.getSymbolicTable();
        if(symbolicTable.isDefined(variableName)){
            Type type = symbolicTable.get(variableName).getType();
            if(type.getClass().equals(INTtype.class)){
                Value value = expression.eval(symbolicTable, programState.getHeap());
                if(value.getType().getClass().equals(STRINGtype.class)) {
                    if (programState.getFileTable().isDefined(((StringValue) value).getValue())) {
                        BufferedReader bufferedReader = programState.getFileTable().get(((StringValue) value).getValue());
                        try {
                            String output = bufferedReader.readLine();
                            if(output.equals("")){
                                throw new MyExceptions("The file is empty!");
                            }
                            else {
                                IntValue newOutput = new IntValue(Integer.parseInt(output));
                                symbolicTable.put(variableName,newOutput);
                            }
                        } catch (IOException e) {
                            throw new MyExceptions("File can't be read!");
                        }
                    }
                }
                else {
                    throw new MyExceptions("The expression {0} must be of type string",value);
                }
            }
            else {
                throw new MyExceptions("The variable must be of type int!");
            }
        }
        else {
            throw  new MyExceptions("The variable name {0} is not defined.",variableName);
        }
        return null;
    }


    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        return typeEnv;
    }

}

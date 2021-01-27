package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.type.REFtype;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class WriteHeapStatement implements Statement{
    private String VarName;
    private Expression expression;

    public WriteHeapStatement (String VarName, Expression expression){
        this.VarName = VarName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions{
        MyDictionary<String, Value> symbolicTable = programState.getSymbolicTable();
        if(symbolicTable.isDefined(VarName)){
            Type type = symbolicTable.get(VarName).getType();
            if(type.getClass().equals(REFtype.class)){
                Value value = expression.eval(symbolicTable,programState.getHeap());
                RefValue refValue = (RefValue) symbolicTable.get(VarName);
                int address = refValue.getAddress();
                if(programState.getHeap().isDefined(address)){
                    if(value.getType().equals(refValue.getLocationType())){
                        programState.getHeap().put(address,value);
                    }
                    else{throw new MyExceptions("IDK MAN!!!");}
                }
                else{throw new MyExceptions("The address {0} is not a key in the heap!",address);}
            }
            else{throw new MyExceptions("It must be a REF type!!");}
        }
        else{throw new MyExceptions("There is no such entry!");}
        return null;
    }


    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        if(!(typeEnv.isDefined(VarName))){
            throw new MyExceptions("Variable does not exist!");
        }
        if(!typeEnv.get(VarName).equals(new REFtype(expression.typeCheck(typeEnv)))){
            throw new MyExceptions("Those are not equal!");
        }
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "[WriteHeap( " + this.VarName +" , "+ this.expression.toString() + ")]";
    }

}

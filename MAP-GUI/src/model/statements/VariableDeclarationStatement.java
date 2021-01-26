package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myDictionary.MyDictionary;
import model.type.*;
import model.value.BoolValue;
import model.value.IntValue;

public class VariableDeclarationStatement implements Statement{
    private String name;
    private Type type;

    public VariableDeclarationStatement(String name, Type type){
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions{
        if(type.getClass().equals(INTtype.class)){
            programState.getSymbolicTable().put(name,type.defaultValue());
        }
        if(type.getClass().equals(BOOLtype.class)){
            programState.getSymbolicTable().put(name,type.defaultValue());
        }
        if(type.getClass().equals(STRINGtype.class)){
            programState.getSymbolicTable().put(name,type.defaultValue());
        }
        if(type.getClass().equals(REFtype.class)){
            programState.getSymbolicTable().put(name,type.defaultValue());
        }
        return null;
    }

    @Override
    public MyDictionary<String,Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        typeEnv.put(name,type);
        return typeEnv;
    }
}

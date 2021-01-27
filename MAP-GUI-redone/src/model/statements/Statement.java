package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myDictionary.MyDictionary;
import model.type.Type;

public interface Statement {
    ProgramState execute(ProgramState programState) throws MyExceptions;
    MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions;
}

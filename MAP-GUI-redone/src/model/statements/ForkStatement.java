package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myDictionary.MyDictionary;
import model.myList.MyList;
import model.myList.MyListImplementation;
import model.myStack.MyStack;
import model.myStack.MyStackImplementation;
import model.type.BOOLtype;
import model.type.Type;
import model.value.Value;

import java.io.BufferedReader;

public class ForkStatement implements Statement {
    private Statement statement;

    public ForkStatement(Statement statement){this.statement = statement;}

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions{
        MyStack<Statement> stack = new MyStackImplementation<>();
        stack.push(statement);
        MyDictionary<String, Value> symbolicTable = programState.getSymbolicTable();
        MyDictionary<Integer, Value> heap = programState.getHeap();
        MyDictionary<String, BufferedReader> fileTable = programState.getFileTable();
        MyList<Value> output = programState.getOutput();
        return new ProgramState(stack,symbolicTable.clone(),output,fileTable.clone(),heap);
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        statement.typeCheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return "[ Fork ( " +this.statement.toString() +" )]";
    }
}

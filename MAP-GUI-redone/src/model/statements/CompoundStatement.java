package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myDictionary.MyDictionary;
import model.myStack.MyStack;
import model.type.Type;

public class CompoundStatement implements Statement{

    private Statement firstStatement;
    private Statement secondStatement;

    public CompoundStatement(Statement firstStatement, Statement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions {
        MyStack<Statement> stack = programState.getExecutableStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        return secondStatement.typeCheck(firstStatement.typeCheck(typeEnv));
    }

    @Override
    public String toString(){
        return "[Compound statement : " + firstStatement.toString() + " and " + secondStatement.toString() + " ]";
    }

}

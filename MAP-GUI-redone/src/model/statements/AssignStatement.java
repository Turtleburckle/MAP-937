package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.myStack.MyStack;
import model.type.Type;
import model.value.Value;

public class AssignStatement implements Statement{
    private String ID;
    private Expression expression;

    public AssignStatement(String ID, Expression expression){
        this.ID = ID;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyExceptions {
        MyStack<Statement> statementStack = programState.getExecutableStack();
        MyDictionary<String,Value> symbolicTable = programState.getSymbolicTable();

        if(symbolicTable.isDefined(ID)) {
            Value value = expression.eval(symbolicTable, programState.getHeap());
            Type typeID = symbolicTable.get(ID).getType();
            if(value.getType().equals(typeID)) {
                symbolicTable.put(ID,value);
            }
            else
            {
                throw new MyExceptions("Declared type [{0}] of variable [{1}] doesn't match the assign expression type [{2}]",
                        typeID,ID,value.getType());
            }
            return null;
        }
        else
        {
            throw new MyExceptions("The used variable {0} was not declared yet.",ID);

        }
    }

    @Override
    public MyDictionary<String,Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        Type typeVar = typeEnv.get(ID);
        Type typeExp = expression.typeCheck(typeEnv);
        if(typeVar.equals(typeExp)){
            return typeEnv;
        }
        else
        {
            throw new MyExceptions("Assignment: right hand side and lef hand side have different types!");
        }

    }

    @Override
    public String toString(){return "" + ID + "=" + expression.toString() + ";";}

}

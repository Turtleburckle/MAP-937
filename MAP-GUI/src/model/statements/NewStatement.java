package model.statements;

import exceptions.MyExceptions;
import model.ProgramState;
import model.expression.Expression;
import model.myDictionary.MyDictionary;
import model.myStack.MyStack;
import model.type.BOOLtype;
import model.type.REFtype;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;
public class NewStatement implements Statement {
    private String VarName;
    private Expression expression;

    public NewStatement(String varName, Expression expression){
        this.VarName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute (ProgramState programState) throws MyExceptions{
        MyDictionary<String, Value> symbolicTable = programState.getSymbolicTable();
        if (symbolicTable.isDefined(VarName)) {
            Type type = symbolicTable.get(VarName).getType();
            if(type.getClass().equals(REFtype.class)){
                Value var = symbolicTable.get(VarName);
                Value value = expression.eval(symbolicTable,programState.getHeap());
                if(var.getType().getClass().equals(REFtype.class)){
                    programState.getHeap().put(ProgramState.free,value);
                    symbolicTable.put(VarName,new RefValue(ProgramState.free, ((REFtype) type).getInner()));
                    ProgramState.free = ProgramState.free + 1;

                }
                else{throw new MyExceptions("The var is not of REF type!");}
            }
            else{throw new MyExceptions("The Variable is not of type REF!");}
        }
        else{throw new MyExceptions("The variable name is not declared.");}
        return  null;
    }

    @Override
    public MyDictionary<String, Type> typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        Type typeVar = typeEnv.get(VarName);
        Type typeExp = expression.typeCheck(typeEnv);
        if(typeVar.equals(new REFtype(typeExp))){
            return typeEnv;
        }
        else{
            throw new MyExceptions("NEW statement: right hand side and left hand side and left hand side have different types");
        }
    }

    @Override
    public String toString(){return "[New model.statement: varName-> " + VarName.toString() + ", expression -> " + expression.toString() + "]\n"; }


}

package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.type.Type;
import model.value.Value;

public class VariableExpression  implements Expression{
    private String ID;

    public VariableExpression(String ID) {this.ID = ID;}

    @Override
    public Value eval(MyDictionary<String, Value> table, MyDictionary<Integer, Value> heap) throws MyExceptions{
        return table.get(ID);
    }

    @Override
    public Type typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        if(!typeEnv.isDefined(ID)){
            throw new MyExceptions("The element is not defined!");
        }
        else{
            return typeEnv.get(ID);
        }
    }

    @Override
    public String toString(){
        return "" + ID;
    }
}

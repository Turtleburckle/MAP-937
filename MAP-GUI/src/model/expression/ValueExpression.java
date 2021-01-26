package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.type.Type;
import model.value.Value;

public class ValueExpression implements Expression{
    private Value value;

    public ValueExpression(Value value) {this.value = value;}

    @Override
    public Value eval(MyDictionary<String, Value> table, MyDictionary<Integer, Value> heap) throws MyExceptions{
        return value;
    }

    @Override
    public Type typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        return value.getType();
    }

    @Override
    public String toString() {return "[Value Expression for value = " + value + "]";}
}

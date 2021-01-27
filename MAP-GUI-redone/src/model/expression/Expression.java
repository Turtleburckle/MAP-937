package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.type.Type;
import model.value.Value;
public interface Expression {
    Value eval(MyDictionary<String, Value> table, MyDictionary<Integer, Value> heap) throws MyExceptions;
    Type typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions;
}

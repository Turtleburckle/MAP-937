package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.operation.OperationsWithLogicValues;
import model.type.BOOLtype;
import model.type.INTtype;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class LogicalExpressions implements Expression{
    private Expression firstExpression;
    private Expression secondExpression;
    private OperationsWithLogicValues logicalExpression;

    public LogicalExpressions(Expression firstExpression, Expression secondExpression, OperationsWithLogicValues logicalExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.logicalExpression = logicalExpression;
    }

    @Override
    public Value eval(MyDictionary<String, Value> table, MyDictionary<Integer, Value> heap) throws MyExceptions {
        Value firstValue, secondValue;
        firstValue = firstExpression.eval(table, heap);
        if(firstValue.getType().getClass() != BOOLtype.class) {
            throw new MyExceptions("First value isn't a boolean value!");
        }
        secondValue = secondExpression.eval(table, heap);
        if(secondValue.getType().getClass() != BOOLtype.class) {
            throw new MyExceptions("Second value isn't a boolean value!");
        }

        BoolValue firstBOOLvalue = (BoolValue) firstValue;
        BoolValue secondBOOLvalue = (BoolValue) secondValue;

        switch (logicalExpression) {
            case AND:
                return new BoolValue(firstBOOLvalue.getValue() && secondBOOLvalue.getValue());
            case OR:
                return new BoolValue(firstBOOLvalue.getValue() || secondBOOLvalue.getValue());
            default:
                throw new MyExceptions("ERROR: Invalid operation!");
        }
    }

    @Override
    public Type typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        Type type1, type2;
        type1 = firstExpression.typeCheck(typeEnv);
        type2 = secondExpression.typeCheck(typeEnv);
        if(type1.equals(new BOOLtype())){
            if(type2.equals(new BOOLtype())){
                return new BOOLtype();
            }
            else {
                throw new MyExceptions("The second Expression is not of type BOOL!");
            }
        }
        else {
            throw new MyExceptions("The first Expression is not of type BOOL!");
        }
    }
}

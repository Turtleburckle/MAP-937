package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.operation.OperationsWithIntegerExpressions;
import model.type.BOOLtype;
import model.type.INTtype;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

public class RelationalExpressions implements Expression {
    private Expression firstExpression;
    private Expression secondExpression;
    private OperationsWithIntegerExpressions operation;

    public RelationalExpressions(Expression firstExpression, Expression secondExpression, OperationsWithIntegerExpressions operation){
        this.firstExpression=firstExpression;
        this.secondExpression = secondExpression;
        this.operation = operation;
    }

    @Override
    public Value eval(MyDictionary<String, Value> table, MyDictionary<Integer, Value> heap) throws MyExceptions {
        Value firstValue = firstExpression.eval(table, heap);
        if(firstValue.getType().getClass() != INTtype.class){
            throw new MyExceptions("This {0} must be an integer expression!",firstExpression);
        }
        Value secondValue = secondExpression.eval(table, heap);
        if(secondValue.getType().getClass() != INTtype.class){
            throw new MyExceptions("The second expression must be of type int!");
        }

        IntValue firstINTvalue = (IntValue) firstValue;
        IntValue secondINTvalue = (IntValue) secondValue;

        switch (operation){
            case LESSTHAN:
                return new BoolValue(firstINTvalue.getValue() < secondINTvalue.getValue());
            case LESSOREQUALTHAN:
                return new BoolValue(firstINTvalue.getValue() <= secondINTvalue.getValue());
            case EQUAL:
                return new BoolValue(firstINTvalue.getValue() == secondINTvalue.getValue());
            case DIFFERENTTHAN:
                return new BoolValue(firstINTvalue.getValue() != secondINTvalue.getValue());
            case GREATERETHAN:
                return new BoolValue(firstINTvalue.getValue() > secondINTvalue.getValue());
            case GREATEROREQUALTHAN:
                return new BoolValue(firstINTvalue.getValue() >= secondINTvalue.getValue());
            default:
                throw new MyExceptions("ERROR: Invalid operation!");
        }
    }

    @Override
    public Type typeCheck(MyDictionary<String,Type> typeEnv) throws MyExceptions{
        Type type1, type2;
        type1 = firstExpression.typeCheck(typeEnv);
        type2 = secondExpression.typeCheck(typeEnv);
        if(type1.equals(new INTtype())){
            if(type2.equals(new INTtype())){
                return new BOOLtype();
            }
            else {
                throw new MyExceptions("The second Expression is not of type INT!");
            }
        }
        else {
            throw new MyExceptions("The first Expression is not of type INT!");
        }
    }
}

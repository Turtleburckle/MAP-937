package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.operation.OperationsWithIntegers;
import model.type.INTtype;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class ArithmeticExpression implements Expression{
    private Expression firstExpression;
    private Expression secondExpression;
    private OperationsWithIntegers operation;

    public ArithmeticExpression(Expression firstExpression,Expression secondExpression, OperationsWithIntegers operation) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operation = operation;
    }

    @Override
    public Value eval(MyDictionary<String, Value> table, MyDictionary<Integer, Value> heap) throws MyExceptions {
        Value firstValue, secondValue;
        firstValue = firstExpression.eval(table,heap);
        if(firstValue.getType().getClass() != INTtype.class) {
            throw new MyExceptions("The first operand must be an integer!");
        }
        secondValue = secondExpression.eval(table, heap);
        if(!secondValue.getType().equals(new INTtype())) {
            throw new MyExceptions("Second operand must be an integer!");
        }

        IntValue firstINTvalue = (IntValue) firstValue;
        IntValue secondINTvalue = (IntValue) secondValue;

        switch (operation){
            case SUM:
                return new IntValue(firstINTvalue.getValue() + secondINTvalue.getValue());
            case DIFFERENCE:
                return new IntValue(firstINTvalue.getValue() - secondINTvalue.getValue());
            case DIVISION:
                if(0 == secondINTvalue.getValue()) {throw new MyExceptions("ERROR: Division by 0 not suported!");}
                return new IntValue(firstINTvalue.getValue() / secondINTvalue.getValue());
            case MULTIPLICATION:
                return new IntValue(firstINTvalue.getValue() * secondINTvalue.getValue());
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
                return new INTtype();
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

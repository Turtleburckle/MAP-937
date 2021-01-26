package model.expression;

import exceptions.MyExceptions;
import model.myDictionary.MyDictionary;
import model.type.REFtype;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements Expression {
    private Expression expression;

    public ReadHeapExpression(Expression expression){this.expression = expression;}

    @Override
    public Value eval(MyDictionary<String,Value> table,MyDictionary<Integer, Value> heap) throws MyExceptions {
        Value value = expression.eval(table,heap);
        if(value.getClass().equals(RefValue.class)){
            RefValue refValue = (RefValue) value;
            int address = refValue.getAddress();
            if(heap.isDefined(address)){
                return heap.get(address);
            }
            else {throw new MyExceptions("The address is not a key in the heap!");}
        }
        else{throw new MyExceptions("Value must be of type REF!");}
    }

    @Override
    public Type typeCheck(MyDictionary<String, Type> typeEnv) throws MyExceptions{
        Type type = expression.typeCheck(typeEnv);
        if(type instanceof REFtype){
            REFtype ref = (REFtype) type;
            return ref.getInner();
        }
        else
        {
            throw new MyExceptions("the rH argument is not a Ref Type");
        }
    }
}

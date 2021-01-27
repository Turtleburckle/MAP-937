package model.type;

import model.value.RefValue;
import model.value.Value;

public class REFtype implements Type {

    private Type inner;

    public REFtype(Type inner){this.inner = inner;}

    public Type getInner(){return inner;}

    @Override
    public boolean equals(Object object){return object instanceof REFtype;}

    @Override
    public String toString(){return "Ref " + inner.toString() + "";}

    @Override
    public Value defaultValue() {return new RefValue(0,inner);}

}

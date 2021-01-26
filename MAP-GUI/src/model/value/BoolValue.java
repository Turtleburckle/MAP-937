package model.value;

import model.type.BOOLtype;
import model.type.Type;

public class BoolValue implements Value {

    private boolean value;

    BoolValue() {value = false;}

    public BoolValue(boolean value){this.value = value;}

    public boolean getValue(){return value;}

    public void setValue(boolean value){this.value = value;}

    @Override
    public Type getType() {return new BOOLtype();}

    @Override
    public boolean equals(Object object){return object instanceof BOOLtype;}

    @Override
    public String toString(){return "Boolean value "+value;}
}

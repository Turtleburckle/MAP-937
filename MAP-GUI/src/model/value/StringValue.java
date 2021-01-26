package model.value;

import model.type.STRINGtype;
import model.type.Type;

public class StringValue implements Value {

    private String value;

    StringValue(){this.value = "";}
    public StringValue(String value){this.value = value;}
    public String getValue(){return value;}
    public void setValue(String newValue){this.value = newValue;}

    @Override
    public Type getType(){return new STRINGtype();}

    @Override
    public String toString() {return "[ STRING value" + value + "]";}

    @Override
    public boolean equals(Object object){return object instanceof STRINGtype;}
}

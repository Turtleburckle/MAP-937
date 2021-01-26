package model.type;

import model.value.BoolValue;
import model.value.Value;

public class BOOLtype implements Type{
    @Override
    public boolean equals(Object object){
        return object instanceof BOOLtype;
    }

    @Override
    public String toString(){
        return "BOOL";
    }

    @Override
    public BoolValue defaultValue(){
        return new BoolValue(false);
    }

}

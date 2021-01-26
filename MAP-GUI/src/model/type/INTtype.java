package model.type;

import model.value.IntValue;
import model.value.Value;

public class INTtype implements Type {

    @Override
    public boolean equals (Object object) {
        return object instanceof INTtype;
    }

    @Override
    public String toString() {
        return "INT";
    }

    @Override
    public IntValue defaultValue(){
        return new IntValue(0);
    }
}

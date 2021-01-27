package model.type;

import model.value.StringValue;

public class STRINGtype implements Type {

    @Override
    public boolean equals(Object object) {return object instanceof STRINGtype;}

    @Override
    public String toString (){return "String";}

    @Override
    public StringValue defaultValue() {
        return new StringValue("");
    }
}

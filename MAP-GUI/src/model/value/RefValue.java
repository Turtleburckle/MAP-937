package model.value;

import model.type.INTtype;
import model.type.REFtype;
import model.type.Type;

public class RefValue implements Value {
    private int address;
    private Type locationType;

    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType  = locationType;
    }

    public int getAddress(){return address;}

    public Type getLocationType(){return locationType;}

    @Override
    public Type getType(){return new REFtype(locationType);}

    @Override
    public boolean equals(Object object){return object instanceof REFtype;}

    @Override
    public String toString(){return "[ REF value address: " + address + ", location: " + locationType + "]\n";}

}

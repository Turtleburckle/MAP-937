package model.value;

import model.type.INTtype;
import model.type.Type;

public class IntValue implements Value {
   private int value;

   IntValue(){this.value = 0;}
   public IntValue(int value){this.value = value;}
   public int getValue(){return value;}
   public void setValue(int value){this.value = value;}

   @Override
    public Type getType() {return new INTtype(); }

    @Override
    public String toString() {return "[ INT value "+ value + "]";}

    @Override
    public boolean equals(Object object){return object instanceof INTtype;}

}


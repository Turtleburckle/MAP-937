package com.company.model;

public class Bird implements Animal{

    private int birdID;
    private double weight;
    private int age;
    private String typeBird;
    private Boolean flies; // true - it flies | false - it doesn't

    public Bird (int ID, double weight, int age, String type, Boolean flies)
    {
        this.birdID = ID;
        this.weight = weight;
        this.age = age;
        this.typeBird = type;
        this.flies = flies;
    }

    @Override
    public int getID() {
        return this.birdID;
    }

    @Override
    public double getWeight() { return this.weight; }

    public void setWeight(double newWeight) { this.weight = newWeight; }

    @Override
    public int getAge() { return this.age; }

    public void setAge() { this.age++; }

    public String getTypeBird() { return typeBird; }

    public Boolean getFlies() { return flies; }

    @Override
    public String toString() {return "Bird >>" +this.birdID+ "<<\nWeight : " + this.weight + "\nAge : " + this.age + "\nType : " + this.typeBird + "\nFlies : " +  this.flies;}

    @Override
    public String toStringFile() {
        return "Bird,"+this.weight+","+this.age+","+this.typeBird+","+this.flies+";";
    }

}

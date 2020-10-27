package com.company.model;

public class Pig implements Animal{

    private int pigID;
    private double weight;
    private int age;
    private Boolean chopped; //true - dead | false -alive *sorry*

    public Pig(int ID, double weight, int age, boolean chopped)
    {
        this.pigID = ID;
        this.weight = weight;
        this.age = age;
        this.chopped = false;
    }

    @Override
    public int getID() {
        return this.pigID;
    }

    @Override
    public double getWeight() { return this.weight; }

    public void setWeight(double newWeight) {this.weight = newWeight;}

    @Override
    public int getAge() { return this.age; }

    public void grow() { this.age++; }

    public Boolean getChopped() { return chopped; }

    public void setChopped(Boolean chopped) { this.chopped = chopped; }

    @Override
    public String toString(){return "Pig >>"+this.pigID+"<<\nWeight : " + this.weight + "\nAge : " + this.age + "\nAlive : " + this.chopped;}

    @Override
    public String toStringFile() {
        return "Pig,"+this.weight+","+this.age+","+this.chopped+";";
    }
}

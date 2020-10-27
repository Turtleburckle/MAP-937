package com.company.model;

public class Cow implements Animal{

    private int cowID;
    private double weight;
    private int age;
    private int litersMilk; // the liters of milk the cow gives per day

    public Cow (int ID, double weight, int age, int litersMilk)
    {
        this.cowID = ID;
        this.weight = weight;
        this.age = age;
        this.litersMilk = litersMilk;
    }

    @Override
    public int getID() {
        return this.cowID;
    }

    @Override
    public double getWeight() { return this.weight; }

    public void setWeight(double newWeight) { this.weight = newWeight; }

    @Override
    public int getAge() { return this.age; }

    public void setAge() {this.age++;}

    public int getLitersMilk() { return litersMilk; }

    public void setLitersMilk(int litersMilk) { this.litersMilk = litersMilk; }

    public String toString() {return "Cow >>" +this.cowID+ "<<\nWeight : " + this.weight + "\nAge : " + this.age + "\nLiters of Milk Produced : " + this.litersMilk;}

    @Override
    public String toStringFile() {
        return "Cow,"+this.weight+","+this.age+","+this.litersMilk+";";
    }
}

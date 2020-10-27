package com.company.controller;

import com.company.repository.Repository;

import java.io.IOException;

public class Controller {
    private Repository repository;
    public Controller (Repository repository){this.repository=repository;}

    public String getAllAnimals()
    {
        return this.repository.getAllAnimals();
    }

    public String getAllTheCows()
    {
        return this.repository.getAllFarmOfType("Cow");
    }

    public String getAllTheBirds()
    {
        return this.repository.getAllFarmOfType("Bird");
    }

    public String getAllThePigs()
    {
        return this.repository.getAllFarmOfType("Pig");
    }

   public String addCow(double weight, int age, int litersOfMilk)
   {
       boolean addStatus = this.repository.addCow(weight,age,litersOfMilk);
       if (addStatus) return "Your animal is in a safe place now!";
       return "I see what you did there, try again later.";
   }

   public String addBird(double weight, int age, String typeOfBird, boolean flies)
   {
       boolean addStatus = this.repository.addBird(weight,age,typeOfBird,flies);
       if (addStatus) return "Your animal is in a safe place now!";
       return "I see what you did there, try again later.";
   }

   public String addPig(double weight, int age, boolean alive)
   {
       boolean addStatus = this.repository.addPig(weight,age,alive);
       if (addStatus) return "Your animal is in a safe place now!";
       return "I see what you did there, try again later.";
   }


    public String removeAnimal(int ID)
    {
        boolean response = this.repository.removeAnimal(ID);
        if (response) {return "We hope you take care of the animal!";}
        return "This animal doesn't exist in our farm, please try another one!";
    }

    public String filterAnimals()
    {
        return this.repository.allWeightAnimals();
    }

    public void exitFarm() throws IOException {this.repository.writeFile();}
}

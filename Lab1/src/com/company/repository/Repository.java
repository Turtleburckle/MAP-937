package com.company.repository;

import com.company.model.Animal;
import com.company.model.Bird;
import com.company.model.Cow;
import com.company.model.Pig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Repository implements RepositoryInterface{

    private final File myFile;
    private String myData;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private int cowNumber;
    private int pigNumber;
    private int birdNumber;

    public Repository () throws FileNotFoundException {
        this.cowNumber = 0;
        this.pigNumber = 0;
        this.birdNumber = 0;
        this.myData = "";
        this.myFile = new File("C:\\Users\\zsoka\\IdeaProjects\\MAP-Lab1\\src\\com\\company\\farm.txt");
        this.readFile();
        //this.print();
    }

    public String getAllAnimals()
    {
        String myFarm = "";
        for (Animal animal : this.animals)
        {
            myFarm += animal.toString() + "\n -------------------- \n";
        }
        return myFarm;
    }

    public String getAllFarmOfType(String type)
    {
        String allFarmAnimalsType = "";
        for (Animal animal : this.animals)
        {
            if (type.equals("Cow"))
            {
                if (animal.getClass().equals(Cow.class))
                {
                    allFarmAnimalsType += animal.toString() + "\n -------------------- \n";
                }
            }
            else if (type.equals("Bird"))
            {
                if (animal.getClass().equals(Bird.class))
                {
                    allFarmAnimalsType += animal.toString() + "\n -------------------- \n";
                }
            }
            else if (type.equals("Pig"))
            {
                if(animal.getClass().equals(Pig.class))
                {
                    allFarmAnimalsType += animal.toString() + "\n -------------------- \n";
                }
            }
        }
        return allFarmAnimalsType;
    }

    public String allWeightAnimals()
    {
        String allAnimalsFiltered = "";
        for(Animal animal : this.animals)
        {
            if(animal.getWeight() > 3)
            {
                allAnimalsFiltered += animal.toString() + "\n -------------------- \n";
            }
        }

        return allAnimalsFiltered;
    }


    @Override
    public void readFile() throws FileNotFoundException {
        String data = "";
        try
        {
            Scanner myReader = new Scanner(this.myFile);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            this.dataSplitter(data);
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e);
        }
        this.myData = data;
    }

    private void dataSplitter(String data)
    {
        String semicolon = ";";
        String comma = ",";
        String[] lines = data.split(semicolon);
        for (String line : lines)
        {
            String[] words = line.split(comma);
            int index = 0;
            String type = "";
            String weight = "";
            String age = "";
            String littersOfMilk = "";
            String typeBird = "";
            String flies = "";
            String chopped = "";
            for (String word : words)
            {
                if (index == 0) type = word;
                if (index == 1) weight = word;
                if (index == 2) age = word;
                if (index == 3 && type.equals("Cow")) littersOfMilk = word;
                if (index == 3 && type.equals("Bird")) typeBird = word;
                if (index == 3 && type.equals("Pig")) chopped = word;
                if (index == 4 && type.equals("Bird")) flies = word;
                index++;
            }
            if (type.equals("Cow")){this.addCow(Double.parseDouble(weight),Integer.parseInt(age),Integer.parseInt(littersOfMilk));}
            if (type.equals("Bird")){this.addBird(Double.parseDouble(weight),Integer.parseInt(age),typeBird,Boolean.parseBoolean(flies));}
            if (type.equals("Pig")){this.addPig(Double.parseDouble(weight),Integer.parseInt(age),Boolean.parseBoolean(chopped));}
        }
    }

    public boolean addCow(double weight, int age, int littersOfMilk)
    {
        try {
            Animal cow = new Cow((this.animals.size() + 1), weight, age, littersOfMilk);
            this.animals.add(cow);
            return true;
        }
        catch (Exception e) {System.out.println(e);}
        return false;
    }

    public boolean addPig(double weight, int age, boolean chopped)
    {
        try {
            Animal pig = new Pig((this.animals.size() + 1), weight, age, chopped);
            this.animals.add(pig);
            return true;
        }
        catch (Exception e) {System.out.println(e);}
        return false;
    }

    public boolean addBird(double weight,int age,String typeOfBird, boolean flies)
    {
        try {
            Animal bird = new Bird((this.animals.size() + 1), weight, age, typeOfBird, flies);
            this.animals.add(bird);
            return true;
        }
        catch (Exception e ){System.out.println(e);}
        return false;
    }

    public boolean removeAnimal(int ID)
    {
        for (Animal animal : this.animals)
        {
            if (animal.getID() == ID)
            {
                this.animals.remove(animal);
                return true;
            }
        }
        return false;
    }

    @Override
    public void writeFile() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\zsoka\\IdeaProjects\\MAP-Lab1\\src\\com\\company\\farm.txt");
        String data = "";
        for (Animal animal : this.animals)
        {
            data += animal.toStringFile() + "\n";
        }
        writer.write(data);
        writer.close();
    }

    public void print()
    {
        System.out.println(this.animals.get(0).toString());
    }
}

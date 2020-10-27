package com.company.view;

import com.company.controller.Controller;
import com.company.exception.MyException;
import com.company.repository.Repository;
import com.company.validator.Validator;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class View {
    private Controller controller;
    private Repository repository;
    private Validator validator;

    public View () throws FileNotFoundException {
        this.validator = new Validator();
        this.repository = new Repository();
        this.controller = new Controller(this.repository);
    }

    public void run()
    {
        System.out.println("Welcome to the Farm!");
        System.out.println("Please choose your option!");
        this.printable();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            try {
                int command = scanner.nextInt();
                if (command == 1) System.out.println(this.controller.getAllAnimals());
                else if (command == 2) System.out.println(this.controller.getAllTheCows());
                else if (command == 3) System.out.println(this.controller.getAllTheBirds());
                else if (command == 4) System.out.println(this.controller.getAllThePigs());
                else if (command == 5) System.out.println(this.getInfoAboutAnimal());
                else if (command == 6) System.out.println(this.removeAnimal());
                else if (command == 7) System.out.println(this.controller.filterAnimals());
                else if (command == 0) {
                    this.controller.exitFarm();
                    exit = true;
                } else throw new Exception("");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public String getInfoAboutAnimal()
    {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Please enter the type of animal!");
            System.out.println("--- 1 -> Cow | 2 -> Bird | 3 -> Pig ---");
            System.out.println("Type :");
            int input = scanner.nextInt();
            String type = "";
            if (input == 1) type += "Cow";
            else if (input == 2) type += "Bird";
            else if (input == 3) type += "Pig";
            else throw new Exception("");
            System.out.println("Please enter the weight!");
            System.out.println("Weight :");
            double weight = scanner.nextDouble();
            System.out.println("Please enter the age!");
            System.out.println("Age :");
            int age = scanner.nextInt();
            if (input == 1)
            {
                System.out.println("Please enter how much milk your cow gives (in liters)!");
                System.out.println("Liters of Milk :");
                int litersOfMilk = scanner.nextInt();
                return this.controller.addCow(weight,age,litersOfMilk);

            }
            else if (input == 2)
            {
                System.out.println("Can our bird fly?");
                System.out.println("--- 1 -> yes | 2 -> no ---");
                System.out.println("Response :");
                int response = scanner.nextInt();
                boolean flies = false;
                if (response == 1) flies = true;
                else if (response == 2) flies = false;
                else throw new Exception("");
                System.out.println("What type of bird is it?");
                System.out.println("Type of bird :");
                String typeOfBird = scanner.nextLine();
                return this.controller.addBird(weight,age,typeOfBird,flies);
            }
            else if (input == 3)
            {
                System.out.println("By default your pig is presumed to be alive, but did you chopped it?");
                System.out.println("--- 1 -> yes | 2 -> no ---");
                int response = scanner.nextInt();
                boolean chopped = false;
                if (response == 1)
                {
                    System.out.println("... that's sad ... ");
                    chopped = true;
                }
                else if (response == 2)
                {
                    System.out.println("... pheeeew!");
                    chopped = false;
                }
                else throw new Exception("");
                return this.controller.addPig(weight,age,chopped);
            }
        }
        catch (Exception e) {System.out.println(e);}
        return "Your add couldn't be done!";
    }

    private String removeAnimal()
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.println("What animal do you want to take home?");
            System.out.println("--- 1 -> Cow | 2 -> Bird | 3 -> Pig ---");
            int response = scanner.nextInt();
            if (response == 1 )
            {
                System.out.println("Please enter the Number Order of your Cow!");
                System.out.println("--- it can be found near the type of animal ---");
                System.out.println("Number Order :");
                int id = scanner.nextInt();
                return this.controller.removeAnimal(id);
            }
            else if (response == 2)
            {
                System.out.println("Please enter the Number Order associated with your Bird!");
                System.out.println("--- it can be found near the type of animal ---");
                int id = scanner.nextInt();
                return this.controller.removeAnimal(id);
            }
            else if (response == 3)
            {
                System.out.println("Please enter the Number Order of your Pig!");
                System.out.println("--- it can be found near the type of animal ---");
                int id = scanner.nextInt();
                return this.controller.removeAnimal(id);
            }
        }
        catch (Exception e) {System.out.println(e);}
        return "Ooops!";
    }

    private void printable()
    {
        System.out.println("0. Exit.");
        System.out.println("1. Show all the animals.");
        System.out.println("2. Show all the Cows.");
        System.out.println("3. Show all the Birds.");
        System.out.println("4. Show all the Pigs.");
        System.out.println("5. Add an animal to the farm.");
        System.out.println("6. Remove an animal from the farm.");
        System.out.println("7. Filter all the animals with the weight ...");
    }
}

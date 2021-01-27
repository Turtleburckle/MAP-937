import controller.ProgramStateController;
import controller.ProgramStateControllerImplementation;
import exceptions.MyExceptions;
import model.ProgramState;
import model.command.Command;
import model.statements.Statement;
import model.type.INTtype;
import repository.ProgramStateRepository;
import repository.ProgramStateRepositoryImplementation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu(){ commands=new HashMap<>(); }
    public void addCommand(Command c){ commands.put(c.getKey(),c);}
    private void printMenu(){
        for(Command com : commands.values()){
            String line=String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }
    public void show(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("Input the option: ");
            String key=scanner.nextLine();
            Command com=commands.get(key);
            if (com==null){
                System.out.println("Invalid Option");
                continue; }
              com.execute();
//            if(key.equals("1")) {
//                Statement statement = EX.getExampleOne();
//                try {
//                    executeAndPrint(statement,"log1.txt");
//                } catch (MyExceptions myExceptions) {
//                    System.out.println(myExceptions.getMessage());
//                }
//            }
//            else if(key.equals("2")){
//                Statement statement = EX.getExampleTwo();
//                try {
//                    executeAndPrint(statement,"log2.txt");
//                } catch (MyExceptions myExceptions) {
//                    System.out.println(myExceptions.getMessage());
//                }
//            }
//            else if(key.equals("3")){
//                Statement statement = EX.getExampleThree();
//                try {
//                    executeAndPrint(statement,"log3.txt");
//                } catch (MyExceptions myExceptions) {
//                    System.out.println(myExceptions.getMessage());
//                }
//            }
        }
    }

    public static void executeAndPrint(Statement statement,String path) throws MyExceptions {
        Scanner myScanner = new Scanner(System.in);
        ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation(path);
        ProgramState finished = statement.execute(new ProgramState());
        programStateRepository.addProgramState(finished);
        ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);

        programStateController.executeALL();
        finished.getOutput().printValues();

    }

}
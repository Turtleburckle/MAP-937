import controller.ProgramStateController;
import controller.ProgramStateControllerImplementation;
import exceptions.MyExceptions;
import model.ProgramState;
import model.command.Command;
import model.command.ExitCommand;
import model.command.RunExample;
import model.expression.Expression;
import model.myStack.MyStack;
import model.statements.Statement;
import model.statements.VariableDeclarationStatement;
import model.type.INTtype;
import repository.ProgramStateRepository;
import repository.ProgramStateRepositoryImplementation;

import java.util.HashMap;
import java.util.Scanner;

public class MAIN {

    public static void main(String[] args){

//        try{
//            Statement statement = EX.getExampleOne();
//            executeAndPrint(statement);
//        }
//        catch(MyExceptions ERROR){
//            System.out.println(ERROR.getMessage());
//        }
//
//        try{
//            Statement statement = EX.getExampleTwo();
//            executeAndPrint(statement);
//        }
//        catch(MyExceptions ERROR){
//            System.out.println(ERROR.getMessage());
//        }
//
//        try{
//            Statement statement = EX.getExampleThree();
//            executeAndPrint(statement);
//        }
//        catch(MyExceptions ERROR){
//            System.out.println(ERROR.getMessage());
//        }
//        try{
//            ProgramState programState = new ProgramState();
//            MyStack<Statement> statements = EX.getExampleFour();
//            programState.setExecutableStack(statements);
//            Scanner myScanner = new Scanner(System.in);
//            System.out.println("Enter path name: ");
//            String path = myScanner.nextLine();
//            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation(path);
//            programStateRepository.addProgramState(programState);
//            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
//
//            programStateController.executeALL();
//            programState.getOutput().printValues();
//
//        } catch (MyExceptions myExceptions) {
//            myExceptions.printStackTrace();
////        }
        //LABORATORY 7
//        try{
//            System.out.println("Example 1\n");
//            System.out.println("------------------------------\n");
//            ProgramState programState = new ProgramState();
//            MyStack<Statement> statements = EX.getExampleFive();
//            programState.setExecutableStack(statements);
//            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation("somefile");
//            programStateRepository.addProgramState(programState);
//            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
//
//            programStateController.executeALL();
//            programState.getOutput().printValues();
//        } catch (MyExceptions myExceptions){myExceptions.printStackTrace();}
//        try{
//            System.out.println("Example 2\n");
//            System.out.println("------------------------------\n");
//            ProgramState programState = new ProgramState();
//            MyStack<Statement> statements = EX.getExampleSix();
//            programState.setExecutableStack(statements);
//            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation("somefile");
//            programStateRepository.addProgramState(programState);
//            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
//
//            programStateController.executeALL();
//            programState.getOutput().printValues();
//        } catch (MyExceptions myExceptions){myExceptions.printStackTrace();}
//        try{
//            System.out.println("Example 3\n");
//            System.out.println("------------------------------\n");
//            ProgramState programState = new ProgramState();
//            MyStack<Statement> statements = EX.getExampleSeven();
//            programState.setExecutableStack(statements);
//            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation("somefile");
//            programStateRepository.addProgramState(programState);
//            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
//
//            programStateController.executeALL();
//            programState.getOutput().printValues();
//        } catch (MyExceptions myExceptions){myExceptions.printStackTrace();}
//        try{
//            System.out.println("Example 4\n");
//            System.out.println("------------------------------\n");
//            ProgramState programState = new ProgramState();
//            MyStack<Statement> statements = EX.getExampleEight();
//            programState.setExecutableStack(statements);
//            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation("somefile");
//            programStateRepository.addProgramState(programState);
//            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
//
//            programStateController.executeALL();
//            programState.getOutput().printValues();
//        } catch (MyExceptions myExceptions){myExceptions.printStackTrace();}
//        try{
//            System.out.println("Example 5\n");
//            System.out.println("------------------------------\n");
//            ProgramState programState = new ProgramState();
//            MyStack<Statement> statements = EX.getExampleNine();
//            programState.setExecutableStack(statements);
//            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation("somefile");
//            programStateRepository.addProgramState(programState);
//            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
//
//            programStateController.executeALL();
//            programState.getOutput().printValues();
//        } catch (MyExceptions myExceptions){myExceptions.printStackTrace();}
        try{
            //System.out.println("Example\n");
            //System.out.println("------------------------------\n");
            ProgramState programState = new ProgramState();
            MyStack<Statement> statements = EX.getExampleTen();
            programState.setExecutableStack(statements);
            ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation("lab10");
            programStateRepository.addProgramState(programState);
            ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);
            programStateController.executeALL();
            programState.getOutput().printValues();
        } catch (MyExceptions myExceptions){myExceptions.printStackTrace();}
    }


    public static void executeAndPrint(Statement statement) throws MyExceptions {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter path name: ");
        String path = myScanner.nextLine();
        ProgramStateRepository programStateRepository = new ProgramStateRepositoryImplementation(path);
        ProgramState finished = statement.execute(new ProgramState());
        programStateRepository.addProgramState(finished);
        ProgramStateController programStateController = new ProgramStateControllerImplementation(programStateRepository);

        programStateController.executeALL();
        finished.getOutput().printValues();

    }
}

/*class Interpreter {
    public static void main(String[] args) {
        Statement ex1= EX.getExampleOne();
        ProgramState prg1 = new ProgramState(ex1);
        ProgramStateRepository repo1 = new ProgramStateRepositoryImplementation("log1.txt");
        repo1.addProgramState(prg1);
        ProgramStateController ctr1 = new ProgramStateControllerImplementation(repo1);

        Statement ex2=EX.getExampleTwo();
        ProgramState prg2 = new ProgramState(ex2);
        ProgramStateRepository repo2 = new ProgramStateRepositoryImplementation("log2.txt");
        repo2.addProgramState(prg2);
        ProgramStateController ctr2 = new ProgramStateControllerImplementation(repo2);

        Statement ex3= EX.getExampleThree();
        ProgramState prg3 = new ProgramState(ex3);
        ProgramStateRepository repo3 = new ProgramStateRepositoryImplementation("log3.txt");
        repo3.addProgramState(prg3);
        ProgramStateController ctr3 = new ProgramStateControllerImplementation(repo3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.show();
    }
}*/


package repository;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myList.MyList;
import model.myList.MyListImplementation;
import model.myStack.MyStack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProgramStateRepositoryImplementation implements ProgramStateRepository{
    private MyList<ProgramState> programStateList;
    private String filePath;

    public ProgramStateRepositoryImplementation(String path){
        this.programStateList = new MyListImplementation<>();
        this.filePath = path;
    }

    public ProgramStateRepositoryImplementation(ProgramState programState, String path){
        this.programStateList = new MyListImplementation<>();
        this.filePath = path;
        this.programStateList.add(programState);
    }

    @Override
    public ProgramStateRepository addProgramState(ProgramState programState){
        programStateList.add(programState);
        return this;
    }

    @Override
    public MyList<ProgramState> getProgramStates() {return programStateList;}

    @Override
    public void setProgramStates(MyList<ProgramState> listProgramState){
        this.programStateList = listProgramState;
    }

    @Override
    public boolean removeProgramState(){return false;}

    @Override
    public void logProgramStateExecutable(ProgramState programState) throws MyExceptions{
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)));
            logFile.write(programState.toString());
            logFile.close();
        }
        catch(IOException error){
            throw new MyExceptions(error.getMessage());
        }
    }
}

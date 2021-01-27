package repository;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myList.MyList;

import java.io.IOException;
import java.util.List;

public interface ProgramStateRepository {
    ProgramStateRepository addProgramState(ProgramState programState);

    MyList<ProgramState> getProgramStates();

    void setProgramStates(MyList<ProgramState> listProgramState);

    boolean removeProgramState();

    void logProgramStateExecutable(ProgramState programState) throws MyExceptions;

}
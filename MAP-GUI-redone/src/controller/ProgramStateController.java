package controller;

import exceptions.MyExceptions;
import model.ProgramState;

import java.util.List;

public interface ProgramStateController {
    void addProgramState(ProgramState programState);
    //ProgramState executeOne(ProgramState programState) throws MyExceptions;
    void executeALL() throws MyExceptions;
    void displayProgramState(ProgramState programState);

    ProgramState getFirstState();

    List<ProgramState> getAllStates();

    void oneStep() throws MyExceptions;
    void oneStepForGUI(int index) throws MyExceptions;
}

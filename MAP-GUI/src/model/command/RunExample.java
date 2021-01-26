package model.command;

import controller.ProgramStateController;
import exceptions.MyExceptions;

public class RunExample extends Command {
    private ProgramStateController controller;
    public RunExample(String key, String desc,ProgramStateController controller){
        super(key, desc);
        this.controller=controller;
    }
    @Override
    public void execute() {
        try{
            controller.executeALL(); }
        catch (MyExceptions error) {
            System.out.println(error.getMessage());
        }
    }
}
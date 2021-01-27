package model;

import exceptions.MyExceptions;
import model.expression.ValueExpression;
import model.myDictionary.MyDictionary;
import model.myDictionary.MyDictionaryImplementation;
import model.myList.MyList;
import model.myList.MyListImplementation;
import model.myStack.MyStack;
import model.myStack.MyStackImplementation;
import model.statements.PrintStatement;
import model.statements.Statement;
import model.type.INTtype;
import model.value.IntValue;
import model.value.Value;
import java.io.BufferedReader;

public class ProgramState {
    private MyStack<Statement> executableStack;
    private MyDictionary<String,Value> symbolicTable;
    private MyList<Value> output;
//    private Statement originalProgram;
    private MyDictionary<String, BufferedReader> fileTable;
    private MyDictionary<Integer,Value> MYheap;
    public static int free = 1;
    private static int NEXT_ID = 1;
    private int ID;

    public ProgramState(MyStack<Statement> stack, MyDictionary<String, Value> clone, MyList<Value> output, MyDictionary<String, BufferedReader> clone1, MyDictionary<Integer, Value> heap) {
        this.ID = getNextID();
        this.executableStack = stack;
        this.symbolicTable = clone;
        this.output = output;
        this.fileTable = clone1;
        this.MYheap = heap;
    }

    public void setExecutableStack(MyStack<Statement> executableStack) {
        this.executableStack = executableStack;
    }
    public ProgramState(){
        this.ID = getNextID();
        this.executableStack = new MyStackImplementation<>();
        this.symbolicTable = new MyDictionaryImplementation<>();
        this.output = new MyListImplementation<>();
  //      this.originalProgram = new PrintStatement(new ValueExpression(new IntValue(0)));
        this.fileTable = new MyDictionaryImplementation<>();
        this.MYheap = new MyDictionaryImplementation<>();
    }

    public ProgramState(MyStack<Statement> stack , MyDictionary<String,Value> symbolicTable, MyList<Value> output, Statement program, MyDictionary<String,BufferedReader> fileTable,MyDictionary<Integer,Value> myHEAP){
        this.ID = getNextID();
        this.executableStack = stack;
        this.symbolicTable = symbolicTable;
        this.output = output;
    //    this.originalProgram = program;
        this.fileTable = fileTable;
        this.MYheap = myHEAP;
    }


    public ProgramState(Statement statement){
        this.ID = getNextID();
        this.executableStack = new MyStackImplementation<>();
        executableStack.push(statement);
        this.symbolicTable = new MyDictionaryImplementation<>();
        this.output = new MyListImplementation<>();
      //  this.originalProgram = originalProgram;
        this.fileTable = new MyDictionaryImplementation<>();
        this.MYheap = new MyDictionaryImplementation<>();
    }

    public boolean isNotCompleted(){return !executableStack.isEmpty();}
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
    public static int getNextID () {NEXT_ID++;return NEXT_ID-1;}
    public MyStack<Statement> getExecutableStack() {return executableStack;}
    public MyDictionary<String,Value> getSymbolicTable() {return symbolicTable;}
    public MyList<Value> getOutput() {return output;}
    //public Statement getOriginalProgram() {return originalProgram;}
    public MyDictionary<String,BufferedReader> getFileTable(){return fileTable;}
    public MyDictionary<Integer,Value> getHeap(){return MYheap;}

    public ProgramState oneStep() throws MyExceptions{
        if(executableStack.isEmpty()){
            throw  new MyExceptions("The program state is empty!\n");
        }
        else{
            Statement currentStatement = executableStack.pop();
            return currentStatement.execute(this);
        }
    }

    @Override
    public String toString() {
        String programStateString = "ProgramState " + this.getID() + "\n";
        String executableStackString = "Executable Stack " + this.getID()+ "\n" +"--------------------"+ "\n" + this.executableStack.toString();
        String symbolicTableString = "Symbolic Table " + this.getID()+ "\n" +"--------------------"+ "\n" + this.symbolicTable.toString();
        String outputString = "Output " + this.getID()+ "\n" +"--------------------"+ "\n" + this.output.toString();
        String heapString = "Heap Table " + this.getID()+ "\n" +"--------------------"+ "\n" + this.getHeap().toString();
        return programStateString + executableStackString + symbolicTableString + outputString + heapString + "\n";
        /*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ProgramState ").append(getID()).append(":\n")
                .append("Executable Stack ").append(getID()).append(":\n").append(executableStack)
                .append("SymbolicTable ").append(getID()).append(":\n").append(symbolicTable)
                .append("Output ").append(getID()).append(":\n").append(output)
                .append("Heap ").append(getID()).append(":\n").append(MYheap).append("]\n5").append("_____________________\n\n");*/
        //return  stringBuilder.toString();
                //"[ProgramState:\n ExecutableStack:\n" + executableStack + "SymbolicTable:\n" + symbolicTable + "Output:\n" + output + "Heap:\n" + MYheap + "]";
    }
}

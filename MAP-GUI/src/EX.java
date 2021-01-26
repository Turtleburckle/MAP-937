import model.ProgramState;
import model.expression.*;
import model.myStack.MyStack;
import model.myStack.MyStackImplementation;
import model.operation.OperationsWithIntegerExpressions;
import model.operation.OperationsWithIntegers;
import model.statements.*;
import model.type.BOOLtype;
import model.type.INTtype;
import model.type.REFtype;
import model.type.STRINGtype;
import model.value.*;

import javax.swing.plaf.nimbus.State;
import java.util.Stack;

public class EX {
    public static Statement getExampleOne(){
        // int v;
        //v=2;
        //Print(v)
        System.out.println("EXAMPLE 1\n");
        return new CompoundStatement(new VariableDeclarationStatement("v",new INTtype()),
                new CompoundStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new
                        VariableExpression("v"))));
    }
    public static Statement getExampleTwo(){
        //int a;
        //a=2+3*5;
        //int b;
        //b=a-4/2 + 7;
        //Print(b)
        System.out.println("EXAMPLE 2\n");
        return new CompoundStatement( new VariableDeclarationStatement("a",new INTtype()),
                new CompoundStatement(new VariableDeclarationStatement("b",new INTtype()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), OperationsWithIntegers.MULTIPLICATION), OperationsWithIntegers.SUM)),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression(new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)), OperationsWithIntegers.SUM)), new PrintStatement(new VariableExpression("b"))))));

    }
    public static Statement getExampleThree(){
        //bool a;
        //a=true;
        //int v;
        //If a Then v=2 Else v=3;
        //Print(v)
        System.out.println("EXAMPLE 3\n");
        return new CompoundStatement(new VariableDeclarationStatement("a",new BOOLtype()),
                new CompoundStatement(new VariableDeclarationStatement("v", new INTtype()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
    }
    public static MyStack<Statement> getExampleFour(){
        //string varf;
        //varf="test.in";
        //openRFile(varf);
        //int varc;
        //readFile(varf,varc);print(varc);
        //readFile(varf,varc);print(varc)
        //closeRFile(varf)
        System.out.println("LABORATORY 3\n");
        Statement firstStatement = new VariableDeclarationStatement("varf",new STRINGtype());
        Statement secondStatement = new AssignStatement("varf",new ValueExpression(new StringValue("test.in")));
        Statement thirdStatement = new OpenFileStatement(new VariableExpression("varf"));
        Statement fourthStatement = new VariableDeclarationStatement("varc",new INTtype());
        Statement fifthStatement1 = new ReadFileStatement(new VariableExpression("varf"),"varc");
        Statement fifthStatement2 = new PrintStatement(new VariableExpression("varc"));
        Statement fifthStatement = new CompoundStatement(fifthStatement1,fifthStatement2);
        Statement sixthStatement1 = new ReadFileStatement(new VariableExpression("varf"),"varc");
        Statement sixthStatement2 = new PrintStatement(new VariableExpression("varc"));
        Statement sixthStatement = new CompoundStatement(sixthStatement1,sixthStatement2);
        Statement seventhStatement = new CloseFileStatement(new VariableExpression("varf"));
        MyStackImplementation<Statement> statements = new MyStackImplementation<>();
        statements.push(seventhStatement);
        statements.push(sixthStatement);
        statements.push(fifthStatement);
        statements.push(fourthStatement);
        statements.push(thirdStatement);
        statements.push(secondStatement);
        statements.push(firstStatement);
        return statements;
    }

public static MyStack<Statement> getExampleFive(){
//        Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
//        At the end of execution: Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and Out={(1,int),(2,Ref int)}
        Statement firstStatement = new VariableDeclarationStatement("v",new REFtype(new INTtype()));
        Statement secondStatement = new NewStatement("v",new ValueExpression(new IntValue(20)));
        Statement thirdStatement = new VariableDeclarationStatement("a", new REFtype(new REFtype(new INTtype())));
        Statement fourthStatement = new NewStatement("a",new VariableExpression("v"));
        Statement fifthStatement = new PrintStatement(new VariableExpression("v"));
        Statement sixthStatement = new PrintStatement(new VariableExpression("a"));
        MyStackImplementation<Statement> statement = new MyStackImplementation<>();
        statement.push(sixthStatement);
        statement.push(fifthStatement);
        statement.push(fourthStatement);
        statement.push(thirdStatement);
        statement.push(secondStatement);
        statement.push(firstStatement);
        return statement;
    }

    public static MyStack<Statement> getExampleSix(){
//        Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
//        At the end of execution: Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and Out={20, 25}
        Statement firstStatement = new VariableDeclarationStatement("v", new REFtype(new INTtype()));
        Statement secondStatement = new NewStatement("v", new ValueExpression(new IntValue(20)));
        Statement thirdStatement = new VariableDeclarationStatement("a",new REFtype(new REFtype(new INTtype())));
        Statement fourthStatement = new NewStatement("a",new VariableExpression("v"));
        Statement fifthStatement = new PrintStatement(new ReadHeapExpression(new VariableExpression("v")));
        Statement sixthStatement = new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),new ValueExpression(new IntValue(5)),OperationsWithIntegers.SUM));
        MyStackImplementation<Statement> statements = new MyStackImplementation<>();
        statements.push(sixthStatement);
        statements.push(fifthStatement);
        statements.push(fourthStatement);
        statements.push(thirdStatement);
        statements.push(secondStatement);
        statements.push(firstStatement);
        return statements;
    }

    public static MyStack<Statement> getExampleSeven(){
//        Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
//        At the end of execution: Heap={1->30}, SymTable={v->(1,int)} and Out={20, 35}
        Statement firstStatement = new VariableDeclarationStatement("v",new REFtype(new INTtype()));
        Statement secondStatement = new NewStatement("v",new ValueExpression(new IntValue(20)));
        Statement thirdStatement = new PrintStatement(new ReadHeapExpression(new VariableExpression("v")));
        Statement fourthStatement = new WriteHeapStatement("v",new ValueExpression(new IntValue(30)));
        Statement fifthStatement = new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5)),OperationsWithIntegers.SUM));
        MyStackImplementation<Statement>statements  = new MyStackImplementation<>();
        statements.push(fifthStatement);
        statements.push(fourthStatement);
        statements.push(thirdStatement);
        statements.push(secondStatement);
        statements.push(firstStatement);
        return statements;
    }

    public static MyStack<Statement> getExampleEight(){
//        Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        Statement statement = new VariableDeclarationStatement("v",new REFtype(new INTtype()));
        Statement statement1 = new NewStatement("v",new ValueExpression(new IntValue(20)));
        Statement statement2 = new VariableDeclarationStatement("a",new REFtype(new REFtype(new INTtype())));
        Statement statement3 = new NewStatement("a",new VariableExpression("v"));
        Statement statement5 = new NewStatement("v",new ValueExpression(new IntValue(30)));
        Statement statement4 = new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))));
        MyStackImplementation<Statement> statements = new MyStackImplementation<>();
        statements.push(statement4);
        statements.push(statement5);
        statements.push(statement3);
        statements.push(statement2);
        statements.push(statement1);
        statements.push(statement);
        return statements;
    }

    public static MyStack<Statement> getExampleNine(){
//        int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        Statement firstStatement = new VariableDeclarationStatement("v",new INTtype());
        Statement secondStatement = new AssignStatement("v",new ValueExpression(new IntValue(4)));
        Statement thirdStatement = new WhileStatement(new RelationalExpressions(new VariableExpression("v"),new ValueExpression(new IntValue(0)),OperationsWithIntegerExpressions.GREATERETHAN),
                new CompoundStatement(new PrintStatement(new VariableExpression("v")),new AssignStatement("v",
                        new ArithmeticExpression(new VariableExpression("v"),new ValueExpression(new IntValue(1)),OperationsWithIntegers.DIFFERENCE))));
        MyStackImplementation<Statement> statements = new MyStackImplementation<>();
        Statement fourthStatement = new PrintStatement(new VariableExpression("v"));
        statements.push(fourthStatement);
        statements.push(thirdStatement);
        statements.push(secondStatement);
        statements.push(firstStatement);

        return statements;
    }

    public static MyStack<Statement> getExampleTen(){
        // int v; Ref int a; v=10;new(a,22);fork(wH(a,30);v=32;print(v);print(rH(a))); print(v);print(rH(a))
        Statement firstStatement = new VariableDeclarationStatement("v", new INTtype());
        Statement secondStatement = new VariableDeclarationStatement("a", new REFtype(new INTtype()));
        Statement thirdStatement = new AssignStatement("v",new ValueExpression(new IntValue(10)));
        Statement fourthStatement =  new NewStatement("a",new ValueExpression(new IntValue(22)));
        Statement fifthStatement = new WriteHeapStatement("a", new ValueExpression(new IntValue(30)));
        Statement sixthStatement = new AssignStatement("v",new ValueExpression(new IntValue(32)));
        Statement seventhStatement = new PrintStatement(new VariableExpression("v"));
        Statement eighthStatement = new PrintStatement(new ReadHeapExpression(new VariableExpression("a")));
        Statement ninthStatement = new PrintStatement(new VariableExpression("v"));
        Statement tenthStatement = new PrintStatement(new ReadHeapExpression(new VariableExpression("a")));
        Statement fork = new ForkStatement(new CompoundStatement(fifthStatement, new CompoundStatement(sixthStatement, new CompoundStatement(seventhStatement, eighthStatement))));

        MyStackImplementation<Statement> statements = new MyStackImplementation<>();
        statements.push(tenthStatement);
        statements.push(ninthStatement);
        statements.push(fork);
        statements.push(fourthStatement);
        statements.push(thirdStatement);
        statements.push(secondStatement);
        statements.push(firstStatement);
        return statements;
    }

}


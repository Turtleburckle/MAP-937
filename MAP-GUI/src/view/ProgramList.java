package view;

import controller.ProgramStateController;
import controller.ProgramStateControllerImplementation;
import exceptions.MyExceptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.ProgramState;
import model.myDictionary.MyDictionaryImplementation;
import model.statements.Statement;
import model.type.Type;
import repository.ProgramStateRepositoryImplementation;

import java.util.ArrayList;
import java.util.List;

public class ProgramList {

    public TableView heapTableView;
    public Button oneStepButton;
    public ListView executableTable;
    public TableView symbolicTable;
    public ListView listOfProgramStatesID;
    public ListView fileTable;
    public ListView outputList;
    public TextField nrOfProgramStates;
    private String statementName;
    public Text StatementName;
    private ProgramStateController programStateController;
    private Integer index = 0;
    private List<Statement> statementList = new ArrayList<>();

    @FXML
    private ListView ProblemsListView;

    public List<Statement> getStatementList() {
        return statementList;
    }

    @FXML
    ListView<String> statementString;

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        this.statementList.add(EXview.getExampleOne());
        list.add(EXview.getTextExampleOne());
        this.statementList.add(EXview.getExampleTwo());
        list.add(EXview.getTextExampleTwo());
        this.statementList.add(EXview.getExampleThree());
        list.add(EXview.getTextExampleThree());
        this.statementList.add(EXview.getExampleFourCompound());
        list.add(EXview.getTextExampleFour());
        this.statementList.add(EXview.getExampleFiveCompound());
        list.add(EXview.getTextExampleFive());
        list.add(EXview.getTextExampleTen());
        this.statementList.add(EXview.getExampleTenCompound());

        TableColumn firstColumn = new TableColumn("Address");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("string1"));
        TableColumn secondColumn = new TableColumn("Value");
        secondColumn.setCellValueFactory(new PropertyValueFactory<>("string2"));
        heapTableView.getColumns().addAll(firstColumn, secondColumn);

        TableColumn firstColumn2 = new TableColumn("Name");
        firstColumn2.setCellValueFactory(new PropertyValueFactory<>("string1"));
        TableColumn secondColumn2 = new TableColumn("Value");
        secondColumn2.setCellValueFactory(new PropertyValueFactory<>("string2"));
        symbolicTable.getColumns().addAll(firstColumn2, secondColumn2);

        ProblemsListView.setItems(list);
        ProblemsListView.getSelectionModel().selectFirst();
    }

    public void getSelectedProblem() {
        int index = ProblemsListView.getSelectionModel().getSelectedIndex();
        this.statementName = new String(ProblemsListView.getSelectionModel().getSelectedItem().toString());
        StatementName.setText(this.statementName);
        this.programStateController = new ProgramStateControllerImplementation(
                new ProgramStateRepositoryImplementation(new ProgramState(statementList.get(index)), "gui.txt")
        );
        populate();
    }

    private void populate() {
        try {
            nrOfProgramStates.setText(String.valueOf(programStateController.getAllStates().size()));
            listOfProgramStatesID.getItems().clear();

            programStateController.getFirstState();

            heapTableView.getItems().clear();
            executableTable.getItems().clear();
            symbolicTable.getItems().clear();
            outputList.getItems().clear();
            fileTable.getItems().clear();
            this.programStateController.getFirstState().getHeap().getContent().forEach((integer, value) -> heapTableView.getItems().add(new StringStringRow(String.valueOf(integer), value.toString())));
            this.programStateController.getFirstState().getExecutableStack().getContent().forEach(statement -> executableTable.getItems().add(statement.toString()));
            this.programStateController.getFirstState().getOutput().getContent().forEach(value -> outputList.getItems().add(value.toString()));
            this.programStateController.getAllStates().forEach(programState -> listOfProgramStatesID.getItems().add(String.valueOf(programState.getID())));
            this.programStateController.getAllStates().stream().filter(programState -> programState.getID() == index).findFirst().ifPresent(programState ->
            {
                programState.getSymbolicTable().getContent().forEach((s, value) -> symbolicTable.getItems().add(new StringStringRow(s, value.toString())));
                programState.getFileTable().getContent().forEach((s, bufferedReader) -> fileTable.getItems().add(s));
            });
        }
        catch(Exception e)
        {

        }
    }

//    public void populateHeapTable(Statement selectedStatement){
//
//
//        //String execution = new String();
//        //execution =  selectedStatement.toString();
//    }

    public void oneStepClicked(MouseEvent mouseEvent) {

        try {
            this.programStateController.oneStep();
        } catch (MyExceptions myExceptions) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(myExceptions.getMessage());
            alert.show();
        }
        populate();
    }

    public void selectedIDchanged(MouseEvent mouseEvent) {
        index = Integer.valueOf(listOfProgramStatesID.getSelectionModel().getSelectedItems().get(0).toString());
        populate();
    }
}

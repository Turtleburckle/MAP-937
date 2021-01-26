package controller;

import exceptions.MyExceptions;
import model.ProgramState;
import model.myList.MyList;
import model.myList.MyListImplementation;
import model.myStack.MyStack;
import model.statements.Statement;
import model.value.RefValue;
import model.value.Value;
import repository.ProgramStateRepository;
import repository.ProgramStateRepositoryImplementation;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ProgramStateControllerImplementation implements ProgramStateController {
    private ProgramStateRepository repository;
    private ExecutorService executor;

    public ProgramStateControllerImplementation() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter path name: ");
        String path = myScanner.nextLine();
        this.repository = new ProgramStateRepositoryImplementation(path);
    }

    public ProgramStateControllerImplementation(ProgramStateRepository programStateRepository) {
        this.repository = programStateRepository;
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        Map<Integer, Value> result = new HashMap<>();
        heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .forEach(integerValueEntry -> result.put(integerValueEntry.getKey(), integerValueEntry.getValue()));
        heap.entrySet().stream()
                .filter(e -> e.getValue() instanceof RefValue)
                .filter(e -> heap.containsKey(((RefValue) e.getValue()).getAddress()))
                .forEach(value -> result.put(((RefValue) value.getValue()).getAddress(), heap.get(((RefValue) value.getValue()).getAddress())));
        return result;
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addProgramState(ProgramState programState) {
        repository.addProgramState(programState);
    }

//    @Override
//    public ProgramState executeOne(ProgramState programState) throws MyExceptions{
//        MyStack<Statement> myStack = programState.getExecutableStack();
//        displayProgramState(programState);
//        if(myStack.isEmpty()){
//            throw new MyExceptions("Program state is empty!");
//        }
//        Statement statement = myStack.pop();
//        return statement.execute(programState);
//    }

//    @Override
//    public void executeALL()throws MyExceptions{
//        ProgramState programState = repository.getProgramStates().get(0);
//        repository.logProgramStateExecutable(programState);
//        while(! programState.getExecutableStack().isEmpty()){
//            programState.oneStep();
//            System.out.println(programState);
//            repository.logProgramStateExecutable(programState);
//            programState.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(programState.getSymbolicTable().getContent().values()), programState.getHeap().getContent()));
//
//        }
//    }

    @Override
    public void executeALL() throws MyExceptions {
        executor = Executors.newFixedThreadPool(5);
        List<ProgramState> programList = removeCompletedPrograms(repository.getProgramStates().getContent());
        while (programList.size() > 0) {
            oneStepForAllPrograms(programList);
            programList = removeCompletedPrograms(repository.getProgramStates().getContent());
        }
        executor.shutdownNow();
        repository.setProgramStates(new MyListImplementation<>(programList));
    }

    public void oneStepForAllPrograms(List<ProgramState> programstates) throws MyExceptions {

        programstates.forEach(prg -> {
            try {
                repository.logProgramStateExecutable(prg);
            } catch (MyExceptions myExceptions) {
                myExceptions.printStackTrace();
            }
        });

        executor = Executors.newFixedThreadPool(5);

        List<Callable<ProgramState>> callList = programstates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newProgramList = null;
        StringBuilder error = new StringBuilder();
        try {
            newProgramList = executor.invokeAll(callList).stream()
                    .map(programStateFuture -> {
                        try {
                            return programStateFuture.get();
                        } catch (Exception e) {
                            error.append(e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());


        } catch (InterruptedException e) {
            error.append(error);
        }
        finally {
            if(!error.toString().equals("")) {
                throw new MyExceptions(error.toString());
            }
        }
        programstates.addAll(Objects.requireNonNull(newProgramList));
        programstates.forEach(prg -> {
            try {
                repository.logProgramStateExecutable(prg);
            } catch (MyExceptions myExceptions) {
                myExceptions.printStackTrace();
            }
        });
        repository.setProgramStates(new MyListImplementation<>(programstates));
    }

    @Override
    public void displayProgramState(ProgramState programState) {
        System.out.println(programState);
    }

    @Override
    public ProgramState getFirstState() {
        return repository.getProgramStates().get(0);
    }

    @Override
    public List<ProgramState> getAllStates() {
        return repository.getProgramStates().getContent();
    }

    @Override
    public void oneStep() throws MyExceptions {
        this.oneStepForAllPrograms(this.getAllStates());
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public boolean oneStepForGUI() throws MyExceptions{
        List<ProgramState> programStates = removeCompletedPrograms((List<ProgramState>) repository.getProgramStates());
        if (programStates.size() > 0){
            oneStepForAllPrograms(programStates);
            removeCompletedPrograms((List<ProgramState>) repository.getProgramStates());
            return true;
        }
        else{
            executor.shutdownNow();
            repository.setProgramStates((MyList<ProgramState>) programStates);
            return false;
        }

    }
}

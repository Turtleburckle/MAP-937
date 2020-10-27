package com.company;

import com.company.repository.Repository;
import com.company.view.View;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    View ui = new View();
	    ui.run();
    }
}

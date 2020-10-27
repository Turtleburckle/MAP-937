package com.company.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface RepositoryInterface {
    void readFile() throws FileNotFoundException;
    void writeFile() throws IOException;

}

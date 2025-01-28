package com.github.curriculeon;

import java.io.File;

public enum DirectoryReference {
    RESOURCE("/src/main/resources"),
    TARGET("/target/"),
    SRC_MAIN("/src/main");

    private final String filePath;

    DirectoryReference(final String filePath) {
        this.filePath = filePath;
    }

    public File getFile(String fileName) {
        return new File(this.filePath + "/" + fileName);
    }
}

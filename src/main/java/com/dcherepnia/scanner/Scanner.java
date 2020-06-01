package com.dcherepnia.scanner;

import com.dcherepnia.entity.file.CountedFile;
import com.dcherepnia.entity.folder.CountedFolder;

import java.io.IOException;
import java.nio.file.Path;

public interface Scanner {
    CountedFolder scanDirectory(Path path);

    CountedFile scanSingleFile(Path path) throws IOException;
}

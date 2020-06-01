package com.dcherepnia.printer;

import com.dcherepnia.entity.file.CountedFile;
import com.dcherepnia.entity.folder.CountedFolder;

public interface CountedEntityPrinter {

    void print(CountedFile file);

    void print(CountedFolder folder);
}

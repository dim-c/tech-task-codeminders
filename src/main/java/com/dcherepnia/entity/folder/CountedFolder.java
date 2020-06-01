package com.dcherepnia.entity.folder;

import com.dcherepnia.entity.file.CountedFIleImpl;
import com.dcherepnia.entity.file.CountedFile;

import java.util.List;

public interface CountedFolder {

    void addFile(CountedFIleImpl countedFile);

    String getName();

    long getLinesCount();

    List<CountedFolder> getSubFolders();

    List<CountedFile> getCountedFiles();

    boolean hasFiles();

    boolean hasSubFolders();

    void appendCount(long count);

    void addFolder(CountedFolder folder);
}

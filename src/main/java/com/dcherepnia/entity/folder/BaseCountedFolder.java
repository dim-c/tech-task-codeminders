package com.dcherepnia.entity.folder;

import com.dcherepnia.entity.file.CountedFIleImpl;
import com.dcherepnia.entity.file.CountedFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

abstract class BaseCountedFolder implements CountedFolder {
    private final String name;
    private final List<CountedFolder> subFolders = new ArrayList<>();
    private final List<CountedFile> countedFiles = new ArrayList<>();
    AtomicLong linesCount = new AtomicLong(0);

    BaseCountedFolder(String name) {
        this.name = name;
    }

    public void addFile(CountedFIleImpl countedFile) {
        countedFiles.add(countedFile);
        appendCount(countedFile.getLinesCount());
    }

    @Override
    public void addFolder(CountedFolder folder) {
        subFolders.add(folder);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getLinesCount() {
        return linesCount.get();
    }

    @Override
    public List<CountedFolder> getSubFolders() {
        return subFolders;
    }

    @Override
    public List<CountedFile> getCountedFiles() {
        return countedFiles;
    }

    @Override
    public boolean hasFiles() {
        return !countedFiles.isEmpty();
    }

    @Override
    public boolean hasSubFolders() {
        return !subFolders.isEmpty();
    }

    public abstract void appendCount(long count);
}

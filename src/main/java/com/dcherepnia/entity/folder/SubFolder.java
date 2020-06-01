package com.dcherepnia.entity.folder;

public class SubFolder extends BaseCountedFolder {

    private final CountedFolder parentFolder;

    public SubFolder(String name, CountedFolder parentFolder) {
        super(name);
        this.parentFolder = parentFolder;
    }

    @Override
    public void appendCount(long count) {
        parentFolder.appendCount(count);
        linesCount.getAndAdd(count);
    }
}


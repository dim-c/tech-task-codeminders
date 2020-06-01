package com.dcherepnia.entity.folder;

public class RootFolder extends BaseCountedFolder {

    public RootFolder(String name) {
        super(name);
    }

    @Override
    public void appendCount(long count) {
        linesCount.getAndAdd(count);
    }
}

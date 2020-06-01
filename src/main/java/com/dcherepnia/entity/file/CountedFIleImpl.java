package com.dcherepnia.entity.file;

public class CountedFIleImpl implements CountedFile {

    private final String name;
    private final long count;

    public CountedFIleImpl(String name, long count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public long getLinesCount() {
        return count;
    }

    @Override
    public String getName() {
        return name;
    }
}

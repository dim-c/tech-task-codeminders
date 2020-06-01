package com.dcherepnia.printer;

import com.dcherepnia.entity.file.CountedFile;
import com.dcherepnia.entity.folder.CountedFolder;

import java.util.stream.Collectors;

import static com.dcherepnia.util.Delimiters.*;

public class CountedEntityPrinterImpl implements CountedEntityPrinter {

    @Override
    public void print(CountedFile file) {
        System.out.println(fileToString(file));
    }

    private String fileToString(CountedFile file) {
        return file.getName() + " : " + file.getLinesCount();
    }

    @Override
    public void print(CountedFolder folder) {
        StringBuilder builder = new StringBuilder();
        builder.append(folder.getName()).append(" : ").append(folder.getLinesCount());
        if (folder.hasFiles()) {
            builder.append(NEW_LINE_WITH_TAB);
            builder.append(folder.getCountedFiles().stream().map(this::fileToString)
                    .collect(Collectors.joining(NEW_LINE_WITH_TAB)));
        }
        if (folder.hasSubFolders()) {
            builder.append(NEW_LINE_WITH_TAB);
            builder.append(folder.getSubFolders().stream().map(f -> toStringWithIntentions(2, f))
                    .collect(Collectors.joining(NEW_LINE_WITH_TAB)));
        }
        System.out.println(builder.toString());
    }

    private String toStringWithIntentions(int intentions, CountedFolder folder) {
        StringBuffer tab = new StringBuffer(LINE_SEPARATOR);
        for (int i = 0; i < intentions; i++) {
            tab.append(TAB);
        }
        StringBuilder builder = new StringBuilder()
                .append(folder.getName())
                .append(" : ")
                .append(folder.getLinesCount());
        if (folder.hasFiles()) {
            builder.append(tab);
            builder.append(folder.getCountedFiles().stream().map(this::fileToString)
                    .collect(Collectors.joining(tab)));
        }
        if (folder.hasSubFolders()) {
            builder.append(tab);
            builder.append(folder.getSubFolders().stream().map(f -> toStringWithIntentions(intentions + 1, f))
                    .collect(Collectors.joining(tab)));
        }
        return builder.toString();
    }
}

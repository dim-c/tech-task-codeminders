package com.dcherepnia.scanner;

import com.dcherepnia.entity.file.CountedFIleImpl;
import com.dcherepnia.entity.file.CountedFile;
import com.dcherepnia.entity.folder.CountedFolder;
import com.dcherepnia.entity.folder.RootFolder;
import com.dcherepnia.entity.folder.SubFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dcherepnia.util.Delimiters.LINE_SEPARATOR;

public class ScannerImpl implements Scanner {

    private final String REGEX_FOR_COMMENTS = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/|\n(?m)^\\s*?$";
    public static final String FILE_EXTENSION = ".java";

    @Override
    public CountedFolder scanDirectory(Path path) {
        CountedFolder rootFolder = new RootFolder(path.getFileName().toString());
        scanSubDirectory(path, rootFolder);
        return rootFolder;
    }

    private void scanSubDirectory(Path path, CountedFolder rootFolder) {
        try (Stream<Path> paths = Files.list(path).parallel()) {
            List<Path> result = paths.filter(Files::isRegularFile)
                    .filter(Files::isReadable).filter(p -> p.toString().endsWith(FILE_EXTENSION)).collect(Collectors.toList());
            result.forEach(p -> {
                try {
                    scanFileInFolder(p, rootFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<Path> paths = Files.list(path).parallel()) {
            List<Path> result = paths.filter(Files::isDirectory)
                    .filter(Files::isReadable).collect(Collectors.toList());
            result.forEach(p -> {
                CountedFolder subFolder = new SubFolder(p.getFileName().toString(), rootFolder);
                rootFolder.addFolder(subFolder);
                scanSubDirectory(p, subFolder);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public CountedFile scanSingleFile(Path path) throws IOException {
        long count = scanFile(path);
        return new CountedFIleImpl(path.getFileName().toString(), count);
    }


    private void scanFileInFolder(Path path, CountedFolder folder) throws IOException {
        long count = scanFile(path);
        folder.addFile(new CountedFIleImpl(path.getFileName().toString(), count));
    }

    private long scanFile(Path path) throws IOException {
        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining(LINE_SEPARATOR));
        lines.close();
        //delete all comments
        data = data.replaceAll(REGEX_FOR_COMMENTS, "");
        return Arrays.stream(data.split(LINE_SEPARATOR)).filter(s -> !s.trim().isEmpty()).count();

    }
}

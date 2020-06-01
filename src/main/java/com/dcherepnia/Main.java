package com.dcherepnia;

import com.dcherepnia.printer.CountedEntityPrinter;
import com.dcherepnia.printer.CountedEntityPrinterImpl;
import com.dcherepnia.scanner.Scanner;
import com.dcherepnia.scanner.ScannerImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.dcherepnia.scanner.ScannerImpl.FILE_EXTENSION;

public class Main {

    public static void main(String... args) throws IOException {
        if (args.length == 0) {
            throw new RuntimeException("Please, provide a directory or file path");
        }
        Path path = getPathIfCorrect(args[0]);
        Scanner scanner = new ScannerImpl();
        CountedEntityPrinter printer = new CountedEntityPrinterImpl();
        if (Files.isDirectory(path)) {
            printer.print(scanner.scanDirectory(path));
        } else {
            printer.print(scanner.scanSingleFile(path));
        }
    }

    private static Path getPathIfCorrect(String pathArg) {
        Path path = Paths.get(pathArg);
        if (!Files.exists(path)) {
            path = Paths.get(path.toString() + FILE_EXTENSION);
            if (!Files.exists(path)) {
                throw new RuntimeException("Path is incorrect");
            }
        }
        return path;
    }
}

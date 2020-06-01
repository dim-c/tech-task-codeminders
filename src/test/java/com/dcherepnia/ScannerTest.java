package com.dcherepnia;


import com.dcherepnia.entity.file.CountedFile;
import com.dcherepnia.entity.folder.CountedFolder;
import com.dcherepnia.scanner.Scanner;
import com.dcherepnia.scanner.ScannerImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class ScannerTest {

    @Test
    public void checkThatScannerCountsLiensFromFilesInDirectoryCorrect() {
        Scanner scanner = new ScannerImpl();
        Path resourceDirectory = Paths.get("src", "test", "resources/root");
        CountedFolder countedFolder = scanner.scanDirectory(resourceDirectory);
        Assert.assertEquals(21, countedFolder.getLinesCount());
        Assert.assertEquals(17, countedFolder.getSubFolders().get(0).getLinesCount());
        Assert.assertEquals(9, countedFolder.getSubFolders().get(0).getSubFolders().get(0).getLinesCount());
        Assert.assertEquals(4, countedFolder.getSubFolders().get(0).getSubFolders().get(1).getLinesCount());
    }

    @Test
    public void checkThatScannerCountsLiensFromSingleFileCorrect() throws IOException {
        Scanner scanner = new ScannerImpl();
        Path resourceDirectory = Paths.get("src", "test", "resources/Dave.java");
        CountedFile countedFile = scanner.scanSingleFile(resourceDirectory);
        Assert.assertEquals(4, countedFile.getLinesCount());
    }
}
package com.mlick.javademo.java8.io;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class RecursiveFileLineReaderTest {

    @Test
    public void testReadAllLineFromAllFilesRecursively() throws Exception {
        final List<String> strings = RecursiveFileLineReader.readAllLineFromAllFilesRecursively(".", ".java");
        assertFalse(strings.isEmpty());
    }
}
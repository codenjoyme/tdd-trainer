package com.apofig.tddtrainer.service;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * User: sanja
 * Date: 11.09.13
 * Time: 23:29
 */
public class WithFile {
    private final File file;

    public WithFile(String fileName) {
        this.file = openFile(fileName);
    }

    private File openFile(String fileName) {
        try {
            URL resource = getClass().getClassLoader().getResource(fileName);
            if (resource == null) {
                File file = new File(fileName);
                file.createNewFile();
                return file;
            }
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("File name: " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("File name: " + fileName, e);
        }
    }

    public void save(String data) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String load() {
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            return new String(chars);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<String> loadSplitted(String separator) {
        return Arrays.asList(load().split(separator));
    }
}

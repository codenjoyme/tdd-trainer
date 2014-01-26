package com.apofig.tddtrainer.service;

import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 23:28
 */
public class WithFileTest {

    @Test
    public void shouldWork() {
        // given
        File file = new File("target\\1.txt");
        file.delete();
        WithFile withFile = new WithFile(file.getAbsolutePath());

        // when
        withFile.save("1\n2\n3\n4\n");

        // then
        assertEquals("1\n2\n3\n4\n", withFile.load());
        assertEquals("[1, 2, 3, 4]", withFile.loadSplitted("\n").toString());
    }

}

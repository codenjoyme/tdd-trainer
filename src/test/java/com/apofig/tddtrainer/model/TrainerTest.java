package com.apofig.tddtrainer.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:21
 */
public class TrainerTest {

    @Test
    public void shouldFirstTestFromListWhenStart() {
        Trainer trainer = new Trainer();

        String task = trainer.getTask();

        assertEquals("1+1=?", task);
    }

}

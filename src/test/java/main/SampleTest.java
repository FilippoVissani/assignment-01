package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

    private Sample sample;

    @BeforeEach
    public void beforeach(){
        sample = new Sample(4);
    }

    @Test
    public void testGetValue(){
        assertEquals(4, sample.getValue());
    }
}

package atas.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessagesTest {

    @Test
    public void getStudentListedMessageTest_zeroStudents() {
        String expectedMessage = "0 students listed!";
        assertEquals(expectedMessage,
                String.format(Messages.getStudentListedMessage(0), 0));
    }

    @Test
    public void getStudentListedMessageTest_oneStudent() {
        String expectedMessage = "1 student listed!";
        assertEquals(expectedMessage,
                String.format(Messages.getStudentListedMessage(1), 1));
    }

    @Test
    public void getStudentListedMessageTest_multipleStudents() {
        String expectedMessageThreeStudents = "3 students listed!";
        assertEquals(expectedMessageThreeStudents,
                String.format(Messages.getStudentListedMessage(3), 3));

        String expectedMessageThirtyStudents = "30 students listed!";
        assertEquals(expectedMessageThirtyStudents,
                String.format(Messages.getStudentListedMessage(30), 30));

        String expectedMessageThreeHundredStudents = "300 students listed!";
        assertEquals(expectedMessageThreeHundredStudents,
                String.format(Messages.getStudentListedMessage(300), 300));
    }
}

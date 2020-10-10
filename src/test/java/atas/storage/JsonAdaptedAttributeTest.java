package atas.storage;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import atas.commons.exceptions.IllegalValueException;
import atas.model.attendance.Attributes;
import atas.model.attendance.Participation;
import atas.model.attendance.Presence;

public class JsonAdaptedAttributeTest {

    private static final String VALID_INDEX = "1";
    private static final String VALID_PRESENCE = "true";
    private static final String VALID_PARTICIPATION = "true";
    private static final String VALID_NAME = "DEFAULT";

    private static final String INVALID_INDEX = "a";
    private static final String INVALID_PRESENCE = "b";
    private static final String INVALID_PARTICIPATION = "c";
    private static final String INVALID_NAME = "";


    @Test
    public void toModelType_validAttributeDetails_returnsAttribute() throws Exception {
        JsonAdaptedAttributes attr =
            new JsonAdaptedAttributes(VALID_INDEX, VALID_PRESENCE, VALID_PARTICIPATION, VALID_NAME);

        Attributes expectedAttributes =
            new Attributes().togglePresence().toggleParticipation();
        assertEquals(expectedAttributes, attr.toModelType());
    }

    @Test
    public void toModelType_invalidPresence_throwsIllegalValueException() {
        JsonAdaptedAttributes attr =
            new JsonAdaptedAttributes(VALID_INDEX, INVALID_PRESENCE, VALID_PARTICIPATION, VALID_NAME);
        String expectedMessage = Presence.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }

    @Test
    public void toModelType_nullPresence_throwsIllegalValueException() {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, null, VALID_PARTICIPATION, VALID_NAME);
        String expectedMessage = Presence.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }

    @Test
    public void toModelType_invalidParticipation_throwsIllegalValueException() {
        JsonAdaptedAttributes attr =
            new JsonAdaptedAttributes(VALID_INDEX, VALID_PRESENCE, INVALID_PARTICIPATION, VALID_NAME);
        String expectedMessage = Participation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }

    @Test
    public void toModelType_nullParticipation_throwsIllegalValueException() {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, VALID_PRESENCE, null, VALID_NAME);
        String expectedMessage = Participation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }
}

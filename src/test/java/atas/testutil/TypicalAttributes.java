package atas.testutil;

import java.util.List;

import atas.model.session.Attributes;
import atas.model.session.AttributesList;
import atas.model.session.Participation;
import atas.model.session.Presence;
import atas.model.student.Name;

public class TypicalAttributes {
    public static final Presence IS_PRESENT = new Presence(true);
    public static final Presence IS_ABSENT = new Presence(false);
    public static final Participation HAS_PARTICIPATED = new Participation(true);
    public static final Participation HAS_NOT_PARTICIPATED = new Participation(false);
    public static final Name STUDENT_NAME = new Name("DEFAULT");

    public static final Attributes DEFAULT_PARTICIPATION = new Attributes(); // absent and has not participated
    public static final Attributes PRESENT_BUT_HAS_NOT_PARTICIPATED =
        new Attributes(IS_PRESENT, HAS_NOT_PARTICIPATED, STUDENT_NAME);
    public static final Attributes ABSENT_BUT_HAS_PARTICIPATED =
        new Attributes(IS_ABSENT, HAS_PARTICIPATED, STUDENT_NAME);
    public static final Attributes PRESENT_AND_HAS_PARTICIPATED =
        new Attributes(IS_PRESENT, HAS_PARTICIPATED, STUDENT_NAME);

    public static AttributesList getTypicalAttributesList() {
        AttributesList toReturn = new AttributesList();
        toReturn.getAttributesList().addAll(getTypicalAttributes());
        return toReturn;
    }

    public static List<Attributes> getTypicalAttributes() {
        return List.of(DEFAULT_PARTICIPATION,
                PRESENT_BUT_HAS_NOT_PARTICIPATED,
                ABSENT_BUT_HAS_PARTICIPATED,
                PRESENT_AND_HAS_PARTICIPATED);
    }
}

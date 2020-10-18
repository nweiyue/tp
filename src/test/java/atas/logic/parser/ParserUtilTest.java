package atas.logic.parser;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import atas.logic.parser.exceptions.ParseException;
import atas.model.session.IndexRange;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.tag.Tag;
import atas.testutil.Assert;

public class ParserUtilTest {
    private static final String INVALID_MATRICULATION = "651234";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_MATRICULATION = "A1234567E";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_EMAIL = "rachel@u.nus.edu";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    private static final String INVALID_SESSIONNAME = "tut~1";
    private static final String INVALID_SESSIONDATE = "1/1/20";

    private static final String VALID_SESSIONNAME = "lab1";
    private static final String VALID_SESSIONDATE = "1/1/2020";

    private static final String VALID_INDEXRANGE = "1-3";
    private static final String INVALID_INDEXRANGE = "1x";


    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, ParserUtil.MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseMatriculation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMatriculation((String) null));
    }

    @Test
    public void parseMatriculation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMatriculation(INVALID_MATRICULATION));
    }

    @Test
    public void parseMatriculation_validValueWithoutWhitespace_returnsMatriculation() throws Exception {
        Matriculation expectedMatriculation = new Matriculation(VALID_MATRICULATION);
        assertEquals(expectedMatriculation, ParserUtil.parseMatriculation(VALID_MATRICULATION));
    }

    @Test
    public void parseMatriculation_validValueWithWhitespace_returnsTrimmedMatriculation() throws Exception {
        String matriculationWithWhitespace = WHITESPACE + VALID_MATRICULATION + WHITESPACE;
        Matriculation expectedMatriculation = new Matriculation(VALID_MATRICULATION);
        assertEquals(expectedMatriculation, ParserUtil.parseMatriculation(matriculationWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseSessionName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSessionName(null));
    }

    @Test
    public void parseSessionName_invalidName_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSessionName(INVALID_SESSIONNAME));
    }

    @Test
    public void parseSessionName_validSessionNameWithoutWhitespace_returnsSessionName() throws Exception {
        SessionName expectedSessionName = new SessionName(VALID_SESSIONNAME);
        assertEquals(expectedSessionName, ParserUtil.parseSessionName(VALID_SESSIONNAME));
    }

    @Test
    public void parseSessionName_validValueWithWhitespace_returnsTrimmedSessionName() throws Exception {
        String matriculationWithWhitespace = WHITESPACE + VALID_SESSIONNAME + WHITESPACE;
        SessionName expectedSessionName = new SessionName(VALID_SESSIONNAME);
        assertEquals(expectedSessionName, ParserUtil.parseSessionName(matriculationWithWhitespace));
    }

    @Test
    public void parseSessionDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSessionDate(null));
    }

    @Test
    public void parseSessionDate_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSessionDate(INVALID_SESSIONDATE));
    }

    @Test
    public void parseSessionDate_validSessionDateWithoutWhitespace_returnsSessionDate() throws Exception {
        SessionDate expectedSessionDate = new SessionDate(VALID_SESSIONDATE);
        assertEquals(expectedSessionDate, ParserUtil.parseSessionDate(VALID_SESSIONDATE));
    }

    @Test
    public void parseSessionDate_validValueWithWhitespace_returnsTrimmedSessionDate() throws Exception {
        String matriculationWithWhitespace = WHITESPACE + VALID_SESSIONDATE + WHITESPACE;
        SessionDate expectedSessionDate = new SessionDate(VALID_SESSIONDATE);
        assertEquals(expectedSessionDate, ParserUtil.parseSessionDate(matriculationWithWhitespace));
    }

    @Test
    public void parseIndexRange_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIndexRange(null));
    }

    @Test
    public void parseIndexRange_invalidRange_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseIndexRange(INVALID_INDEXRANGE));
    }

    @Test
    public void parseIndexRange_validIndexRangeWithoutWhitespace_returnsIndexRange() throws Exception {
        IndexRange expectedIndexRange = new IndexRange(VALID_INDEXRANGE);
        assertEquals(expectedIndexRange, ParserUtil.parseIndexRange(VALID_INDEXRANGE));
    }

    @Test
    public void parseIndexRange_validValueWithWhitespace_returnsTrimmedIndexRange() throws Exception {
        String matriculationWithWhitespace = WHITESPACE + VALID_INDEXRANGE + WHITESPACE;
        IndexRange expectedIndexRange = new IndexRange(VALID_INDEXRANGE);
        assertEquals(expectedIndexRange, ParserUtil.parseIndexRange(matriculationWithWhitespace));
    }
}

package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static atas.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_MATRICULATION_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static atas.logic.commands.CommandTestUtil.MATRICULATION_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.MATRICULATION_DESC_BOB;
import static atas.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static atas.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static atas.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static atas.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static atas.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static atas.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_MATRICULATION_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static atas.logic.commands.studentlist.AddStudentCommand.MESSAGE_USAGE;
import static atas.logic.parser.CommandParserTestUtil.assertParseFailure;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static atas.testutil.TypicalPersons.AMY;
import static atas.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.AddStudentCommand;
import atas.model.person.Email;
import atas.model.person.Matriculation;
import atas.model.person.Name;
import atas.model.person.Person;
import atas.model.tag.Tag;
import atas.testutil.PersonBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_AMY + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                 + TAG_DESC_FRIEND, new AddStudentCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddStudentCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + MATRICULATION_DESC_AMY + EMAIL_DESC_AMY,
                new AddStudentCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MATRICULATION_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_MATRICULATION_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_MATRICULATION_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Matriculation.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MATRICULATION_DESC_BOB
                        + EMAIL_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}

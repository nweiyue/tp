package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@u.nus.edu").withMatriculation("A2395823W").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@u.nus.edu").withMatriculation("A8473948R").withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withMatriculation("A2345893J")
            .withEmail("heinz@u.nus.edu").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withMatriculation("A2837453G")
            .withEmail("cornelia@u.nus.edu").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withMatriculation("A2384576V")
            .withEmail("werner@u.nus.edu").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withMatriculation("A7984295F")
            .withEmail("lydia@u.nus.edu").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withMatriculation("A4958234S")
            .withEmail("anna@u.nus.edu").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withMatriculation("A5720936H")
            .withEmail("stefan@u.nus.edu").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withMatriculation("A6398734N")
            .withEmail("hans@u.nus.edu").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withMatriculation(VALID_MATRICULATION_AMY).withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withMatriculation(VALID_MATRICULATION_BOB).withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

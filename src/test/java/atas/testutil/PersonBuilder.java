package atas.testutil;

import java.util.HashSet;
import java.util.Set;

import atas.model.person.Email;
import atas.model.person.Matriculation;
import atas.model.person.Name;
import atas.model.person.Person;
import atas.model.tag.Tag;
import atas.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_MATRICULATION = "A3458234L";
    public static final String DEFAULT_EMAIL = "alice@u.nus.edu";

    private Name name;
    private Matriculation matriculation;
    private Email email;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        matriculation = new Matriculation(DEFAULT_MATRICULATION);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        matriculation = personToCopy.getMatriculation();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Matriculation} of the {@code Person} that we are building.
     */
    public PersonBuilder withMatriculation(String matriculation) {
        this.matriculation = new Matriculation(matriculation);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, matriculation, email, tags);
    }

}

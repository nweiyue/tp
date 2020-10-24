package atas.testutil;

import java.util.HashSet;
import java.util.Set;

import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.Student;
import atas.model.tag.Tag;
import atas.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_MATRICULATION = "A3458234L";
    public static final String DEFAULT_EMAIL = "alice@u.nus.edu";

    private Name name;
    private Matriculation matriculation;
    private Email email;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        matriculation = new Matriculation(DEFAULT_MATRICULATION);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
    }

    /**
     * Creates a {@code StudentBuilder} with customized details.
     */
    public StudentBuilder(String matriculation, String email) {
        name = new Name(DEFAULT_NAME);
        this.matriculation = new Matriculation(matriculation);
        this.email = new Email(email);
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        matriculation = studentToCopy.getMatriculation();
        email = studentToCopy.getEmail();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Matriculation} of the {@code Student} that we are building.
     */
    public StudentBuilder withMatriculation(String matriculation) {
        this.matriculation = new Matriculation(matriculation);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Student build() {
        return new Student(name, matriculation, email, tags);
    }

}

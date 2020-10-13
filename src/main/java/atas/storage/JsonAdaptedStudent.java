package atas.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import atas.commons.exceptions.IllegalValueException;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.Student;
import atas.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String matriculation;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String matriculation,
                              @JsonProperty("email") String email,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.matriculation = matriculation;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        matriculation = source.getMatriculation().value;
        email = source.getEmail().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted Student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (matriculation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Matriculation.class.getSimpleName()));
        }
        if (!Matriculation.isValidMatriculation(matriculation)) {
            throw new IllegalValueException(Matriculation.MESSAGE_CONSTRAINTS);
        }
        final Matriculation modelMatriculation = new Matriculation(matriculation);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        return new Student(modelName, modelMatriculation, modelEmail, modelTags);
    }

}

package atas.logic.parser;

import static atas.commons.util.StringUtil.isNonZeroUnsignedInteger;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import atas.commons.core.index.Index;
import atas.logic.parser.exceptions.ParseException;
import atas.model.session.IndexRange;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code range} into an {@code IndexRange} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     */
    public static IndexRange parseIndexRange(String range) {
        requireNonNull(range);
        String trimmedIndexRange = range.trim();
        return new IndexRange(trimmedIndexRange);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Matriculation parseMatriculation(String matriculation) throws ParseException {
        requireNonNull(matriculation);
        String trimmedMatriculation = matriculation.trim();
        if (!Matriculation.isValidMatriculation(trimmedMatriculation)) {
            throw new ParseException(Matriculation.MESSAGE_CONSTRAINTS);
        }
        return new Matriculation(trimmedMatriculation);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String sessionName} into an {@code SessionName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sessionName} is invalid.
     */
    public static SessionName parseSessionName(String sessionName) throws ParseException {
        requireNonNull(sessionName);
        String trimmedSessionName = sessionName.trim();
        if (!SessionName.isValidSessionName(trimmedSessionName)) {
            throw new ParseException(SessionName.MESSAGE_CONSTRAINTS);
        }
        return new SessionName(trimmedSessionName);
    }

    /**
     * Parses a {@code String sessionDate} into an {@code SessionDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sessionDate} is invalid.
     */
    public static SessionDate parseSessionDate(String sessionDate) throws ParseException {
        requireNonNull(sessionDate);
        String trimmedSessionDate = sessionDate.trim();
        if (!SessionDate.isValidSessionDate(trimmedSessionDate)) {
            throw new ParseException(SessionDate.MESSAGE_CONSTRAINTS);
        }
        return new SessionDate(trimmedSessionDate);
    }

}

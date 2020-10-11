package atas.logic;

import static atas.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static atas.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static atas.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.MATRICULATION_DESC_AMY;
import static atas.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static atas.logic.commands.confirmation.ConfirmationCommand.ACCEPT_COMMAND_FULL;
import static atas.logic.parser.CliSyntax.PREFIX_TAG;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalPersons.AMY;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import atas.commons.core.index.Index;
import atas.logic.commands.CommandResult;
import atas.logic.commands.confirmation.ConfirmationCommand;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.commands.studentlist.AddCommand;
import atas.logic.commands.studentlist.DeleteCommand;
import atas.logic.commands.studentlist.EditCommand;
import atas.logic.commands.studentlist.ListCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.ReadOnlyAddressBook;
import atas.model.ReadOnlySessionList;
import atas.model.UserPrefs;
import atas.model.person.Person;
import atas.storage.JsonAddressBookStorage;
import atas.storage.JsonSessionListStorage;
import atas.storage.JsonUserPrefsStorage;
import atas.storage.StorageManager;
import atas.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonSessionListStorage jsonSessionListStorage =
                new JsonSessionListStorage(temporaryFolder.resolve("typicalSessionSessionList.json"));
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(jsonSessionListStorage, addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() throws CommandException, ParseException {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        int outOfBoundIndexOneBased = outOfBoundIndex.getOneBased();

        String deleteCommand = DeleteCommand.COMMAND_WORD + " " + outOfBoundIndexOneBased;
        assertCommandSuccess(deleteCommand, String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_DELETE,
                outOfBoundIndexOneBased), model);
        assertCommandException(ACCEPT_COMMAND_FULL, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);


        String editCommand = EditCommand.COMMAND_WORD + " " + outOfBoundIndexOneBased + " "
                + PREFIX_TAG + "newTag";
        assertCommandSuccess(editCommand, String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_EDIT,
                outOfBoundIndexOneBased), model);
        assertCommandException(ACCEPT_COMMAND_FULL, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonSessionListStorage jsonSessionListStorage =
                new JsonSessionListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionSessionList.json"));
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(jsonSessionListStorage, addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + MATRICULATION_DESC_AMY + EMAIL_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void getters() {
        JsonSessionListStorage jsonSessionListStorage =
                new JsonSessionListStorage(temporaryFolder.resolve("typicalSessionSessionList.json"));
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(jsonSessionListStorage, addressBookStorage, userPrefsStorage);
        LogicManager logicManager = new LogicManager(model, storage);

        logic.enableCurrentSession();
        logic.disableCurrentSession();

        assertEquals(logicManager.getAddressBook(), logic.getAddressBook());
        assertEquals(logicManager.getFilteredPersonList(), logic.getFilteredPersonList());
        assertEquals(logicManager.getFilteredSessionList(), logic.getFilteredSessionList());
        assertEquals(logicManager.getAddressBookFilePath(), logic.getAddressBookFilePath());
        assertEquals(logicManager.getGuiSettings(), logic.getGuiSettings());
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonSessionListIoExceptionThrowingStub extends JsonSessionListStorage {
        private JsonSessionListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSessionList(ReadOnlySessionList sessionList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}

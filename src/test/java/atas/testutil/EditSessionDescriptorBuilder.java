package atas.testutil;

import atas.logic.commands.sessionlist.EditSessionCommand;
import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;

/**
 * A utility class to help with building EditSessionDescriptor objects.
 */
public class EditSessionDescriptorBuilder {

    private EditSessionCommand.EditSessionDescriptor descriptor;

    public EditSessionDescriptorBuilder() {
        descriptor = new EditSessionCommand.EditSessionDescriptor();
    }

    public EditSessionDescriptorBuilder(EditSessionCommand.EditSessionDescriptor descriptor) {
        this.descriptor = new EditSessionCommand.EditSessionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSessionDescriptor} with fields containing {@code session}'s details
     */
    public EditSessionDescriptorBuilder(Session session) {
        descriptor = new EditSessionCommand.EditSessionDescriptor();
        descriptor.setSessionName(session.getSessionName());
        descriptor.setSessionDate(session.getSessionDate());
    }

    /**
     * Sets the {@code sessionName} of the {@code EditSessionDescriptor} that we are building.
     */
    public EditSessionDescriptorBuilder withSessionName(String sessionName) {
        descriptor.setSessionName(new SessionName(sessionName));
        return this;
    }

    /**
     * Sets the {@code sessionDate} of the {@code EditSessionDescriptor} that we are building.
     */
    public EditSessionDescriptorBuilder withSessionDate(String sessionDate) {
        descriptor.setSessionDate(new SessionDate(sessionDate));
        return this;
    }

    public EditSessionCommand.EditSessionDescriptor build() {
        return descriptor;
    }

}

package gemini;

import gemini.model.AbstractScienceObserver;

public class ScienceObserver extends AbstractScienceObserver {
    public ScienceObserver(
            String firstName,
            String lastName,
            String address,
            String email,
            int id,
            String department
    ) {
        super(firstName, lastName, address, email, id, department);
    }

    public ScienceObserver() {
        super();
    }
}

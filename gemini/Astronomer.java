package gemini;

import gemini.model.AbstractAstronomer;

public class Astronomer extends AbstractAstronomer {
    public Astronomer(
            String firstName,
            String lastName,
            String address,
            String email,
            int id,
            String institution
    ) {
        super(firstName, lastName, address, email, id, institution);
    }

    public Astronomer() {
        super();
    }

}

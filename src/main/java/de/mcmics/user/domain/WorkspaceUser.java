package de.mcmics.user.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class WorkspaceUser extends User {

    @Override
    public String toString() {
        return "User: " + getPK();
    }
}

package de.mcmics.common.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
public class CurrentMaxId implements Serializable {

    private static final long serialVersionUID = -7595829207517201151L;

    private long high;

    @Id
    private PK pk;

    @Version
    private int version;
}

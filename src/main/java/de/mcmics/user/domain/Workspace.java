package de.mcmics.user.domain;

import de.mcmics.common.ToStringBuilder;
import de.mcmics.common.domain.PK;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"tenantId", "id"}))
public class Workspace implements Serializable {

    private static final long serialVersionUID = -3483434924934247304L;

    @EmbeddedId
    private PK pk;
    @Id
    private long id;
    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false),
        @JoinColumn(name = "tenantId", referencedColumnName = "tenantId", insertable = false,
            updatable = false)})
    private WorkspaceUser user;

    private long userId;

    public Workspace(long id) {
        this.id = id;
    }

    public Workspace() {
        this(1L);
    }

    public PK getPK() {
        return pk;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("description", description)
            .toString();
    }
}

package de.mcmics.user.domain;

import de.mcmics.common.ToStringBuilder;
import de.mcmics.common.domain.DefaultMember;
import de.mcmics.common.domain.PK;
import de.mcmics.lib.db.common.domain.Member;
import de.mcmics.lib.db.common.domain.NamedMember;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorOptions(force = true)
@Table(name = "Users")
public abstract class User extends DefaultMember implements NamedMember {

    private static final long serialVersionUID = 8810039165419267472L;

    @Column(length = 200)
    private String name;

    @EmbeddedId
    private PK pk;

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<? extends Member> getTenantType() {
        return User.class;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("name", name)
            .toString();
    }
}

package de.mcmics.common.domain;

import de.mcmics.lib.db.common.domain.Member;

import java.io.Serializable;

public abstract class DefaultMember implements Member {

    private static final long serialVersionUID = -1474138382265764710L;

    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other instanceof DefaultMember) {
            final DefaultMember otherDefaultMember = (DefaultMember) other;
            if(getTenantType().equals(otherDefaultMember.getTenantType())) {
                if (getPK() == null) {
                    return otherDefaultMember.getPK() == null;
                }
                return getPK().equals(otherDefaultMember.getPK());
            }
        }
        return false;
    }

    @Override
    public final long getId() {
        return getPK().getId();
    }

    public PK getPK() {
        return null;
    }

    @Override
    public final long getTenantId() {
        return getPK().getTenantId();
    }

    @Override
    public Serializable getSerializablePK() {
        return getPK();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPK() == null) ? 0 : getPK().hashCode());
        return result;
    }

    @Override
    public abstract String toString();
}

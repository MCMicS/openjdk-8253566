package de.mcmics.lib.db.common.domain;

import de.mcmics.common.lang.TenantAndIdAware;

import java.io.Serializable;

public interface TenantMember extends Serializable, TenantAndIdAware {
    @Override
    boolean equals(Object pObj);

    @Override
    long getId();

    Class<? extends TenantMember> getInternalType();

    @Override
    long getTenantId();

    Serializable getSerializablePK();

    @Override
    int hashCode();
}

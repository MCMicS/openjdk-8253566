package de.mcmics.lib.db.common.domain;

import de.mcmics.common.lang.TenantAndIdAware;

import java.io.Serializable;

public interface Member extends Serializable, TenantAndIdAware {

    @Override
    boolean equals(Object pObj);

    long getId();

    Class<? extends Member> getTenantType();

    long getTenantId();

    Serializable getSerializablePK();

    @Override
    int hashCode();
}

package de.mcmics.lib.db.common.domain;

import de.mcmics.common.lang.Named;

public interface NamedMember extends Member, Named, TenantMember {

    default Class<? extends TenantMember> getInternalType() {
        return TenantMember.class;
    }
}

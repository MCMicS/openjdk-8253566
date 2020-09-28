package de.mcmics.common.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Comparator;

@Data
@Embeddable
public class PK implements Serializable, Comparable<PK> {

    private static final long serialVersionUID = -1971572009347219384L;

    private long id;

    private long tenantId;

    @Override
    public int compareTo(PK other) {
        if (other == null) {
            return -1;
        }
        return Comparator.comparingLong(PK::getTenantId).thenComparingLong(PK::getId).compare(this, other);
    }

    @Override
    public String toString() {
        return "PK: " + getId();
    }
}

package de.mcmics.lib.db.common.domain;

import lombok.Getter;

import java.sql.Blob;

@Getter
public class BlobContentSetter {

    private final Blob sqlBlob;

    private final long length;

    public BlobContentSetter(Blob sqlBlob, long length) {
        this.sqlBlob = sqlBlob;
        this.length = length;
    }

}

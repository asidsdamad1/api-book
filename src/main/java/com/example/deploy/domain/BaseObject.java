package com.example.deploy.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class BaseObject {

    @Id
    @Type(
            type = "uuid-char"
    )
    @Column(
            name = "id",
            unique = true,
            nullable = false
    )
    private UUID id;
    @Column(
            name = "uuid_key",
            nullable = true
    )
    @Type(
            type = "uuid-char"
    )
    private UUID uuidKey;
    @Column(
            name = "voided",
            nullable = true
    )
    private Boolean voided;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getVoided() {
        return this.voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public UUID getUuidKey() {
        return this.uuidKey;
    }

    public void setUuidKey(UUID uuidKey) {
        this.uuidKey = uuidKey;
    }

    public BaseObject() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }

        this.uuidKey = UUID.randomUUID();
    }

    public BaseObject(BaseObject object) {

        if (object != null) {
            this.uuidKey = UUID.randomUUID();
            this.id = object.getId();
        }

    }
}
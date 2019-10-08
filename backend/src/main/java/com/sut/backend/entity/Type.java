package com.sut.backend.entity;
import javax.persistence.*;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;


@Entity
@Setter @Getter
@ToString

@EqualsAndHashCode
@Table (name="Types")

public class Type {

    @Id // Annotations @Id บอกว่าเป็น Primary key
    @SequenceGenerator(name="types_seq",sequenceName="types_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="types_seq")
    @Column(name="typesID")

    private @NonNull Long typesID;
    private @NonNull String types;

    public Long getTypesID() {
        return typesID;
    }

    public void setTypesID(Long typesID) {
        this.typesID = typesID;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
    
}
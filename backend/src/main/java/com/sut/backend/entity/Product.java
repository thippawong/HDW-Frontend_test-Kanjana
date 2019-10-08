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
@Table (name="Products")

public class Product {

    @Id // Annotations @Id บอกว่าเป็น Primary key
    @SequenceGenerator(name="products_seq",sequenceName="products_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="products_seq")
    @Column(name="productsID")

    private @NonNull Long productsID;
    private @NonNull String name;
    private @NonNull String code;
    private @NonNull String image;
    private @NonNull String detail;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    private Type type;

    public Long getProductsID() {
        return productsID;
    }

    public void setProductsID(Long productsID) {
        this.productsID = productsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
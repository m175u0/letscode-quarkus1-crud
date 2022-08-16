package org.exemplo.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String name;
    @Column(nullable = false)
    @NotNull
    @Min(18)
    private int age;
    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp = "^[\\p{Upper}]{2}[\\d]{9}$")
    private String vat;
    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp = "^\\S+@\\S+$")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    public Client() {}

    public Client(long id, String name, int age, String vat, String email, Category category) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.vat = vat;
        this.email = email;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

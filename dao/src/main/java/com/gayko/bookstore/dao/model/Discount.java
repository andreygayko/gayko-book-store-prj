package com.gayko.bookstore.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_discount")
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "interest")
    private BigDecimal interestRate;

    @Column(name = "final_date")
    private LocalDateTime finalDate;

    @ManyToMany
    @JoinTable(
            name = "t_item_discount",
            joinColumns = @JoinColumn(name = "f_discount_id", foreignKey=@ForeignKey(name="foreign_key_discount_id")),
            inverseJoinColumns = @JoinColumn(name = "f_item_id", foreignKey=@ForeignKey(name="foreign_key_item_id"))
    )
    private List<Item> items = new ArrayList<>();

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}

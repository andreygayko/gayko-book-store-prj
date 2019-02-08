package com.gayko.bookstore.model.impl;


import java.util.List;
import java.util.Objects;

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String surname;

    private String password;

    private RoleDTO role;

    private List<OrderDTO> orders;

    private DiscountDTO discount;

    public UserDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public List<OrderDTO> getOrders() { return orders; }

    public void setOrders(List<OrderDTO> orders) { this.orders = orders; }

    public DiscountDTO getDiscount() { return discount; }

    public void setDiscount(DiscountDTO discount) { this.discount = discount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                //Objects.equals(name, userDTO.name) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(surname, userDTO.surname) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(role, userDTO.role) &&
                Objects.equals(orders, userDTO.orders) &&
                Objects.equals(discount, userDTO.discount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, surname, password, role, orders, discount);
    }
}

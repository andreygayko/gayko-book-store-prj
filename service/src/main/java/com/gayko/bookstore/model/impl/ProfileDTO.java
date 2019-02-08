package com.gayko.bookstore.model.impl;

import java.util.Objects;

public class ProfileDTO {

    private Long id;

    private String address;

    private String phone;

    private UserDTO userDTO;

    public ProfileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDTO that = (ProfileDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(userDTO, that.userDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, address, phone, userDTO);
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", userDTO=" + userDTO +
                '}';
    }
}

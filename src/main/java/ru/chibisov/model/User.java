package ru.chibisov.model;

/**
 * Представление сущности пользователя в системе
 */
public class User implements Identifiable<Long> {

    private static final long serialVersionUID = 4546946133334325941L;

    /**
     * Уникальный идентификатор пользователя в системе
     */
    private Long id;

    /**
     * Роль пользователя системы
     */
    private Role role;

    /**
     * Логин пользователя
     */
    private String name;

    /**
     * Хеш пароля
     */
    private String password;

    /**
     * Контактный телефон
     */
    private String phone;

    /**
     * Адрес эл. почты
     */
    private String email;

    public User() {
        this.role = Role.CUSTOMER;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

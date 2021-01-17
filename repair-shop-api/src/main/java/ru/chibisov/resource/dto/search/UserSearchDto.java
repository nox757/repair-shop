package ru.chibisov.resource.dto.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель для поиска пользователей")
public class UserSearchDto {

    @ApiModelProperty(value = "Имф пользователя", example = "Иванов Иван")
    private String name;

    @ApiModelProperty(value = "Адрес электронной почты", example = "test@test.ru")
    private String email;

    @ApiModelProperty(value = "Телефон пользователя", example = "79778899743")
    private String phone;

    public String getName() {
        return name;
    }

    public UserSearchDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSearchDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserSearchDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "UserSearchDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

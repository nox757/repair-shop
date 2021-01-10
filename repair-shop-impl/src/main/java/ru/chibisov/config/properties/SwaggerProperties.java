package ru.chibisov.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /**
     * Название проекта
     */
    private String title;

    /**
     * Описание
     */
    private String description;

    /**
     * Контактное лицо
     */
    private Contact contract = new Contact();

    public SwaggerProperties() {
    }

    public SwaggerProperties(String title, String description, Contact contract) {
        this.title = title;
        this.description = description;
        this.contract = contract;
    }

    public String getTitle() {
        return title;
    }

    public SwaggerProperties setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SwaggerProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    public Contact getContract() {
        return contract;
    }

    public SwaggerProperties setContract(Contact contract) {
        this.contract = contract;
        return this;
    }

    public static class Contact {

        private String name;

        private String url;

        private String mail;

        public Contact() {
        }

        public Contact(String name, String url, String mail) {
            this.name = name;
            this.url = url;
            this.mail = mail;
        }

        public String getName() {
            return name;
        }

        public Contact setName(String name) {
            this.name = name;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Contact setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getMail() {
            return mail;
        }

        public Contact setMail(String mail) {
            this.mail = mail;
            return this;
        }
    }
}


package ru.chibisov.dao;

import org.springframework.context.annotation.Bean;
import ru.chibisov.dao.impl.MaterialDaoImpl;
import ru.chibisov.dao.impl.RequestDaoImpl;
import ru.chibisov.dao.impl.RequestMaterialDaoImpl;
import ru.chibisov.dao.impl.SupplierDaoImpl;
import ru.chibisov.dao.impl.UserDaoImpl;

public class RepositoryConfig {

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public RequestDao requestDao() {
        return new RequestDaoImpl();
    }

    @Bean
    public RequestMaterialDao requestMaterialDao() {
        return new RequestMaterialDaoImpl();
    }

    @Bean
    public MaterialDao materialDao() {
        return new MaterialDaoImpl();
    }

    @Bean
    public SupplierDao supplierDao() {
        return new SupplierDaoImpl();
    }

}

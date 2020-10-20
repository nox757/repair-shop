package ru.chibisov.service;

import org.springframework.context.annotation.Bean;
import ru.chibisov.dao.RequestDao;
import ru.chibisov.dao.RequestMaterialDao;
import ru.chibisov.dao.UserDao;
import ru.chibisov.service.impl.MaterialServiceImpl;
import ru.chibisov.service.impl.RequestServiceImpl;
import ru.chibisov.service.impl.SupplierServiceImpl;
import ru.chibisov.service.impl.UserServiceImpl;

public class ServiceConfig {

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserServiceImpl(userDao);
    }

    @Bean
    public RequestService requestService(RequestDao requestDao, RequestMaterialDao requestMaterialDao) {
        return new RequestServiceImpl(requestDao, requestMaterialDao);
    }

    @Bean
    public MaterialService materialService() {
        return new MaterialServiceImpl();
    }

    @Bean
    public SupplierService supplierService() {
        return new SupplierServiceImpl();
    }

}

package ru.chibisov.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.chibisov.model.Material;
import ru.chibisov.model.Request;
import ru.chibisov.model.RequestMaterial;
import ru.chibisov.model.Role;
import ru.chibisov.model.Supplier;
import ru.chibisov.model.User;
import ru.chibisov.service.MaterialService;
import ru.chibisov.service.RequestService;
import ru.chibisov.service.SupplierService;
import ru.chibisov.service.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainService {

    public static void main(String[] args) {

        ApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);

//        UserService userService = (UserService) context.getBean("userServiceImpl");
//        RequestService requestService = (RequestService) context.getBean("requestServiceImpl");
//        MaterialService materialService = (MaterialService) context.getBean("materialServiceImpl");
//        SupplierService supplierService = (SupplierService) context.getBean("supplierServiceImpl");
//
//        User customer = new User();
//        customer.setId(0L);
//        customer.setName("Иванов 1");
//        customer.setPhone("70778800234");
//
//        User repair = new User();
//        repair.setRole(Role.REPAIRER);
//        repair.setId(2L);
//        repair.setName("Иванов 2");
//        repair.setPhone("70778800235");
//
//        userService.addUser(customer);
//        userService.addUser(repair);
//
//        Material material = new Material();
//        material.setId(0L);
//        material.setCodeName("AAA/BBB/01");
//        material.setName("Кожа");
//        material.setPrice(BigDecimal.valueOf(1.5));
//        material.setRemains(BigDecimal.valueOf(10.0));
//
//        Material material2 = new Material();
//        material2.setId(1L);
//        material2.setCodeName("AAA/BBB/02");
//        material2.setName("Веревка");
//        material2.setPrice(BigDecimal.valueOf(5.0));
//        material2.setRemains(BigDecimal.valueOf(10.0));
//
//        materialService.addMaterial(material);
//        materialService.addMaterial(material2);
//
//        Supplier supplier = new Supplier();
//        supplier.setId(0L);
//        supplier.setNameAgent("Петров 1");
//        supplier.setOrgName("Рога и копыта");
//        supplier.setPhoneAgent("70891122304");
//        supplier.setMaterials(new HashSet<>(Arrays.asList(material, material2)));
//        supplierService.addSupplier(supplier);
//
//        Request request = new Request();
//        request.setId(0L);
//        request.setCustomer(userService.getUserById(0L));
//        request.setRepairer(userService.getUserById(2L));
//        request.setAmount(BigDecimal.valueOf(10.5));
//
//        RequestMaterial requestMaterial = new RequestMaterial();
//        requestMaterial.setMaterial(materialService.getMaterialById(0L));
//        requestMaterial.setQuantity(1.0);
//        RequestMaterial requestMaterial2 = new RequestMaterial();
//        requestMaterial2.setMaterial(materialService.getMaterialById(1L));
//        requestMaterial2.setQuantity(0.5);
//        Set<RequestMaterial> materialsRequests = new HashSet<>();
//        materialsRequests.add(requestMaterial);
//        materialsRequests.add(requestMaterial2);
//
//        request.setMaterials(materialsRequests);
//        requestService.addRequest(request);
//
//        System.out.println(requestService.getRequestById(0L).toString());
//        System.out.println(supplierService.getSupplierById(0L));

    }

}

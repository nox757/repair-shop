package ru.chibisov.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.chibisov.model.*;
import ru.chibisov.service.MaterialService;
import ru.chibisov.service.RequestService;
import ru.chibisov.service.SupplierService;
import ru.chibisov.service.UserService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MainService {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService userService = (UserService) context.getBean("userService");
        RequestService requestService = (RequestService) context.getBean("requestService");
        MaterialService materialService = (MaterialService) context.getBean("materialService");
        SupplierService supplierService = (SupplierService) context.getBean("supplierService");

        User customer = new User();
        customer.setId(0L);
        customer.setName("Иванов 1");
        customer.setPhone("70778800234");

        User repair = new User();
        repair.setRole(Role.REPAIRER);
        repair.setId(2L);
        repair.setName("Иванов 2");
        repair.setPhone("70778800235");

        userService.addUser(customer);
        userService.addUser(repair);

        Supplier supplier = new Supplier();
        supplier.setId(0L);
        supplier.setNameAgent("Петров 1");
        supplier.setOrgName("Рога и копыта");
        supplier.setPhoneAgent("70891122304");
        supplierService.addSupplier(supplier);

        Material material = new Material();
        material.setId(0L);
        material.setSupplier(supplierService.getSupplierById(0L));
        material.setSupplier(supplierService.getSupplierById(0L));
        material.setCodeName("AAA/BBB/01");
        material.setPrice(BigDecimal.valueOf(1.5));
        material.setRemains(BigDecimal.valueOf(10.0));

        Material material2 = new Material();
        material2.setId(1L);
        material2.setSupplier(supplierService.getSupplierById(0L));
        material2.setSupplier(supplierService.getSupplierById(0L));
        material2.setCodeName("AAA/BBB/02");
        material2.setPrice(BigDecimal.valueOf(5.0));
        material2.setRemains(BigDecimal.valueOf(10.0));

        materialService.addMaterial(material);
        materialService.addMaterial(material2);

        Request request = new Request();
        request.setId(0L);
        request.setCustomer(userService.getUserById(0L));
        request.setRepairer(userService.getUserById(2L));
        request.setAmount(BigDecimal.valueOf(10.5));

        Map<Material, Double> materialsRequest = new HashMap<>();
        materialsRequest.put(materialService.getMaterialById(0L), 0.5);
        materialsRequest.put(materialService.getMaterialById(1L), 1.0);
        request.setMaterials(materialsRequest);

        requestService.addRequest(request);

        System.out.println(requestService.getRequestById(0L).toString());

    }

}

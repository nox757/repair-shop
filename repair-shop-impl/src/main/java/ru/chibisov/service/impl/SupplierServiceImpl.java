package ru.chibisov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.resource.dto.SupplierDto;
import ru.chibisov.controller.dto.mapper.SupplierMapper;
import ru.chibisov.resource.dto.search.SupplierSearchDto;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.Supplier;
import ru.chibisov.repository.MaterialRepository;
import ru.chibisov.repository.SupplierRepository;
import ru.chibisov.service.SupplierService;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private static final Logger log = LoggerFactory.getLogger(SupplierServiceImpl.class.getName());

    private SupplierRepository supplierRepository;
    private SupplierMapper mapper;

    private MaterialRepository materialRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper mapper,
                               MaterialRepository materialRepository) {
        log.info("saveService");
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.materialRepository = materialRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.getOne(id);
        if (supplier.getId() == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        supplier.setMaterials(new HashSet<>(materialRepository.findAllBySupplierId(id)));
        return mapper.map(supplier);
    }

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapper.map(supplierDto);
        return mapper.map(supplierRepository.save(supplier));
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = mapper.map(supplierDto);
        return mapper.map(supplierRepository.save(supplier));
    }

    @Override
    public void removeSupplierById(Long id) {
        try {
            supplierRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.toString());
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>(supplierRepository.findAll());
        return mapper.map(suppliers);
    }

    @Override
    public Page<SupplierDto> getSuppliers(SupplierSearchDto supplierSearchDto, Pageable pageable) {
        return supplierRepository.findAll(getSpecification(supplierSearchDto), pageable).map(mapper::map);
    }

    private Specification<Supplier> getSpecification(SupplierSearchDto supplierSearchDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (supplierSearchDto.getNameAgent() != null) {
                predicates.add(builder.like(
                        builder.lower(root.get("nameAgent")),
                        String.format("%%%s%%", supplierSearchDto.getNameAgent().toLowerCase())
                ));
            }

            if (supplierSearchDto.getOrgName() != null) {
                predicates.add(builder.like(
                        builder.lower(root.get("orgName")),
                        String.format("%%%s%%", supplierSearchDto.getOrgName().toLowerCase())
                ));
            }

            if (supplierSearchDto.getPhoneAgent() != null) {
                predicates.add(root.get("phoneAgent").in(supplierSearchDto.getPhoneAgent()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}

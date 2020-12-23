package ru.chibisov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.chibisov.model.Supplier;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Поставщик {@link Supplier}
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

}

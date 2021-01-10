package ru.chibisov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.chibisov.model.User;

import java.util.List;

/**
 * Интерфейс управления персистетным состоянием объектов с типом Пользователь {@link User}
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * Возвращает коллекцию всех клиентов
     */
    @Query("SELECT u FROM User u WHERE u.role = 'CUSTOMER'")
    List<User> AllCustomer();

    /**
     * Возвращает коллецию всех мастеров
     */
    @Query("SELECT u FROM User u WHERE u.role = 'REPAIRER'")
    List<User> getAllRepairer();

}

package ru.chibisov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chibisov.controller.dto.UserDto;
import ru.chibisov.controller.dto.mapper.UserMapper;
import ru.chibisov.controller.dto.search.UserSearchDto;
import ru.chibisov.exception.ObjectNotFoundException;
import ru.chibisov.model.User;
import ru.chibisov.repository.UserRepository;
import ru.chibisov.service.UserService;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private UserRepository userRepository;
    private UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        log.info("saveService");
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.getOne(id);
        if (user == null) {
            throw new ObjectNotFoundException(String.valueOf(id));
        }
        return mapper.map(user);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = mapper.map(userDto);
        return mapper.map(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = mapper.map(userDto);
        return mapper.map(userRepository.save(user));
    }

    @Override
    public void removeUserById(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.debug(e.toString());
            throw new ObjectNotFoundException(String.valueOf(id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        return mapper.map(users);
    }


    @Override
    public Page<UserDto> getUsers(UserSearchDto userSearchDto, Pageable pageable) {
        return userRepository.findAll(getSpecification(userSearchDto), pageable).map(mapper::map);
    }

    private Specification<User> getSpecification(UserSearchDto userSearchDto) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userSearchDto.getName() != null) {
                predicates.add(builder.lower(root.get("name")).in(userSearchDto.getName().toLowerCase()));
            }

            if (userSearchDto.getEmail() != null) {
                predicates.add(root.get("email").in(userSearchDto.getEmail()));
            }

            if (userSearchDto.getPhone() != null) {
                predicates.add(root.get("phone").in(userSearchDto.getPhone()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}

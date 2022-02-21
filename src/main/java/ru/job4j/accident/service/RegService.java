package ru.job4j.accident.service;

import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.User;

public interface RegService {

    Authority findByAuthority(String authority);

    User save(User user);

    boolean existsByUsername(String username);
}

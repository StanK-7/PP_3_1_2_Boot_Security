package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void initDB() {
        Role roleAdmin = new Role(1L, "ROLE_ADMIN");
        Role roleUser = new Role(2L, "ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        adminSet.add(roleUser);
        userSet.add(roleUser);

        User admin = new User("admin", "admin",
                "Stepan", "Razin", 35,
                "st.razin@mail.ru", adminSet);
        admin.setId(1L);

        User user = new User("user", "user",
                "Emelyan", "Pugachev", 33,
                "em.pugachev@mail.ru", userSet);

        user.setId(2L);

//        roleAdmin.setUsers(new HashSet<>(Collections.singleton(admin)));
//        roleUser.setUsers(new HashSet<>(List.of(admin, user)));

        userService.saveUser(admin);
        userService.saveUser(user);

    }


}

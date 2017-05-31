package com.intexsoft.tests;

import com.intexsoft.model.User;
import com.intexsoft.services.impl.UserServiceImpl;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by artsem_dudar on 5/30/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {

    UserServiceImpl service = new UserServiceImpl();
    User user;

    @Before
    public final void beforeTest() {
        user = new User(4,
                "User4",
                "Address4",
                "user4@abc.com");
    }

    @Test
    public final void t1_findByIdUserTest() {
        service.saveUser(user);
        assertEquals(user, service.findById(user.id));
    }

    @Test
    public final void t2_findByNameUserTest() {
        service.saveUser(user);
        assertEquals(user, service.findByName(user.userName));
    }

    @Test
    public final void t3_isUserExistTest() {
        service.saveUser(user);
        assertTrue(service.isUserExist(user));
    }

    @Test
    public final void t4_saveUserTest() {
        User newUser = new User(5,
                "User5",
                "Address5",
                "user5@abc.com");
        service.saveUser(newUser);
        assertTrue(service.isUserExist(newUser));
    }



    @Test
    public final void t5_findAllUsersTest() {
        List<User> users = service.findAllUsers();
        assertNotNull(users.size());
    }


    @Test
    public final void t6_deleteUserByIdTest() {
        service.saveUser(user);
        service.deleteUserById(user.id);
        assertFalse(service.isUserExist(user));
    }

    @Test
    public final void t7_updateUserTest() {
        service.saveUser(user);
        user.userName= "update_username";
        service.updateUser(user);
        assertEquals(user, service.findById(user.id));
    }

    @Test
    public final void t8_deleteAllUsersTest() {
        service.saveUser(user);
        service.deleteAllUsers();
        List<User> users = service.findAllUsers();
        assertEquals(users.size(), 0);
    }

    @Test
    public final void t9_findUserByIdNullTest() {
        assertNull(service.findById(-1));
    }

    @After
    public final void after() {
        service.deleteUserById(user.id);
    }
}

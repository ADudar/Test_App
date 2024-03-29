package com.intexsoft.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intexsoft.model.User;
import com.intexsoft.services.UserService;

/**
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private static final AtomicLong counter = new AtomicLong();
    private static List<User> users;

    static {
        users = populateUsers();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findById(long id) {
        for (User user : users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.userName.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        user.id = counter.incrementAndGet();
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserById(long id) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.id == id) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(User user) {
        return findByName(user.userName) != null;
    }

    public void deleteAllUsers() {
        users.clear();
    }

    private static List<User> populateUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User(counter.incrementAndGet(), "User1", "Address1", "user1@abc.com"));
        users.add(new User(counter.incrementAndGet(), "User2", "Address2", "user2@abc.com"));
        users.add(new User(counter.incrementAndGet(), "User3", "Address3", "user3@abc.com"));
        return users;
    }
}

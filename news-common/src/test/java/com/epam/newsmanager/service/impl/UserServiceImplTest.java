package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.impl.UserDaoImpl;
import com.epam.newsmanager.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class with Mockito Framework for <cod>UserServiceImpl</cod> class.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDaoImpl userDao;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertUser() throws Exception {
        User user = new User();
        user.setUserId(1L);
        when(userDao.insert(user)).thenReturn(1L);
        userService.insertUser(user);
        Assert.assertEquals(1, user.getUserId());
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setUserId(1L);
        when(userService.getUserById(1L)).thenReturn(user);

        userService.insertUser(user);
        userService.deleteUser(1L);
        verify(userDao, times(1)).insert(any(User.class));
        verify(userDao, times(1)).delete(any(Long.class));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setUserId(1L);
        when(userService.getUserById(1L)).thenReturn(user);
        User newUser = userService.getUserById(1L);
        Assert.assertEquals(user, newUser);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Set<User> users = new HashSet<User>();
        users.add(new User());
        when(userDao.getAll()).thenReturn(users);
        Set<User> newUsers = userDao.getAll();
        Assert.assertEquals(users, newUsers);
    }
}
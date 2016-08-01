package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.impl.RoleDaoImpl;
import com.epam.newsmanager.entity.Role;
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
import static org.mockito.Mockito.*;

/**
 * Test class with Mockito Framework for <cod>RoleServiceImpl<cod/> class.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDaoImpl roleDao;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertRole() throws Exception {
        Role role = new Role();
        role.setUserId(1L);
        when(roleDao.insert(role)).thenReturn(1L);
        roleService.insertRole(role);
        Assert.assertEquals(1L, role.getUserId());
    }

    @Test
    public void testDeleteRole() throws Exception {
        Role role = new Role();
        role.setUserId(1L);
        when(roleDao.getByUserId(1L)).thenReturn(role);

        roleService.insertRole(role);
        roleService.deleteRole(1L);
        verify(roleDao, times(1)).insert(any(Role.class));
        verify(roleDao, times(1)).delete(any(Long.class));
    }

    @Test
    public void testGetAllRoles() throws Exception {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());
        when(roleDao.getAll()).thenReturn(roles);
        Set<Role> newRoles = roleDao.getAll();
        Assert.assertEquals(roles, newRoles);
    }

    @Test
    public void testGetRoleById() throws Exception {
        Role role = new Role();
        role.setUserId(1L);
        when(roleDao.getByUserId(1L)).thenReturn(role);
        Role newRole = roleDao.getByUserId(1L);
        Assert.assertEquals(role, newRole);
    }
}
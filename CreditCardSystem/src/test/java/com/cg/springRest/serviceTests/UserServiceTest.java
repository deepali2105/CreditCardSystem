package com.cg.springRest.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.springRest.model.User;
import com.cg.springRest.repository.UserRepository;
import com.cg.springRest.service.UserService;
import com.cg.springRest.service.UserServiceImplementation;



@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserServiceImplementation service;
    
    @MockBean
    UserRepository repository;
    
    UserService listMock= mock(UserService.class,"myMock");
    
    @Test
    public void addAdminTest() {
        User user= new User(1, "akale", "anushka", 1);
        when(repository.save(user)).thenReturn(user);
        assertEquals(user,service.addAdmin(user));
    }
    
   
    @Test
    public void getAllAdminTest() {
        when(repository.findAll()).thenReturn( Stream.of(new User(1, "akale", "anushka", 1)).collect(Collectors.toList()));
        assertEquals(1, service.getAllUsers().size());
    }

    @Test
    public void deleteAdminTest() {
        User user= new User(1, "akale", "anushka", 1);
        service.deleteAdmin(user.getUserId());
        verify(repository, times(1)).deleteById(user.getUserId());
    }
    
  
    @Test
    public void getAdminById() {
        int userId=2;
        List<User> listUser = Stream.of(
                new User(1, "akale", "anushka", 1), 
                new User(2, "drambhad", "deepali", 1))
                .collect(Collectors.toList());
        repository.saveAll(listUser);
        when(repository.findById(userId)).thenReturn(Optional.of(listUser.get(1)));
        assertEquals(listUser.get(1), service.getAdmin(userId));
    }
}
package com.rollingstone.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rollingstone.dao.jpa.EcommUserRepository;
import com.rollingstone.domain.User;

/*
 * Service class to do CRUD for User and Address through JPS Repository
 */
@Service
public class EcommUserService {

    private static final Logger log = LoggerFactory.getLogger(EcommUserService.class);

    @Autowired
    private EcommUserRepository userRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public EcommUserService() {
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    public void updateUser(User user) {
    	userRepository.save(user);
    }

    public void deleteUser(Long id) {
    	userRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<User> getAllUsersByPage(Integer page, Integer size) {
        Page pageOfUsers = userRepository.findAll(new PageRequest(page, size));
        
    	log.info("In Real Service Kaku Parameters" + size + page);

    	log.info("In Real Service Kaku " + pageOfUsers.getSize());

        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfUsers;
    }
    
    public List<User> getAllUsers() {
        Iterable<User> pageOfUsers = userRepository.findAll();
        
        List<User> users = new ArrayList<User>();
        
        for (User u : pageOfUsers){
        	users.add(u);
        }
    	log.info("In Real Service Kaku getAllUsers users size :"+users.size());

    	
        return users;
    }
}

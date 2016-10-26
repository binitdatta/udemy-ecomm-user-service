package com.rollingstone.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.User;



public interface EcommUserRepository extends PagingAndSortingRepository<User, Long> {
    User findUserByRating(int rating);
    Page findAll(Pageable pageable);
}

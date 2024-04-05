package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserEntity;
import com.inventory.inventoryservice.security.model.UserSearchDto;
import com.inventory.inventoryservice.utils.IterableUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final  UserRepository userRepository;
    public Page<UserEntity> searchPage(UserSearchDto searchDto) {
        Pageable pageable=  PageRequest.of(searchDto.getPage(), searchDto.getSize());
        Predicate predicate = UserPredicate.search(searchDto);
        return  userRepository.findAll(predicate, pageable);
    }

    public List<UserEntity> searchList(UserSearchDto searchDto) {
        Predicate predicate = UserPredicate.search(searchDto);
        return IterableUtils.toList(userRepository.findAll(predicate));
    }
}

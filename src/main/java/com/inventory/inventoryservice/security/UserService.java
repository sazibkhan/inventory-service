package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService{

  private final UserRepository userRepository;
  private final UserValidatorService userValidatorService;
  private final UserQueryService userQueryService;
  private final UserRoleRepository userRoleRepository;

  public UserRest registration(UserDto userDto) {
    UserEntity user = UserTransform.toUserEntity(userDto);
    var password = new BCryptPasswordEncoder().encode(userDto.getPassword());
    user.setPassword(password);
    userRepository.save(user);

    List<UserRoleEntity> userRoleList = userDto.getRoles().stream()
            .map(itm -> {
              UserRoleEntity userRoleItem = UserRoleTransform.toUserRoleEntity(itm);
              userRoleItem.setUser(user);
              userRoleItem.setRoleName(itm.getRoleName());
              return userRoleItem;
            }).collect(Collectors.toList());
    userRoleRepository.saveAll(userRoleList);
    return UserTransform.toUserRest(user);
  }

  @Transactional
  public UserRest saveUser(UserDto userDto){
    UserEntity user = UserTransform.toUserEntity(userDto);
    var password = new BCryptPasswordEncoder().encode(userDto.getPassword());
    user.setPassword(password);
    userRepository.save(user);

    List<UserRoleEntity> userRoleList = userDto.getRoles().stream()
      .map(itm -> {
        UserRoleEntity userRoleItem = UserRoleTransform.toUserRoleEntity(itm);
        userRoleItem.setUser(user);
        userRoleItem.setRoleName(itm.getRoleName());
        return userRoleItem;
      }).collect(Collectors.toList());
    userRoleRepository.saveAll(userRoleList);
    return UserTransform.toUserRest(user);
  }

  @Transactional
  public UserRest updateUser(Long id, UserDto userDto){
    UserEntity user = userValidatorService.ifFoundByIdReturnElseThrow(id);
    userDto.setPassword(user.getPassword());
    userRoleRepository.deleteAll(user.getRoles());
    user.setRoles(null);
    BeanUtils.copyProperties(userDto, user);
    userRepository.save(user);

    List<UserRoleEntity> newRoles = userDto.getRoles().stream()
        .map(itm-> {
          UserRoleEntity role = new UserRoleEntity();
          role.setUser(user);
          role.setRoleName(itm.getRoleName());
          return role;
        }).collect(Collectors.toList());

    user.setRoles(userRoleRepository.saveAll(newRoles));
    return UserTransform.toUserRest(user);
  }

  public void deleteUser(Long id){
    UserEntity user = userValidatorService.ifFoundByIdReturnElseThrow(id);
    userRepository.deleteById(user.getId());
  }

  public Page<UserRest> searchPage(UserSearchDto searchDto){
    Page<UserEntity> page = userQueryService.searchPage(searchDto);
    List<UserRest> resultList = UserTransform.toUserRestList(page.getContent());
    return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
  }

  public List<UserRest> searchList(UserSearchDto searchDto){
    return UserTransform.toUserRestList(userQueryService.searchList(searchDto));
  }


}

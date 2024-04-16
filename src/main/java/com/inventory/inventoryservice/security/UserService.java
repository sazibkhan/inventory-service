package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserDto;
import com.inventory.inventoryservice.security.model.UserEntity;
import com.inventory.inventoryservice.security.model.UserRest;
import com.inventory.inventoryservice.security.model.UserSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserValidatorService userValidatorService;
  private final UserQueryService userQueryService;

  public UserRest registerUser(UserDto userDto) {

    // var password = new BCryptPasswordEncoder().encode("092624");

    // todo: save data in user table
    // todo: save data in user roles table

    return new UserRest();
  }

  public UserRest saveUser(UserDto userDto) {
    UserEntity user = UserTransform.toUserEntity(userDto);
    userRepository.save(user);
    return UserTransform.toUserRest(user);
  }

  public UserRest updateUser(Long id, UserDto userDto) {
    UserEntity user = userValidatorService.ifFoundByIdReturnElseThrow(id);
    user.setFullName(userDto.getFullName());
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    userRepository.save(user);

    return UserTransform.toUserRest(user);
  }

  public void deleteUser(Long id){
    UserEntity  user= userValidatorService.ifFoundByIdReturnElseThrow(id);
    userRepository.deleteById(user.getId());
  }



  public Page<UserRest> searchPage(UserSearchDto searchDto) {
    Page<UserEntity> page = userQueryService.searchPage(searchDto);
    List<UserRest> resultList = UserTransform.toUserRestList(page.getContent());
    return new PageImpl<>(resultList, page.getPageable(), page.getTotalElements());
  }

  public List<UserRest> searchList(UserSearchDto searchDto) {
    return UserTransform.toUserRestList(userQueryService.searchList(searchDto));
  }

}

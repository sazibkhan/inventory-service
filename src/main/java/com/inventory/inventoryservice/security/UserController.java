package com.inventory.inventoryservice.security;

import com.inventory.inventoryservice.security.model.UserDto;
import com.inventory.inventoryservice.security.model.UserRest;
import com.inventory.inventoryservice.security.model.UserSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/create-user")
  @PreAuthorize("hasAnyRole('USER_CREATE','ROLE_ADMIN')")
  public ResponseEntity<UserRest> saveUser(@RequestBody UserDto userDto) {
    UserRest userRest = userService.saveUser(userDto);
    return ResponseEntity.status(HttpStatus.OK).body(userRest);
  }


  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('USER_UPDATE','ROLE_ADMIN')")
  public ResponseEntity<UserRest> updateUser(@PathVariable Long id,
                                             @RequestBody UserDto userDto) {
    UserRest userRest = userService.updateUser(id, userDto);
    return ResponseEntity.status(HttpStatus.OK).body(userRest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('USER_DELETE','ROLE_ADMIN')")
  public  ResponseEntity<String>deleteUser(@PathVariable Long id){
    userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully.");
  }

  @PostMapping("/search-page")
  @PreAuthorize("hasAnyRole('USER_SEARCH','ROLE_ADMIN')")
  public ResponseEntity<?> searchPage(@RequestBody UserSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(userService.searchPage(searchDto));
  }

  @PostMapping("/search-list")
  @PreAuthorize("hasAnyRole('USER_SEARCH','ROLE_ADMIN')")
  public ResponseEntity<?> searchList(@RequestBody UserSearchDto searchDto) {
    return ResponseEntity.status(HttpStatus.OK)
      .body(userService.searchList(searchDto));
  }

}

package com.inventory.inventoryservice.security.model;

import com.inventory.inventoryservice.model.SearchDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
public class UserSearchDto extends SearchDto {

    private Long id;
    private String fullName;
    private String username;
    private Boolean enabled;




}

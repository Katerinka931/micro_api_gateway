package com.micro.api_gateway.pojos;

import com.micro.api_gateway.entity.User;
import com.micro.api_gateway.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPojo {
    private Long id;
    private String username;
    private UserRole role;
    private String password;

    public static UserPojo fromEntity(User user) {
        UserPojo pojo = new UserPojo();
        pojo.setId(user.getId());
        pojo.setUsername(user.getUsername());
        pojo.setRole(user.getRole());
        return pojo;
    }

    public static User toEntity(UserPojo pojo) {
        User user = new User();
        user.setUsername(pojo.getUsername());
        user.setRole(pojo.getRole());
        return user;
    }
}
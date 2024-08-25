package org.digitinary.traninng.librarymanagmentsystem.mapper;


import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {


    User userModelToUser(UserModel userModel);

    UserModel userToUserModel(User user);
}

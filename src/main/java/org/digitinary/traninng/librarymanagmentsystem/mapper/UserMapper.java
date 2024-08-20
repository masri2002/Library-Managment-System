package org.digitinary.traninng.librarymanagmentsystem.mapper;


import org.digitinary.traninng.librarymanagmentsystem.entity.User;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id", defaultValue = "0") // Default value 0 for int id
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    User userModelToUser(UserModel userModel);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    UserModel userToUserModel(User user);
}

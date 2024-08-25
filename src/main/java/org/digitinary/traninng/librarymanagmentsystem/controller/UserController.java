package org.digitinary.traninng.librarymanagmentsystem.controller;

import jakarta.validation.Valid;
import org.digitinary.traninng.librarymanagmentsystem.dto.UserPageDto;
import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;
import org.digitinary.traninng.librarymanagmentsystem.model.UserModel;
import org.digitinary.traninng.librarymanagmentsystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
     @GetMapping
       public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
     }
     @PostMapping
     public ResponseEntity<?> addUser(@Valid @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.addUser(user));
     }
     @PostMapping("/{uId}/loanBook/{bId}")
    public ResponseEntity<?> loanBookById(@PathVariable Long uId, @PathVariable Long bId, @RequestBody Loan loan){
        userService.loanBook(uId,bId,loan);
        return ResponseEntity.ok("Book Loaned");
     }
    @PostMapping("/email-type/{type}")
    public ResponseEntity<?> getGmailUsers(
            @PathVariable String type,
            @RequestBody UserPageDto pageDto) {

        if (pageDto.getSortFiled() == null || pageDto.getSortFiled().isEmpty()) {
            throw new IllegalArgumentException("Sort field must not be null or empty");
        }

        return ResponseEntity.ok(userService.getUsersWithSpecificEmailType(pageDto, type));
    }

}

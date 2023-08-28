package com.example.springpr.repository.user;

import com.example.springpr.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {

    private List<UserDTO> users;

    public UserRepository() {
        this.users = List.of(
                new UserDTO("anton","anton@gmail.com","Антон", "Иванов", "pass1"),
                new UserDTO("vasya","вася@gmail.com","Вася", "Конч", "pass2"));
    }

    public UserDTO getByLogin(String email) {
        return this.users.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }

    public List<UserDTO> getAll() {
        return this.users;
    }
}
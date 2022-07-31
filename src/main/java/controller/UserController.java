package controller;

import annotation.GetMapping;
import annotation.PostMapping;
import db.DataBase;
import model.ClientResponse;
import model.Credential;
import model.User;
import service.UserService;
import types.HttpStatus;

import java.io.IOException;

public class UserController {

    @GetMapping(path = "/user")
    public ClientResponse getUserTest() {
        return new ClientResponse(HttpStatus.OK, null, "getUserTest");
    }

    @GetMapping(path = "/user/create")
    public ClientResponse createUserGet(User user) {
        DataBase.addUser(user);
        return new ClientResponse(HttpStatus.OK, null, user);
    }

    @PostMapping(path = "/user/create")
    public ClientResponse createUserPost(User user) {
        return UserService.getInstance().createUser(user);
    }

    @PostMapping(path = "/user/login")
    public ClientResponse login(Credential credential) {
        return UserService.getInstance().auth(credential);
    }

    @GetMapping(path = "/user/list")
    public ClientResponse getUsers() throws IOException {
        return UserService.getInstance().getUsers();
    }

}

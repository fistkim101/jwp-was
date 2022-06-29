package webserver.http.controller;

import db.DataBase;
import model.User;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestBody;
import webserver.http.Cookie;
import webserver.http.response.HttpResponse;

import java.util.Objects;

public class LoginController extends AbstractController {

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        RequestBody requestBody = httpRequest.getRequestBody();
        String userId = requestBody.get("userId");
        String password = requestBody.get("password");

        User user = DataBase.findUserById(userId);
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("유저를 찾을수 없습니다.");
        }

        if (user.getPassword().equals(password)) {
            httpResponse.redirect("/index.html");
            httpResponse.setCookie(new Cookie("logined", "true", "/"));
            return;
        }
        httpResponse.redirect("/user/login_failed.html");
        httpResponse.setCookie(new Cookie("logined", "false", "/"));
    }
}
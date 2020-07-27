package jp.search.service;

import jp.search.pojo.User;

import java.util.List;

public interface LoginService {
    //login
    User login();

    //get user list
    List<User> getUser();

}

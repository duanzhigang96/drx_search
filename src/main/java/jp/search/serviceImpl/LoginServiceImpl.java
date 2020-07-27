package jp.search.serviceImpl;

import jp.search.mapper.UserMapper;
import jp.search.pojo.User;
import jp.search.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login() {
        User user = userMapper.selectByPrimaryKey("1");
        return user;
    }

    @Override
    public List<User> getUser() {
        List<User> userList = userMapper.getAll();
        return userList;
    }
}

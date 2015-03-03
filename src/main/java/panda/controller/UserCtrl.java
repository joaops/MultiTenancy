package panda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import panda.repo.IUserDao;
import panda.repo.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Feng on 01-Mar-15.
 */
@Controller
public class UserCtrl {

    @Autowired
    IUserDao userDao;

    @ResponseBody
    @RequestMapping(value = "/**")
    public List<User> showUser(HttpServletRequest request) throws SQLException {
        return userDao.findAll();
    }
}

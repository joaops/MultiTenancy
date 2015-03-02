package panda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Feng on 01-Mar-15.
 */
@Controller
public class UserCtrl {

    @ResponseBody
    @RequestMapping(value = "/**")
    public String showUser(HttpServletRequest request) throws SQLException {
        return request.getRemoteUser();
    }
}

package br.com.joaops.smt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.joaops.smt.repository.ITestDao;
import br.com.joaops.smt.repository.IUserDao;
import br.com.joaops.smt.model.Test;
import br.com.joaops.smt.model.User;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserCtrl {
    
    @Autowired
    ITestDao testDao;
    
    @Autowired
    IUserDao userDao;
    
    @ResponseBody
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public List<User> showUser() throws SQLException {
        return userDao.findAll();
    }
    
    @ResponseBody
    @RequestMapping(value = "/**")
    public List<Test> showContent(HttpServletRequest request) throws SQLException {
        return testDao.findAll();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("home/index");
        mav.addObject("users", userDao.findAll());
        mav.addObject("tests", testDao.findAll());
        return mav;
    }
    
}
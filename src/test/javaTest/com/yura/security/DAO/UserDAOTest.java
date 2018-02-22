package javaTest.com.yura.security.DAO;

import com.yura.security.entity.User;
import com.yura.security.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/resourcesTest/applicationContext.xml",
        "/resourcesTest/hibernate-context.xml",
        "/resourcesTest/mvc-dispatcher-servlet.xml",
        "/resourcesTest/spring-security.xml"})
@WebAppConfiguration
public class UserDAOTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        User user = userService.getUser(1);

        Assert.assertEquals("Yura", user.getName());
        Assert.assertEquals("Yura", user.getFirstName());
        Assert.assertEquals("yura@gmail.com", user.getemail());
    }

    @Test
    public void addUser() {
        User user = new User("phil1245", "Phil", "ph@akz.com", "12345");
        userService.addUser(user);

        List<User> list = userService.getUserByName("phil1245");
        User addedUser = list.get(0);
        Assert.assertEquals(user.getName(), addedUser.getName());
        Assert.assertEquals(user.getFirstName(), addedUser.getFirstName());
        Assert.assertEquals(user.getemail(), addedUser.getemail());
        Assert.assertEquals(user.getPassword(), addedUser.getPassword());
    }

    @Test
    public void editUser() {
        List<User> list = userService.getUserByName("phil1245");
        User user = list.get(0);
        String userName = user.getName();

        user.setName("PHIL1");
        userService.editUser(user);

        List<User> list1 = userService.getUserByName("PHIL1");
        User editedUser = list1.get(0);
        Assert.assertNotEquals(userName, editedUser.getName());
    }
}

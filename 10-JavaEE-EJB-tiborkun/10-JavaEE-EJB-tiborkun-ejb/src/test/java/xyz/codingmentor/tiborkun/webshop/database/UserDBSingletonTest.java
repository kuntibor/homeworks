package xyz.codingmentor.tiborkun.webshop.database;

import xyz.codingmentor.tiborkun.webshop.services.UserDBSingleton;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;
import xyz.codingmentor.tiborkun.webshop.exceptions.UserIsAlreadyExistException;
import xyz.codingmentor.tiborkun.webshop.exceptions.UserIsNotExistException;

/**
 *
 * @author Tibor Kun
 */
public class UserDBSingletonTest {

    private static UserDBSingleton userDBSingleton;
    private static UserEntity firstUser;
    private static UserEntity secondUser;

    @BeforeClass
    public static void init() {
        firstUser = new UserEntity.Builder()
                .username("Béla")
                .password("aA12.,")
                .email("bela@test.com").build();
        secondUser = new UserEntity.Builder()
                .username("Kata")
                .password("aA12.,")
                .email("kata@test.com").build();
        userDBSingleton = new UserDBSingleton();
        userDBSingleton.addUser(firstUser);
    }

    @Test
    public void rightAddUser() {
        userDBSingleton.addUser(secondUser);
        String username = secondUser.getUsername();
        Assert.assertEquals(secondUser, userDBSingleton.getUser(username));
        userDBSingleton.deleteUser(secondUser);
    }

    @Test(expected = UserIsAlreadyExistException.class)
    public void wrongAddUserAlreadyExist() {
        String existingUsername = firstUser.getUsername();
        UserEntity existingUser = new UserEntity.Builder().username(existingUsername).build();
        userDBSingleton.addUser(existingUser);
    }

    @Test
    public void rightGetUser() {
        String username = firstUser.getUsername();
        Assert.assertEquals(firstUser, userDBSingleton.getUser(username));
    }

    @Test(expected = UserIsNotExistException.class)
    public void wrongGetUserNotExist() {
        String wrongUsername = "Not exist";
        userDBSingleton.getUser(wrongUsername);
    }

    @Test
    public void rightAuthenticate() {
        String username = firstUser.getUsername();
        String password = firstUser.getPassword();
        Assert.assertTrue(userDBSingleton.authenticate(username, password));
    }

    @Test
    public void wrongAuthenticate() {
        String username = firstUser.getUsername();
        String wrongPassword = firstUser.getPassword() + "wrong";
        Assert.assertFalse(userDBSingleton.authenticate(username, wrongPassword));
    }

    @Test
    public void rightModifyUser() {
        userDBSingleton.addUser(secondUser);
        String newEmail = "new@test.com";
        secondUser.setEmail(newEmail);
        UserEntity modifiedSecondUser = secondUser;
        Assert.assertEquals(modifiedSecondUser, userDBSingleton.modifyUser(modifiedSecondUser));
        userDBSingleton.deleteUser(modifiedSecondUser);
    }

    @Test
    public void rightDeleteUser() {
        userDBSingleton.addUser(secondUser);
        Assert.assertEquals(secondUser, userDBSingleton.deleteUser(secondUser));
    }

    @Test
    public void rightGetAllUser() {
        List<UserEntity> sampleList = new ArrayList<>();
        sampleList.add(firstUser);
        Assert.assertEquals(sampleList, userDBSingleton.getAllUser());
    }

}

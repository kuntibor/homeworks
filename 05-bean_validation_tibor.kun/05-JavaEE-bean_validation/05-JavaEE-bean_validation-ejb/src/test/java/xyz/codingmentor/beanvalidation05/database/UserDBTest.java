package xyz.codingmentor.beanvalidation05.database;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.userexpection.UserDBIsEmptyExpection;
import xyz.codingmentor.beanvalidation05.userexpection.UserIsAlreadyExistExpection;
import xyz.codingmentor.beanvalidation05.userexpection.UserIsNotExistExpection;

/**
 *
 * @author teiep
 */
public class UserDBTest {

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

        UserDB.INSTANCE.addUser(firstUser);
    }

    @Test
    public void rightAddUser() {
        UserDB.INSTANCE.addUser(secondUser);
        String username = secondUser.getUsername();
        Assert.assertEquals(secondUser, UserDB.INSTANCE.getUser(username));
        UserDB.INSTANCE.deleteUser(secondUser);
    }

    @Test(expected = UserIsAlreadyExistExpection.class)
    public void wrongAddUserAlreadyExist() {
        String existingUsername = firstUser.getUsername();
        UserEntity existingUser = new UserEntity.Builder().username(existingUsername).build();
        UserDB.INSTANCE.addUser(existingUser);
    }

    @Test
    public void rightGetUser() {
        String username = firstUser.getUsername();
        Assert.assertEquals(firstUser, UserDB.INSTANCE.getUser(username));
    }

    @Test(expected = UserIsNotExistExpection.class)
    public void wrongGetUserNotExist() {
        String wrongUsername = "Not exist";
        UserDB.INSTANCE.getUser(wrongUsername);
    }

    @Test
    public void rightAuthenticate() {
        String username = firstUser.getUsername();
        String password = firstUser.getPassword();
        Assert.assertTrue(UserDB.INSTANCE.authenticate(username, password));
    }

    @Test
    public void wrongAuthenticate() {
        String username = firstUser.getUsername();
        String wrongPassword = firstUser.getPassword() + "wrong";
        Assert.assertFalse(UserDB.INSTANCE.authenticate(username, wrongPassword));
    }

    @Test
    public void rightModifyUser() {
        UserDB.INSTANCE.addUser(secondUser);
        String newEmail = "new@test.com";
        secondUser.setEmail(newEmail);
        UserEntity modifiedSecondUser = secondUser;
        Assert.assertEquals(modifiedSecondUser, UserDB.INSTANCE.modifyUser(modifiedSecondUser));
        UserDB.INSTANCE.deleteUser(modifiedSecondUser);
    }

    @Test
    public void rightDeleteUser() {
        UserDB.INSTANCE.addUser(secondUser);
        Assert.assertEquals(secondUser, UserDB.INSTANCE.deleteUser(secondUser));
    }

    @Test
    public void rightGetAllUser() {
        List<UserEntity> sampleList = new ArrayList<>();
        sampleList.add(firstUser);
        Assert.assertEquals(sampleList, UserDB.INSTANCE.getAllUser());
    }

    @Test(expected = UserDBIsEmptyExpection.class)
    public void wrongGetAllUser() {
        UserDB.INSTANCE.deleteUser(firstUser);
        List<UserEntity> sampleList = new ArrayList<>();
        Assert.assertEquals(sampleList, UserDB.INSTANCE.getAllUser());
        UserDB.INSTANCE.addUser(firstUser);
    }
}

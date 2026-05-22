package factories;

import com.github.javafaker.Faker;
import models.User;

public class UserFactory {

    private static final String default_password = "ilovemydogpaco";
    private static final Faker faker;

    static {
        faker = new Faker();
    }

    public static User createDefault() {

        var user = new User();
        user.setEmail(faker.internet().safeEmailAddress());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setTelephone(faker.phoneNumber().phoneNumber());
        user.setPassword(default_password);
        user.setPasswordConfirm(default_password);
        user.setSubscribe(false);
        user.setPrivacyPolicy(true);
        return user;

    }

}

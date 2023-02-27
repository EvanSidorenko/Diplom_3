package api.client;

import com.github.javafaker.Faker;

public class UserGenerator {

   Faker faker = new Faker();

   public User getUserWithRandomCreds() {
      return new User(faker.internet().emailAddress(), faker.internet().password(), faker.name().username());
   }

}

package testdata;

import org.testng.annotations.DataProvider;

public class LoginTestData {

    @DataProvider(name = "testAdmins")
    public Object[][] getValidAdmins() {
        return new Object[][]{
                {"Admin", "admin123"}
        };
    }

    @DataProvider(name = "invalidUserCredentials")
    public Object[][] getInvalidUsers() {
        return new Object[][]{
                {"testuser12", "admin123"}
        };
    }
}

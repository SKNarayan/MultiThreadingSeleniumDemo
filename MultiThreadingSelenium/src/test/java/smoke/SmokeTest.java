package smoke;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchResultPage;

//@Test(singleThreaded = true)
public class SmokeTest extends BaseTest {

    @Test
    public void SearchDelhi() throws InterruptedException {

        home
                .enterDestination(location.get("delhi"))
                .findHotels()
                .verifyDestination(location.get("delhi"))
                .view_Rates()
                .select_room();

    }

    @Test
    public void SearchParis() throws InterruptedException {
        home
                .enterDestination(location.get("paris"))
                .findHotels()
                .verifyDestination(location.get("paris"));

    }

   // @Test
    public void SignIn(){
        home
                .signIn(userName.get("validUserName"), password.get("validPassword"));

    }

}

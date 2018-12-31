package fab.io.masterinsta.main;

import java.io.IOException;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import fab.io.masterinsta.auth.InstagramAuthenticatorFactory;

public class App
{
    public static void main(String[] args)
    {
        InstagramAuthenticatorFactory instaAuth = InstagramAuthenticatorFactory.getInstance();
        Instagram4j instagram = instaAuth.connect("fabio.bonnie@gmail.com", "b0nn13");
        //        Instagram4j instagram = instaAuth.connect("contato.matchleads@gmail.com", "M@tchLe@d99");
        try
        {
            instagram.login();
            InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("fabio0ficial"));
            System.out.println("ID for @github is " + userResult.getUser().getPk());
            System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

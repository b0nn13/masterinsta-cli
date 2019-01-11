package fab.io.masterinsta.main;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserInfoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import fab.io.masterinsta.auth.InstagramAuthenticatorFactory;

public class App2 {
	private static final InstagramAuthenticatorFactory instaAuth = InstagramAuthenticatorFactory.getInstance();
	private static Instagram4j INSTAGRAM;

	public static void main(String[] args) {
		INSTAGRAM = instaAuth.connect("USER", "PASSWORD");
		try {
			InstagramSearchUsernameResult userResult = getUserData(668228693L);
			System.out.println("ID for " + userResult.getUser().getUsername() + " is " + userResult.getUser().getPk());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static InstagramSearchUsernameResult getUserData(Long userId) throws ClientProtocolException, IOException {
		INSTAGRAM.login();
		InstagramSearchUsernameResult userResult = INSTAGRAM.sendRequest(new InstagramGetUserInfoRequest(userId));
		
		return userResult;
	}


}
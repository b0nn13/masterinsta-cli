package fab.io.masterinsta.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserReelMediaFeedRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserReelMediaFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import fab.io.masterinsta.auth.InstagramAuthenticatorFactory;

public class App {
	private static final InstagramAuthenticatorFactory instaAuth = InstagramAuthenticatorFactory.getInstance();
	private static Instagram4j INSTAGRAM;

	public static void main(String[] args) {
		INSTAGRAM = instaAuth.connect("*****@gmail.com", "******");
		try {
			InstagramSearchUsernameResult userResult = getUserData("*****");

//			System.out.println("\n\n");
//			Set<String> followers = getFollowers(userResult);
//			System.out.println("\n\n");
//			Set<String> followings = getFollowings(userResult);
//
//			followings.removeAll(followers);
//			System.out.println("Pessoas que você segue e não te seguem: ");
//			followings.forEach(following -> {
//				System.out.println(following);
//			});

			List<Liker> likers = getLikers(userResult);
			System.out.println("\nRANKING DE CURTIDAS");
			likers.stream().sorted().forEach(l -> {
				System.out.println(l.getName() + " " + l.getLikes() + " curtidas");
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static InstagramSearchUsernameResult getUserData(String name) throws ClientProtocolException, IOException {
		INSTAGRAM.login();
		InstagramSearchUsernameResult userResult = INSTAGRAM.sendRequest(new InstagramSearchUsernameRequest(name));
		System.out.println("ID for " + userResult.getUser().getUsername() + " is " + userResult.getUser().getPk());
		System.out.println("Number of followers: " + userResult.getUser().getFollower_count());
		System.out.println("Number of followings: " + userResult.getUser().getFollowing_count());
		return userResult;
	}

	private static Set<String> getFollowers(InstagramSearchUsernameResult userResult) throws ClientProtocolException, IOException {
		Set<String> followers = new HashSet<>();
		InstagramGetUserFollowersResult githubFollowers = INSTAGRAM.sendRequest(new InstagramGetUserFollowersRequest(userResult.getUser().getPk()));
		List<InstagramUserSummary> users = githubFollowers.getUsers();
		for (InstagramUserSummary user : users) {
			System.out.println("User " + user.getUsername() + " follows YOU!");
			followers.add(user.getUsername());
		}
		return followers;
	}

	private static Set<String> getFollowings(InstagramSearchUsernameResult userResult) throws ClientProtocolException, IOException {
		Set<String> following = new HashSet<>();
		InstagramGetUserFollowersResult githubFollowers = INSTAGRAM.sendRequest(new InstagramGetUserFollowingRequest(userResult.getUser().getPk()));
		List<InstagramUserSummary> users = githubFollowers.getUsers();
		for (InstagramUserSummary user : users) {
			System.out.println("Você está seguindo: " + user.getUsername());
			following.add(user.getUsername());
		}
		return following;
	}

	private static List<Liker> getLikers(InstagramSearchUsernameResult userResult) throws ClientProtocolException, IOException {
		Map<String, Liker> likersResult = new HashMap<>();
		InstagramFeedResult feedResults = INSTAGRAM.sendRequest(new InstagramUserFeedRequest(userResult.getUser().getPk()));
		InstagramGetUserReelMediaFeedResult feedReelResults = INSTAGRAM.sendRequest(new InstagramGetUserReelMediaFeedRequest(userResult.getUser().getPk()));
		List<InstagramFeedItem> items = feedResults.getItems();
		if (items != null) {
			items.forEach(feed -> {
				List<InstagramUserSummary> likers = feed.getLikers();
				if (likers != null) {
					likers.forEach(liker -> {
						String likerName = liker.getUsername();
						Liker item = likersResult.get(likerName);
						if (item == null) {
							item = new Liker();
							item.setName(likerName);
							item.setLikes(1L);
							item.setUserSummary(liker);
							likersResult.put(likerName, item);
						} else {
							item.setLikes(item.getLikes() + 1);
						}
					});
				}
			});
		}
		List<Liker> list = new ArrayList<>(likersResult.values());
		return list;
	}

}
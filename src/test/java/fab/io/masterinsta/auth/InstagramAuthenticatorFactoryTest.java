package fab.io.masterinsta.auth;

import static org.hamcrest.CoreMatchers.*;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InstagramAuthenticatorFactoryTest
{
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }
    
    @Before
    public void setUp() throws Exception
    {
    }
    
    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testGetConnection() throws Exception
    {
        InstagramAuthenticatorFactory instaAuth = InstagramAuthenticatorFactory.getInstance();
        Instagram4j connection = instaAuth.connect("contato.matchleads@gmail.com", "M@tchLe@d99");
        MatcherAssert.assertThat(connection.isLoggedIn(), is(true));
    }

    @Test
    public void testGetInstance() throws Exception
    {
        InstagramAuthenticatorFactory instaAuth = InstagramAuthenticatorFactory.getInstance();
        MatcherAssert.assertThat(instaAuth, notNullValue());
    }
    
    @Test
    public void testGetInstanceIsSingleton() throws Exception
    {
        InstagramAuthenticatorFactory instaAuth1 = InstagramAuthenticatorFactory.getInstance();
        MatcherAssert.assertThat(instaAuth1, notNullValue());
        InstagramAuthenticatorFactory instaAuth2 = InstagramAuthenticatorFactory.getInstance();
        MatcherAssert.assertThat(instaAuth1, equalTo(instaAuth2));
        
    }
    
}

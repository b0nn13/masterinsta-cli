package fab.io.masterinsta.auth;

import java.io.IOException;

import org.brunocvcunha.instagram4j.Instagram4j;

/**
 * Factory of Instagram4j connections
 * @author fabio.xavier
 *
 */
public class InstagramAuthenticatorFactory
{
    private static InstagramAuthenticatorFactory INSTANCE;
    
    /**
     * Default constructor for {@link InstagramAuthenticatorFactory}
     */
    private InstagramAuthenticatorFactory()
    {
    }
    
    /**
     * Get instance
     * @return instance for {@link InstagramAuthenticatorFactory}
     */
    public static InstagramAuthenticatorFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new InstagramAuthenticatorFactory();
        }
        return INSTANCE;
    }
    
    /**
     * Method to get connection from Instagram4j
     * @param username user name
     * @param password password
     * @return an instance for {@link Instagram4j}
     */
    public Instagram4j connect(String username, String password)
    {
        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        try
        {
            instagram.login();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Erro ao conectar no Instagram com a conta");
        }
        return instagram;
    }
}

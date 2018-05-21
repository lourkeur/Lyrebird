package moe.lyrebird.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import moe.lyrebird.model.sessions.SessionManager;
import moe.lyrebird.model.twitter4j.Twitter4JComponents;
import moe.lyrebird.model.twitter4j.TwitterHandler;
import twitter4j.Twitter;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Back-end (Twitter, persistence etc.) components go here.
 * <p>
 * For Twitter4J wrapping see {@link Twitter4JComponents}
 */
@Configuration
public class BackendComponents {

    @Bean
    @Lazy
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public TwitterHandler twitterHandler(final SessionManager sessionManager, final Twitter twitter) {
        return new TwitterHandler(sessionManager, twitter);
    }

}

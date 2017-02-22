package moe.lyrebird.model.sessions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import twitter4j.auth.OAuth2Authorization;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * A session represents one Twitter handle in the object model.
 * Sessions are managed by the {@link SessionManager}.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session implements Serializable {
    
    private static final long serialVersionUID = -9038797949832585362L;
    
    @Id
    private Long uid;
    
    private OAuth2Authorization oAuth2Authorization;
    private String userHandle;
    
    @Override
    public String toString() {
        return this.userHandle;
    }
}

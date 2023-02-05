package nam.gor.machineryleasing.auth.token;

import nam.gor.machineryleasing.models.entities.User;

public interface TokenExtractor {
    User extractUserFromToken(String token);
}

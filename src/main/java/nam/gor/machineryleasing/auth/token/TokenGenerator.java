package nam.gor.machineryleasing.auth.token;

import nam.gor.machineryleasing.models.entities.User;

public interface TokenGenerator {
    String generateTokenForUser(User user);
}

package nam.gor.machineryleasing.auth.encoder;

public interface PasswordEncoder {
    String hashPassword(String password);
    boolean comparePasswordAndHash(String password, String hash);
}

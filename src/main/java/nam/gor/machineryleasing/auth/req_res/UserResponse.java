package nam.gor.machineryleasing.auth.req_res;


import lombok.Value;
import lombok.With;
import nam.gor.machineryleasing.models.entities.User;

@Value
public class UserResponse {
    String id;
    String name;
    String email;
    @With
    String token;

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null
        );
    }
}

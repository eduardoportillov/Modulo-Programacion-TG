package Security;

import java.util.UUID;

import com.google.gson.Gson;

import Repositories.ISecurityUtils;

public class SecurityUtils implements ISecurityUtils{

    public String generateToken(UUID keyUser) {
        int expirationTime = 200000 * 60 * 10;
        String jsonUser = new Gson().toJson(keyUser);
        String token = Security.JWT.JWT.create(jsonUser, expirationTime);
        return token;
    }

        public UUID decodeToken(String token) {
        String jsonUser = Security.JWT.JWT.decode(token);
        UUID keyToken = new Gson().fromJson(jsonUser, UUID.class);
        return keyToken;
    }
}

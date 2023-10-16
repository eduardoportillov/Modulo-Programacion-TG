package JWT;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWT {

    private static final String JWT_TOKEN = "finanzas-personales";
    private static final String ISSUER = "modulo-pro";

    public static String create(String user, int expirationTime) {
        Builder builder = com.auth0.jwt.JWT.create().withIssuer(ISSUER)
                .withSubject(user)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime));

        builder.withClaim("name", "Test name");
        Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN);
        String token = builder.sign(algorithm);
        return token;
    }

    public static String decode(String token) {
        Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN);
        JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT decodedJWT = verifier.verify(token.replaceAll("Bearer ", ""));
        Map<String, Claim> claims = decodedJWT.getClaims();
        return claims.get("sub").asString();
    }
}
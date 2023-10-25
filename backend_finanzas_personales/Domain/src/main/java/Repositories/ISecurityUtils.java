package Repositories;

import java.util.UUID;

public interface ISecurityUtils {
    public String generateToken(UUID keyUser);
    public UUID decodeToken(String token);
}

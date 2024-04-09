package entity;

public record Client(
        String firstName,
        String secondName,
        String email,
        String phone,
        String id,
        String passwordHash
) {
}

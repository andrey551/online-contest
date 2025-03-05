package com.tad.user.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDGenerator {
    public static UUID generateUUIDFromString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            byte[] uuidBytes = new byte[16];
            System.arraycopy(hash, 0, uuidBytes, 0, 16);

            return UUID.nameUUIDFromBytes(uuidBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found", e);
        }
    }
}

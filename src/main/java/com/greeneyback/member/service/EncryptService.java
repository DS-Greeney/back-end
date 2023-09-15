package com.greeneyback.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class EncryptService {

    private final AesBytesEncryptor encryptor;

    public String encryptEmail(String email) {
        byte[] encrypt = encryptor.encrypt(email.getBytes(StandardCharsets.UTF_8));
        return byteArrayToString(encrypt);
    }

    public String decryptEmail(String encryptString) {
        byte[] decryptBytes = stringToByteArray(encryptString);
        byte[] decrypt = encryptor.decrypt(decryptBytes);
        return new String(decrypt, StandardCharsets.UTF_8);
    }

    public String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte abyte : bytes) {
            sb.append(abyte);
            sb.append(" ");
        }
        return sb.toString();
    }

    public byte[] stringToByteArray(String byteString) {
        String[] split = byteString.split("\\s");
        ByteBuffer buffer = ByteBuffer.allocate(split.length);
        for (String s : split) {
            buffer.put((byte) Integer.parseInt(s));
        }
        return buffer.array();
    }
}

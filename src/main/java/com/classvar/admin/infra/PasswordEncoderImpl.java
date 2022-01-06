package com.classvar.admin.infra;

import com.classvar.admin.application.common.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.SecureRandom;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {
  static final int SALT_SIZE = 16;

  @Override
  public String encode(String password, String salt) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 해시함수를 사용

      String hashedPW = password;

      // key-stretching
      for (int i = 0; i < 10000; i++) {
        hashedPW += salt; // 패스워드와 Salt 를 합쳐 새로운 문자열 생성
        md.update(hashedPW.getBytes()); // temp 의 문자열을 해싱하여 md 에 저장해둔다
        hashedPW = byteToString(md.digest()); // md 객체의 다이제스트를 얻어 password 를 갱신한다
      }

      return hashedPW;
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public String getSalt() {
    SecureRandom rnd = new SecureRandom();
    byte[] toSalt = new byte[SALT_SIZE];
    rnd.nextBytes(toSalt);

    return byteToString(toSalt);
  }

  private String byteToString(byte[] arr) {
    StringBuilder sb = new StringBuilder();

    for (byte item : arr) {
      sb.append(String.format("%02x", item));
    }
    return sb.toString();
  }
}

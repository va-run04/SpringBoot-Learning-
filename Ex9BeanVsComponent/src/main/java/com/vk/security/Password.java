package com.vk.security;

// Imagine this is a third-party library class
// You can't add @Component to it because you don't own the source code
// It needs "SHA" or "MD5" passed to its constructor — custom creation logic
public class Password {

    private String algorithm;

    public Password(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return "Password{algorithm='" + algorithm + "'}";
    }
}
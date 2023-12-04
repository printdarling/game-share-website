package com.wang.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PwdEncoder {
    public String md5EncoderTwice(String password){
        return DigestUtils.md5Hex(DigestUtils.md5Hex(password));
    }
}

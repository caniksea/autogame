package com.caniksea.poll.rankinteractive.autogame.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringHelper {
    public boolean isEmptyOrNull(String str) {
        return StringUtils.isEmpty(str);
    }

    public String setEmptyIfNull(String str) {
        if (isEmptyOrNull(str)) return "";
        return str;
    }

    public String generateId(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

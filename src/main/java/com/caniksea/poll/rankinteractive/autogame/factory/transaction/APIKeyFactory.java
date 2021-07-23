package com.caniksea.poll.rankinteractive.autogame.factory.transaction;

import com.caniksea.poll.rankinteractive.autogame.entity.transaction.APIKey;
import com.caniksea.poll.rankinteractive.autogame.helper.PasswordHelper;
import com.caniksea.poll.rankinteractive.autogame.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class APIKeyFactory {

    private StringHelper stringHelper;
    private PasswordHelper passwordHelper;

    @Autowired public APIKeyFactory(StringHelper stringHelper, PasswordHelper passwordHelper) {
        this.stringHelper = stringHelper;
        this.passwordHelper = passwordHelper;
    }

    public Optional<APIKey> build(String keyType, String value) {
        APIKey apiKey = null;
        if (!(this.stringHelper.isEmptyOrNull(keyType) && this.stringHelper.isEmptyOrNull(value)))
            apiKey = APIKey.builder().keyType(keyType).value(this.passwordHelper.passwordEncoder().encode(value)).build();
        return Optional.ofNullable(apiKey);
    }
}

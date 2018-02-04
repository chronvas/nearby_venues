package com.chronvas.nearbyvenues.repo.api;

import com.chronvas.nearbyvenues.BuildConfig;

import java.util.HashMap;
import java.util.Map;

class Helper {
    static final Map<String, String> QUERY_CONSTANTS;

    static {
        QUERY_CONSTANTS = new HashMap<>();
        QUERY_CONSTANTS.put("v", BuildConfig.API_VERSION);
        QUERY_CONSTANTS.put("client_id", BuildConfig.CLIENT_ID);
        QUERY_CONSTANTS.put("client_secret", BuildConfig.CLIENT_SECRET);
    }
}

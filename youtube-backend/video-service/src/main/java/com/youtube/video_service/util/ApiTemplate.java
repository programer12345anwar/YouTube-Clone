package com.youtube.video_service.util;

import java.util.Map;

public interface ApiTemplate {

    // http://localhost/api/v1/central/user/register?userName=anwar&userEmail=mdanwar40212@gmail.com
    public Object makeGetCall(String apiUrl, String endPoint, Map<String, String> queryParams);

    // public Object makePostCall(String apiUrl, String endPoint, Map<String, String> queryParams, Object requestBody, String token);

    public Object makePostCall(String apiUrl, String endPoint, Map<String, String> queryParams, Object requestBody);

    public Object makePutCall(String apiUrl, String endPoint, Map<String, String> queryParams, Object requestBody);
}

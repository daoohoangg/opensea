//package com.example.authservice.repository.httpsclient;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@FeignClient(name = "profile-service", url = "${app.services.profile}",
//        configuration = { AuthenticationRequestInterceptor.class })
//public interface ProfileClient {
//    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileCreationRequest request);
//}

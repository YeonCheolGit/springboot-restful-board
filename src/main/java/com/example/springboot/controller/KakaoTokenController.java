package com.example.springboot.controller;

import com.example.springboot.model.response.SingleResult;
import com.example.springboot.service.exception.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"4. Social"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/social/login")
public class KakaoTokenController {

    private final Environment environment;
    private final ResponseService responseService;

    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    /*
     1. 프론트에서 카카오 회원가입 버튼 클릭 합니다.
     2. 카카오 인가 코드 발급 위한 URI 생성 후 클라이언트 response
     */
    @GetMapping(value = "/kakaoAuthCode")
    @ApiOperation(value = "카카오 인가 코드 발급 URI")
    public ResponseEntity<SingleResult> kakaoSignUpButton() {
        StringBuilder loginUrl = new StringBuilder()
                .append(environment.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect)
                .append("&response_type=code");
        return new ResponseEntity<>(responseService.getSingleResult(loginUrl), HttpStatus.OK);
    }
}

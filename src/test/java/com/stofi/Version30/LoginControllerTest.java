package com.stofi.Version30;

import org.json.JSONException;
import org.json.JSONObject;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {


        @Autowired
        private TestRestTemplate restTemplate;

        @Value("${jwt.algorithm.key}")
        private String jwtSecret;

        @Test
        public void testSuccessfulLogin() throws JSONException {
            // Arrange
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>("{\"username\":\"username1\",\"password\":\"password123A\"}", headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/auth/login", request, String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            String jwtToken = jsonObject.getString("jwt");
            System.out.println(response);
            // Assert
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(jwtToken).isNotBlank();
        }

        private String parseJWT(String jwtToken) {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return claims.getSubject();
        }
    }


package com.example.RestTemplateTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTemplateTest {

        @Resource(name = "apacheRestTemplate")
        RestTemplate apacheRestTemplate;

        @Resource(name = "defaultRestTemplate")
        RestTemplate defaultRestTemplate;

        @Test
        @LoadBalanced
        public void testDefaultRestTemplate0 () {

            ResponseEntity<String> responseEntity;
            try {
                HttpEntity<String> httpEntity = new HttpEntity<>("{ }");
                responseEntity = defaultRestTemplate.exchange("http://localhost:8080/test/0", HttpMethod.POST, httpEntity, String.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getRawStatusCode());
                System.out.println(e.getResponseBodyAsString());
                Assert.fail("Unexpected exception!");
                return;
            }
            Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            Assert.assertEquals("{ \"result\" = \"OK\" }", responseEntity.getBody().toString());

        }


        @Test
        @LoadBalanced
        public void testApacheRestTemplate0 () {

            ResponseEntity<String> responseEntity;
            try {
                HttpEntity<String> httpEntity = new HttpEntity<>("{ }");
                responseEntity = apacheRestTemplate.exchange("http://localhost:8080/test/0", HttpMethod.POST, httpEntity, String.class);
            } catch (HttpClientErrorException e) {
                System.out.println(e.getRawStatusCode());
                System.out.println(e.getResponseBodyAsString());
                Assert.fail("Unexpected exception!");
                return;
            }
            Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            Assert.assertEquals("{ \"result\" = \"OK\" }", responseEntity.getBody().toString());

        }

        @Test
        @LoadBalanced
        public void testDefaultRestTemplate1 () {

            try {
                HttpEntity<String> httpEntity = new HttpEntity<>("{ }");
                ResponseEntity<String> responseEntity = defaultRestTemplate.exchange("http://localhost:8080/test/1", HttpMethod.POST, httpEntity, String.class);
            } catch (HttpClientErrorException e) {

                Assert.assertEquals(400, e.getRawStatusCode());
                Assert.assertEquals("{ \"result\" = \"NOT OK\" }", e.getResponseBodyAsString());
                System.out.println(e.getRawStatusCode());
                System.out.println(e.getResponseBodyAsString());
                return;
            }
            Assert.fail("should not get here");

        }

        @Test
        @LoadBalanced
        public void testApacheRestTemplate1 () {

            try {
                HttpEntity<String> httpEntity = new HttpEntity<>("{ }");
                ResponseEntity<String> responseEntity = apacheRestTemplate.exchange("http://localhost:8080/test/1", HttpMethod.POST, httpEntity, String.class);
            } catch (HttpClientErrorException e) {
                Assert.assertEquals(400, e.getRawStatusCode());
                Assert.assertEquals("{ \"result\" = \"NOT OK\" }", e.getResponseBodyAsString());
                System.out.println(e.getRawStatusCode());
                System.out.println(e.getResponseBodyAsString());
                return;
            }
            Assert.fail("should not get here");

        }


}

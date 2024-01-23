package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

@RequestMapping("hello")
@Controller // http 통신을 굉장히 편하게 할 수 있다.
//모든 요청에 ResponseBody를 붙이고 싶다면 RestController사용
//@RestController : 데이터만 주어야 하는 상황에 모든 부분에 ResponseBody를 붙여야하는데 그와 같은 역할을 해준다.
//데이터만 주어야하는 상황 -> 프론트가 따로있는 상황(CSR, react나 vue를 사용하고있구나 생각
// 클래스차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정

public class HelloController {

    //    @responseBody가 없고, return타입이 String이면 templates밑에 html파일 리턴(화면찾으러감)
//    data만을 return할때는 @ResponseBody를 붙인다.(csr, restapi)
    @GetMapping("string") // url 뒷부분 설정
//    @RequestMapping(value = "string", method = RequestMethod.GET)
    @ResponseBody // ResponseBody return
    public String helloString() {
        return "hello_string"; //templates밑에 "hello_string"파일을 리턴하고 파일이 없으면 에러
    }

    @GetMapping("json") // url 뒷부분 설정
    @ResponseBody // ResponseBody return
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setName("jang");
        hello.setEmail("jang@naver.com");
        hello.setPassword("123123");
        return hello;
    }

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }

    //get메소드 url 데이터 받는 법
    //1. parameter 방식 : ?키=밸류
    //2. pathvariable 방식(더 restful하다 rest의 규격에 더 맞다) : localhost:8080/model/1

    @GetMapping("screen-model-param")
//    ?name=hongildong의 방식으로 호출(파라미터 호출방식)
    public String helloScreenModelParam(@RequestParam(value = "name") String inputname, Model model) {
//        화면에 data를 넘기고 싶을때는 Model객체 사용
//        model에 key:value형식으로 전달 // return screen
        model.addAttribute("myData", inputname);
        return "screen";
    }

    //    pathvariable방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어,
//    좀 더 RestFul API 디자인에 적합(현대적인 아키텍처에 적합)
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("myData", id);
        return "screen";
    }

    //    PostMapping 테스트
//    ⭐Form태그로 x-www 데이터 처리
    @GetMapping("form-screen")
    public String helloFormScreen() {
        return "hello-form-screen";
    }

    @PostMapping("form-post-handle")
//    form태그를 통한 body의 데이터 형태가 key1=value1&key2=value2
    @ResponseBody
    public String formPostHandle(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "password") String password) {
        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        return "정상처리";
    }

    //    Form 태그 2번째 방법
    @PostMapping("form-post-handle2")
    @ResponseBody
//    Spring에서 Hello클래스의 인스턴스를 자동 매핑하여 생성
//    form-data형식 즉, x-www-url인코딩 형식의 경우 사용
//    이를 데이터 바인딩이라 부른다. ⭐(Hello클래스에 setter 필수)
    public String formPostHandle2(Hello hello) {
        System.out.println(hello);
        return "정상처리";
    }


    //    ⭐json데이터 처리
    @GetMapping("/json-screen")
    public String helloJsonScreen() {
        return "hello-json-screen";
    }

    //    1번째 방법
    @PostMapping("/json-post-handle1")
    @ResponseBody
//    @ResponseBody는 json으로 post요청이 들어왔을때 body에서 data를 꺼내기 위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body) {
        System.out.println("name : " + body.get("name"));
        System.out.println("email : " + body.get("email"));
        System.out.println("password : " + body.get("password"));
        Hello hello = new Hello();
        hello.setName(body.get("name"));
        hello.setEmail(body.get("email"));
        hello.setPassword(body.get("password"));
        return "ok";
    }

    //    2번째 방법
    @PostMapping("/json-post-handle2")
    @ResponseBody
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        Hello hello = new Hello();
        hello.setName(body.get("name").asText());
        hello.setEmail(body.get("email").asText());
        hello.setPassword(body.get("password").asText());
        return "ok";
    }

    //    ⭐3번째 방법 사용하기
    @PostMapping("/json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        return "ok";
    }

    @PostMapping("/httpservlet")
    @ResponseBody
    public String httpServletTest(HttpServletRequest req){
//        HttpServletRequest객체에서 header정보 추출
        System.out.println(req.getContentType());
        System.out.println(req.getMethod());
//        session : 로그인(auth) 정보에서 필요한 정보값을 추출할 때 많이 사용
        System.out.println(req.getSession());
        System.out.println(req.getHeader("Accept"));

//        HttpServletRequest객체에서 body정보 추출
        System.out.println(req.getParameter("test1"));
        System.out.println(req.getParameter("test2"));
//        req.getReader()를 통해 ByfferedReader로 받아 직접 파싱

        return "OK";
    }

//    JSON 데이터 처리
    @GetMapping("/hello-servlet-jsp-get")
    public String helloServletJspGet(Model model){
        model.addAttribute("myData", "jsp test data");
        return "hello-jsp";
    }

    public void helloBuilderTest(){
        Hello hello = Hello.builder()
                .name("hong")
                .email("hong@naver.com")
                .build();
    }
}
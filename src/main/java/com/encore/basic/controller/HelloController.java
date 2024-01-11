package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("hello")
@Controller // http 통신을 굉장히 편하게 할 수 있다.
//모든 요청에 ResponseBody를 붙이고 싶다며느 RestController사용
//@RestController : 데이터만 주어야 하는 상황에 모든 부분에 ResponseBody를 붙여야하는데 그와 같은 역할을 해준다.
//데이터만 주어야하는 상황 -> 프론트가 따로있는 상황(CSR, react나 vue를 사용하고있구나 생각
// 클래스차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정
//!!!
public class HelloController {

//    @responseBody가 없고, return타입이 String이면 templates밑에 html파일 리턴(화면찾으러감)
//    data만을 return할때는 @ResponseBody를 붙인다.(csr, restapi)
    @GetMapping("string") // url 뒷부분 설정
    @ResponseBody // ResponseBody return
    public String helloString(){
        return "hello_string"; //templates밑에 "hello_string"파일을 리턴하고 파일이 없으면 에러
    }

    @GetMapping("json") // url 뒷부분 설정
    @ResponseBody // ResponseBody return
    public String helloJson(){
        return "string"; //templates밑에 "hello_string"파일을 리턴하고 파일이 없으면 에러
    }

    @GetMapping("screen")
    public String helloScreen(){
        return "screen";
    }

    //get메소드 url 데이터 받는 법
    //1. parameter 방식 : ?키=밸류
    //2. pathvariable 방식(더 restful하다 rest의 규격에 더 맞다) : localhost:8080/model/1

    @GetMapping("screen-model-param")
//    ?name=hongildong의 방식으로 호출(파라미터 호출방식)
    public String helloScreenModelParam(@RequestParam(value = "name")String inputname, Model model){
//        화면에 data를 넘기고 싶을때는 Model객체 사용
//        model에 key:value형식으로 전달 // return screen
        model.addAttribute("myData", inputname);
        return "screen";
    }

//    pathvariable방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어,
//    좀 더 RestFul API 디자인에 적합(현대적인 아키텍처에 적합)
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model){
        model.addAttribute("myData", id);
        return "screen";
    }
}
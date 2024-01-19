package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-post")
public class HelloServletRestPost extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader br = req.getReader(); // BufferedReader로 입력받은 값을 getReader로 한줄씩 직접읽어오기.
//        StringBuilder sb = new StringBuilder(); // StringBuilder 선언
//        String line = null; // String 선언 // 한줄 씩 받아야하기 때문에
//        while ((line = br.readLine()) !=null){ //null값이 아닐때 까지 실행
//            sb.append(line); // sb에 br.readLine() 추가
//        }
//        String hello = sb.toString(); // StringBuilder를 String으로 변환

        ObjectMapper mapper = new ObjectMapper();
        Hello hello1 = mapper.readValue(req.getReader(), Hello.class);
//        Hello hello1 = mapper.readValue(hello, Hello.class);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print("OK");
        System.out.println(hello1);
    }

}

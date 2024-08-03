package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}", username);
        log.info("age = {}", age);

        response.getWriter().write("ok");
    }

    @ResponseBody // view를 조회하지 않고 문자를 response에..
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {

        log.info("username = {}", memberName);
        log.info("age = {}", memberAge);

        return "ok";
    }

    @ResponseBody // view를 조회하지 않고 문자를 response에..
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody // view를 조회하지 않고 문자를 response에..
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody // view를 조회하지 않고 문자를 response에..
    @RequestMapping("/request-param-required")
    public String requestParamRequired( @RequestParam(name = "username", required = false) String username,
                                        @RequestParam(name = "age", required = false) Integer age) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody // view를 조회하지 않고 문자를 response에..
    @RequestMapping("/request-param-default")
    public String requestParamDefault( @RequestParam(name = "username", defaultValue = "guest",required = false) String username,
                                        @RequestParam(name = "age", defaultValue = "20", required = false) int age) {

        log.info("username = {}", username);
        log.info("age = {}", age);

        return "ok";
    }

    @ResponseBody // view를 조회하지 않고 문자를 response에..
    @RequestMapping("/request-param-map")
    public String requestParamMap(Map<String, Object> paramMap) {

        log.info("username = {}", paramMap.get("username"));
        log.info("age = {}", paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());
        return "ok";
    }

    // ModelAttribute 생략해도 자동으로 등록
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());
        return "ok";
    }

}

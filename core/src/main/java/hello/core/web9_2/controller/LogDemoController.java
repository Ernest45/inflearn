package hello.core.web9_2.controller;

import hello.core.common9_2.MyLogger;
import hello.core.web9_2.service.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody // view 화면이 없어서 문자로 바로 반환을 위해 ( 보통은 요청이오면 view 템플릿 거쳐 랜더링돼서 나가야하지만..)
    public String logDemo(HttpServletRequest request) { // 자바에서 제공하는 서블릿 규약인 request 정보를 받을 수 있다
        String requestURL = request.getRequestURI().toString(); //고객이 어떤 URL로 요청한 지 알 수 있다.
        myLogger.setRequestURL(requestURL); //로그 남을 때 uri까지 같이 반환하기 위해

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "Ok";

    }
}

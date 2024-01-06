package hello.core.web9_2.service;

import hello.core.common9_2.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
        private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggers;

    public void logic(String id) {

//        MyLogger myLogger = myLoggers.getObject();
        myLogger.log("service id = " + id);


    }
}

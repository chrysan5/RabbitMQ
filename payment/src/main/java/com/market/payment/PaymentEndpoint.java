package com.market.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentEndpoint {

    @Value("${spring.application.name}")
    private String appName;

    //큐를 바라보도록 리스너를 만드는 코드
    @RabbitListener(queues = "${message.queue.payment}") //이 큐를 바라봄 -> 이 큐의 메시지를 가져와 처리한다.
    public void receiveMessage(String orderId) {
        log.info("receive orderId:{}, appName : {}", orderId, appName); //컨슈머로 등록한 appName
    }
}

//rest api의 crud를 받는 동작이 아닌, 사용자로부터 요청을 받아들이는 동작을 하지 않는 케이스 같은 경우에는
//컨트롤러 이름 대신 엔드포인트 라는 이름으로 파일명을 생성하는 편이다.
package com.market.order;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${message.queue.product}")
    private String productQueue;

    @Value("${message.queue.payment}")
    private String paymentQueue;

    private final RabbitTemplate rabbitTemplate;

    //rabbitTemplate로 paymentQuieue, productQueue에 메시지를 보낸다.
    public void createOrder(String orderId) {
        rabbitTemplate.convertAndSend(productQueue, orderId); //파라미터 : 보내는 큐, 보내는 것(orderid)
        rabbitTemplate.convertAndSend(paymentQueue, orderId);
    }

}

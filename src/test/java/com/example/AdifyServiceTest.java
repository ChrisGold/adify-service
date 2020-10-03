package com.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdifyServiceTest {

    class SenderSpy implements Subscription.Sender {
        String event;
        String body;

        @Override
        public void send(String event, String body) {
            this.event = event;
            this.body = body;
        }
    }

    @Test
    @Tag("slow")
    void onFetchProductPageSendDisplay() {
        SenderSpy spy = new SenderSpy();
        AdifyService a = new AdifyService(new Adify(new HerokuGetRequest("adify")), "SESSION_ID,USER_ID,PRODUCT_ID", spy);
        a.execute();
        assertEquals("display", spy.event);
        assertEquals("SESSION_ID,USER_ID,PRODUCT_ID,TODO", spy.body);
    }

}
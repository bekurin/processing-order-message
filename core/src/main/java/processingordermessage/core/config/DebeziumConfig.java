package processingordermessage.core.config;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.debezium.inbound.DebeziumMessageProducer;
import org.springframework.integration.debezium.support.DebeziumHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class DebeziumConfig {
    @Bean
    public MessageChannel debeziumInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer debeziumMessageProducer(DebeziumEngine.Builder<ChangeEvent<byte[], byte[]>> debeziumEngineBuilder, MessageChannel debeziumInputChannel) {
        DebeziumMessageProducer debeziumMessageProducer = new DebeziumMessageProducer(debeziumEngineBuilder);
        debeziumMessageProducer.setOutputChannel(debeziumInputChannel);
        return debeziumMessageProducer;
    }

    @ServiceActivator(inputChannel = "debeziumInputChannel")
    public void handler(Message<?> message) {
        Object destination = message.getHeaders().get(DebeziumHeaders.DESTINATION);
        String key = new String((byte[]) message.getHeaders().get(DebeziumHeaders.KEY));
        String payload = new String((byte[]) message.getPayload());
        System.out.println("KEY: " + key + ", DESTINATION: " + destination + ", PAYLOAD: " + payload);
    }
}

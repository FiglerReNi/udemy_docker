package hu.docker.pageviewdao.config;

import hu.docker.pageviewdao.model.PageView;
import hu.docker.pageviewmodel.model.PageViewEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXB;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import hu.docker.pageviewdao.repositories.PageViewRepository;
@Configuration
public class RabbitConfig {

    @Bean
    Queue queue() {
        return new Queue("pageviewqueue", false);
    }

    @Bean(name = "amqpInputChannel")
    public MessageChannel amqpInputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public SimpleMessageListenerContainer inboundContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("pageviewqueue");
        return container;
    }


    @Bean
    public AmqpInboundChannelAdapter amqpInboundChannelAdapterPageView(SimpleMessageListenerContainer listenerContainer,
            @Qualifier("amqpInputChannel") MessageChannel inboundChannel) {
        AmqpInboundChannelAdapter adapter = new AmqpInboundChannelAdapter(listenerContainer);
        adapter.setOutputChannel(inboundChannel);
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "amqpInputChannel")
    public MessageHandler pageViewMessageHandler(PageViewRepository repository) {
        return message -> {

            String xmlString = (String) message.getPayload();

            InputStream is = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));

            PageViewEvent pageViewEvent =  JAXB.unmarshal(is, PageViewEvent.class);

            PageView pageView = new PageView();
            pageView.setPageUrl(pageViewEvent.getPageUrl());
            pageView.setPageViewDate(pageViewEvent.getPageViewDate());
            pageView.setCorrelationId(pageViewEvent.getCorrelationId());

            repository.save(pageView);
        };
    }
}
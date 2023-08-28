package hu.docker.pageviewservice.pageview;


import hu.docker.pageviewmodel.model.PageViewEvent;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXB;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageViewServiceImpl implements PageViewService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendPageViewEvent(PageViewEvent event) {
        Writer w = new StringWriter();
        JAXB.marshal(event, w);
        String xmlString = w.toString();

        rabbitTemplate.convertAndSend("pageviewqueue", xmlString);
        rabbitTemplate.convertAndSend("auditpageviewqueue", event.getCorrelationId());
    }
}

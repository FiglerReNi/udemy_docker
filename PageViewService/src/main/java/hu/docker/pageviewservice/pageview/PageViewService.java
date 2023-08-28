package hu.docker.pageviewservice.pageview;

import hu.docker.pageviewmodel.model.PageViewEvent;
import org.springframework.stereotype.Service;

@Service
public interface PageViewService {

    void sendPageViewEvent(PageViewEvent event);
}

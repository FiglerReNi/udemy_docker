package hu.docker.pageviewmodel.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PageViewEvent {

    private String pageUrl;
    private Date pageViewDate;
    private String correlationId;

    public String getPageUrl() {
        return pageUrl;
    }

    @XmlElement
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Date getPageViewDate() {
        return pageViewDate;
    }

    @XmlElement
    public void setPageViewDate(Date pageViewDate) {
        this.pageViewDate = pageViewDate;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    @XmlElement
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}

package hu.docker.pageviewdao.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import lombok.Getter;

@Getter
@Entity
public class PageView {
    @Id
    @GeneratedValue
    private Long id;

    private String pageUrl;
    private Date pageViewDate;
    private String correlationId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void setPageViewDate(Date pageViewDate) {
        this.pageViewDate = pageViewDate;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}

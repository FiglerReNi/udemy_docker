package hu.docker.pageviewdao.repositories;

import hu.docker.pageviewdao.model.PageView;
import org.springframework.data.repository.CrudRepository;

public interface PageViewRepository extends CrudRepository<PageView, Long> {

}

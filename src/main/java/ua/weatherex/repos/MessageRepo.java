package ua.weatherex.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.weatherex.domain.Message;

import java.util.List;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);


}

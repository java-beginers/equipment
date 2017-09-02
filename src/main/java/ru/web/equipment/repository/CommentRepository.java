package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Comment;
import ru.web.equipment.entity.Task;

import java.util.List;

/**
 * Репозиторий комментариев.
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Ищет все комментарии для указанной заявки
     * @param task Заявка
     * @return Список комментариев
     */
    List<Comment> findByTask(Task task);
}

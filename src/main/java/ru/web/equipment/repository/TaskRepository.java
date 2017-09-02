package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Task;
import ru.web.equipment.entity.User;

import java.util.List;

/**
 * Репозиторий заявок
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Получает все заявки для указанной категории.
     * @param category категория заявок
     * @return Список заявок
     */
    List<Task> findByCategory(Category category);

    /**
     * Получает все заявки для указанного владельца.
     * @param owner Пользователь, являющийся владельцем
     * @return Список заявок
     */
    List<Task> findByOwner(User owner);

    /**
     * Получает все заявки для указанного исполнителя.
     * @param executor Пользователь, являющийся исполнителем
     * @return Список заявок
     */
    List<Task> findByExecutor(User executor);
}

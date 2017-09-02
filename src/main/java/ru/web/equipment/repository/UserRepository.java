package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;

import java.util.List;

/**
 * Интерфейс для доступа к данным пользователя.
 * Вся прелесть Spring-DATA заключается в том, что достаточно только объявить интерфейс и
 * все необходимые методы будут реализованы на лету по именам, объявленным в интерфейсе.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Получает учетную запись пользователя по его логину
     *
     * @param login логин.
     * @return Учетная запись пользователя
     */
    User findOneByLogin(String login);

    /**
     * Получает всех пользователей, которые не входят в указанную роль.
     *
     * @param role Роль пользователя
     * @return Список пользователей
     */
    List<User> findAllByRole(UserRole role);
}

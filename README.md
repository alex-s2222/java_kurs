## Описание 
ФондNet - это веб-сервис, для планировки задач по уборки территорий и не только

## Используемые технологии:
Mysql, java spring, boostrap, html, javascript, css.

## Декомпозиция проекта:
- [config](https://github.com/alex-s2222/java_kurs/tree/main/src/main/java/com/main/app/config)
    - DataLoader (создаем начальных пользователей)
    - MvcConfig
    - SecurityConfig (разграничиваем доступ)
- [controllers](https://github.com/alex-s2222/java_kurs/tree/main/src/main/java/com/main/app/controllers)(Конечныe URL)
    - CreatTaskController
    - HomeController
    - PersonalController
    - RegistrationController
    - SettingController
    - TaskController
    - ViewTaskController
- [models](https://github.com/alex-s2222/java_kurs/tree/main/src/main/java/com/main/app/models)(модели баз-данных)
    - Role
    - Task
    - User
- [repo](https://github.com/alex-s2222/java_kurs/tree/main/src/main/java/com/main/app/repo)(модели баз-данных)(поиск)
    - RoleRepository
    - TaskRepository
    - UserRepository
- services


## Запуск
Создайте нового пользователя и пустую БД:

<code>mysql -u root -p -e 'CREATE DATABASE IF NOT EXISTS officerent;'
mysql -u root -p -e "CREATE USER 'root'@'localhost' IDENTIFIED BY 'root12345';"
mysql -u root -p -e "GRANT ALL ON root.* TO 'root'@'localhost';"
</code>

запуск приложение осуществляется с помощью - [Application.java](https://github.com/alex-s2222/java_kurs/tree/main/src/main/java/com/main/app)

# java_kurs
## Описание 
ФондNet - это веб-сервис, для планировки задач по уборки территорий и не только

## Используемые технологии:
Mysql, java spring, boostrap, html, javascript, css.

## Декомпозиция проекта:
- Личный кабинет.
    - User
    - Admin
- Роли
    - Admin
    - Manager
    - User

## Запуск
Создайте нового пользователя и пустую БД:
<code>
mysql -u root -p -e 'CREATE DATABASE IF NOT EXISTS officerent;'
mysql -u root -p -e "CREATE USER 'officerent'@'localhost' IDENTIFIED BY 'officerent12345';"
mysql -u root -p -e "GRANT ALL ON officerent.* TO 'officerent'@'localhost';"
    </code>

нужно  - [Application.java](https://github.com/alex-s2222/java_kurs/tree/main/src/main/java/com/main/app)

### *Отправляю "сырой проект, что успел" из-за ограничений по дэдЛайну.*
1. Для запуска сервера src/main/java/server/Server.java
2. Для запуска клиента src/main/java/client/Client.java
3. Подключение нескольких клиентов тестировал из IDEA с multi instance Run options 
#### ***Прошу обратную связь по:*** 
- уже написаным тестам (стоит ли их продолжать писать модульными с "моками" или наоборот лучше с иммитацией подключения типа "общий жирный тест"? );
- классу src/main/java/constants/ChatConstants.java ( приемлимо ли читать строку инициализации настроек здесь? И вообще ... );
- по initial.ini. (src/main/resources ) Достаточно ли просто двух строк данных в файле, или лучше чтоб был json или xml форматы с соответствующими парсерами?
- по расположению ресурсов в проекте и содержанию самой папки (src/main/resources );
- по расположению папки лог файлов, принципам логирования и наименованию лог файлов(infoLogs) 

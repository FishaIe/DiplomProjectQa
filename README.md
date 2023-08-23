# Запуск проекта и автотестов

### Запуск проекта

Для того чтобы запустить проект, нужно открыть 2 терминала:
1) в первом терминале прописываем команду `docker-compose up`, для запуска докера и эмулятора банка;
2) во втором терминале прописываем команду

для *mySQL* - `java “-Dspring.datasource.url=jdbc:mysql://localhost:3306/app” “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass” -jar artifacts/aqa-shop.jar`,

для *Postgres* - `java “-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app” “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass” -jar artifacts/aqa-shop.jar`

чтобы запустить джарник проекта с определёнными параметрами.

Все эти терминалы больше трогать и закрывать не нужно.

### Запуск тестов

Есть 2 варианты запуска тестов:
1) открыть новый терминал и прописать команду `./gradlew clean test --info`;
2) зайти в файл *src/java/ru.netology.web/test/PayTest* и нажать на зелёную кнопку на 18 строке.

Всегда предпочтителен 1-й вариант

### Получение отчета

Для того чтобы сгенерировать отчет, требуется прогнать все автотесты командой `./gradlew clean test --info`, после чего прописать команду `./gradlew allureServe`, после чего откроется веб страница с отчётом.
Если возникли какие-то проблемы, можно ещё раз прогнать автотесты и перед командой `./gradlew allureServe` прописать `./gradlew allureReport`.

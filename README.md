# Проект по автоматизации UI тестирования для сайта Cinimex

## :green_book: Содержание:

- [Использованный стек технологий](#computer-использованный-стек-технологий)
- [Варианты запуска тестов](#running_woman-варианты-запуска-тестов)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Allure-отчет](#-allure-отчет)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Интеграция с Jira](#-интеграция-с-Jira)
- [Уведомление в Telegram с использованием бота](#-уведомление-в-telegram-с-использованием-бота)
- [Видео запуска одного из тестов в Selenoid](#-видео-запуска-одного-из-тестов-в-selenoid)

## :computer: Использованный стек технологий

<p align="center">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Allure TestOps" src="images/logo/AllureTestOps.svg">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

Параметризованные автотесты написаны на <code>Java</code> с использованием <code>Gradle</code> и <code>JUnit 5</code>.
Для UI тестов используется фреймворк [Selenide](https://selenide.org/).
Тесты можно запускать локально или удаленно с помощью [Selenoid](https://aerokube.com/selenoid/).
Сборка в <code>Jenkins</code> реализована с формированием Allure-отчета и отправкой уведомления с результатами тестирования в <code>Telegram</code> после завершения прохождения тестов.

Allure-отчет включает в себя:
* названия тестов с шагами выполнения;
* скриншот страницы в браузере в момент завершения автотеста;
* Page Source;
* логи браузерной консоли;
* видео выполнения UI тестов.

## :running_man: Варианты запуска тестов

### Локальный запуск тестов
С параметрами по умолчанию
```
gradle clean test -Denv=local
```

При необходимости можно изменить параметры запуска
```
gradle clean test
${TASK}
-Dbrowser=${BROWSER}
-Dversion=${VERSION}
-Dbrowsersize=${SIZE}
```

### Запуск тестов на удаленном браузере
```
gradle clean test -Denv=server
```
При необходимости можно изменить параметры запуска

```
gradle clean test -Denv=remote
-Dbrowser=${BROWSER}
-Dversion=${VERSION}
-Dbrowsersize=${SIZE}
```

### Параметры сборки

* <code>BROWSER</code> – браузер, в котором будут выполняться тесты. По-умолчанию - <code>chrome</code>.
* <code>VERSION</code> – версия браузера, в которой будут выполняться тесты. По-умолчанию - <code>109.0</code>.
* <code>SIZE</code> – размер окна браузера, в котором будут выполняться тесты. По-умолчанию - <code>1024x768</code>.

## <img width="4%" style="vertical-align:middle" title="Jenkins" src="src/test/resources/img/JenkinsLogo.png"> Сборка в Jenkins
### <a target="_blank" href="https://jenkins.autotests.cloud/job/cinimex_jenkins/build?delay=0sec">*Jenkins job*</a>

<p align="center">
<img title="Jenkins Build" src="src/test/resources/img/JenkinsJob.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="src/test/resources/img/Allure.png"> Allure-отчет
### <a target="_blank" href="https://jenkins.autotests.cloud/job/cinimex_jenkins/16/allure/">*Overview*</a>

<p align="center">
<img title="Allure Overview" src="src/test/resources/img/Allure2.png">
</p>

### *Результат прохождения параметризованных тестов с описанием  и шагами выполнения*

<p align="center">
<img title="Test Results in Alure" src="src/test/resources/img/allurereport.png">
</p>


## <img width="4%" style="vertical-align:middle" title="Telegram" src="src/test/resources/img/AllureTestops.png"> Интеграция с Allure TestOps
### *Allure TestOps* <a target="_blank" href="https://allure.autotests.cloud/project/1839/dashboards">*Dashboard*</a>

<p align="center">  
<img title="Allure TestOps Dashboard" src="src/test/resources/img/TestopsDash.png">  
</p>  

### *Тест кейсы*

<p align="center">  
<img title="Allure TestOps Tests" src="src/test/resources/img/тк.png">  
</p>

### *Запуски*

<p align="center">  
<img title="Allure TestOps Tests" src="src/test/resources/img/launch.png">  
</p>


## <img width="4%" style="vertical-align:middle" title="Telegram" src="src/test/resources/img/AllureTestops.png"> Интеграция с Jira
### *Link* <a target="_blank" href="https://jira.autotests.cloud/browse/AUTO-1483">*Jira*</a>

<p align="center">  
<img title="Allure TestOps Dashboard" src="src/test/resources/img/Jira.png">  
</p>  


## <img width="4%" style="vertical-align:middle" title="Telegram" src="src/test/resources/img/telegram.png"> Уведомление в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически отправляет сообщение с отчетом прохождения тестов.

<p align="center">
<img width="70%" title="Telegram Notifications" src="src/test/resources/img/Bot.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Selenoid" src="src/test/resources/img/selenoid.png"> Видео запуска одного из тестов в Selenoid

Для каждого теста выполняется запись видео. Ниже представлен пример видео прохождения теста.
<p align="center">
  <img title="Selenoid Video" src="src/test/resources/img/video.gif">
</p>

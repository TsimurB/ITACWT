FROM ubuntu:20.04

# Устанавливаем зависимости
RUN apt-get update && apt-get install -y \
    wget \
    gnupg2 \
    apt-transport-https \
    ca-certificates \
    software-properties-common \
    openjdk-17-jdk \
    xvfb

# Устанавливаем Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' && \
    apt-get update && apt-get install -y google-chrome-stable && \
    apt-get clean

# Устанавливаем Maven вручную из альтернативного источника
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz -O /tmp/maven.tar.gz
RUN tar xzf /tmp/maven.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.8.5 /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/bin/mvn

# Устанавливаем переменные окружения для Maven
ENV MAVEN_HOME /opt/maven
ENV PATH ${MAVEN_HOME}/bin:${PATH}

# Копируем проект в контейнер
COPY . /usr/src/app
WORKDIR /usr/src/app

# Запуск xvfb перед выполнением тестов
CMD ["sh", "-c", "Xvfb :99 -ac & mvn clean install"]

# Устанавливаем зависимости и запускаем тесты
RUN mvn clean install
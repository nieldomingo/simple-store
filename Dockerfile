FROM clojure
MAINTAINER nieldomingo

ADD . /opt/simple-store

WORKDIR /opt/simple-store

RUN lein deps
RUN lein ring uberjar

EXPOSE 3000

CMD ["java", "-jar", "target/simple-store-0.1.0-SNAPSHOT-standalone.jar"]

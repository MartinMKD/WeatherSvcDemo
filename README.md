# WeatherSvcDemo
Weather Service endpoint demo

Pre-requisites:

1) Java 11 (e.g. brew install --cask temurin11, for MacOS install)
2) git

How to run Weather Serice demo:

1) git clone https://github.com/MartinMKD/WeatherSvcDemo.git
2) cd WeatherServiceDemo
3) ./gradlew run

The service will run bound to http://localhost:8080/weathersvc by default.

If you want to change the port it binds to or the OpenWeather API key used, edit WeatherService.properties here:

martin@macbookpro WeatherSvcDemo % pwd
/Users/martin/IdeaProjects/WeatherSvcDemo
martin@macbookpro WeatherSvcDemo % cat ./src/main/resources/WeatherService.properties
httpPort=8080
apiKey=5cf87e4d27fa3db53bbb23b354fdb5a9%
martin@macbookpro WeatherSvcDemo %

The current API key does work.

Once it is up and running, to test it, open your favorite browser and enter a URL (e.g.):

For Dallas, TX: http://localhost:8080/weathersvc?lat=32.74&lon=-96.85

For Monett, MO: http://localhost:8080/weathersvc?lat=36.93&lon=-93.93


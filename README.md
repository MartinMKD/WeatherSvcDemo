# WeatherSvcDemo
Weather Service endpoint demo

Pre-requisites:

1) Java 11 (e.g. brew install --cask temurin11, for MacOS install)
2) git

How to run Weather Serice demo:

1) git clone https://github.com/MartinMKD/WeatherSvcDemo.git
2) cd WeatherServiceDemo
3) ./gradlew run

The last command will compile/build and run the embedded HTTP service/container bound to http://localhost:8080/weathersvc by default.

If you want to change the port it binds to or the OpenWeather API key used, edit WeatherService.properties w/vi or your favorite editor here:

martin@macbookpro WeatherSvcDemo % pwd<br/>
/Users/martin/IdeaProjects/WeatherSvcDemo<br/><br/>
martin@macbookpro WeatherSvcDemo % cat ./src/main/resources/WeatherService.properties<br/>
**httpPort=8080**<br/>
**apiKey=5cf87e4d27fa3db53bbb23b354fdb5a9%**<br/>
martin@macbookpro WeatherSvcDemo %<br/>

The current API key does work.

Once it is up and running, to test it, open your favorite browser and enter a URL (e.g.):

For Dallas, TX: http://localhost:8080/weathersvc?lat=32.74&lon=-96.85<br/>
For Monett, MO: http://localhost:8080/weathersvc?lat=36.93&lon=-93.93<br/>
For San Diego, CA: http://localhost:8080/weathersvc?lat=32.74&lon=-117.11<br/>
For Yellowknife, Canada: http://localhost:8080/weathersvc?lat=62.48&lon=-114.43<br/>
For Negril, Jamaica: http://localhost:8080/weathersvc?lat=18.27&lon=-78.35<br/>

Or any other coordinates you can glean thru Google Maps or similar services, or your Android/iPhone.

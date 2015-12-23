# AndroidTest
A sample test framework template for Android

Contains a sample Functional testing framework using [Appium](http://appium.io) and Battery Statistics report generator using [Battery Historian](https://github.com/google/battery-historian).

#### Running Functional Tests

````gradle
./gradlew clean test
````

#### Running Functional Tests with Battery Statistics

````gradle
./gradlew startBatteryStats clean test stopBatteryStats
````
Reports will be generated at `'reports/battery_stats/batterystats.html'`

#### Tasks

1. `test` : Run functional tests
2. `startBatteryStats` : Stop charging on connected device and reset battery stats
3. `stopBatteryStats` : Get battery stats from device, convert it into html format and restart charging on connected device

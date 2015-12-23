# AndroidTest
A sample test framework template for Android

Includes sample functional testing framework using [Appium](http://appium.io) and battery statistics report generator using [Battery Historian](https://github.com/google/battery-historian).

#### Assumptions

1. Appium is installed and added to path variable (use `npm install appium` otherwise)
2. Android SDK is installed
3. An android emulator is running or a physical device is connected

#### To Run Functional Tests

````gradle
./gradlew clean test
````

#### To Run Functional Tests with Battery Statistics

````gradle
./gradlew startBatteryStats clean test stopBatteryStats
````
Reports will be generated at `'reports/battery_stats/batterystats.html'`

#### Available Tasks

1. `test` : Run functional tests
2. `startBatteryStats` : Stop charging on connected device and reset battery stats
3. `stopBatteryStats` : Get battery stats from device, convert it into html format and restart charging on connected device

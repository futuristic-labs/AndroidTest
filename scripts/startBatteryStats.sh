export ANDROID_HOME="/Volumes/Data/Documents/Android/sdk";
adb kill-server
adb devices
adb shell "su -c 'echo 0 > /sys/class/power_supply/battery/hv_charger_set;exit'"
adb shell dumpsys batterystats --reset

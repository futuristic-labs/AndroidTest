export ANDROID_HOME="/Volumes/Data/Documents/Android/sdk";
adb kill-server
adb devices
adb shell dumpsys batterystats > com.manusunny.materialdesign > reports/battery_stats/batterystats.txt
adb shell "su -c 'echo 1 > /sys/class/power_supply/battery/hv_charger_set;exit'"
python scripts/historian.py reports/battery_stats/batterystats.txt > reports/battery_stats/batterystats.html

package com.manusunny.framework;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AppiumServer extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(AppiumServer.class);
    private Process process;

    @Override
    public void run() {
        try {
            if (isRunning()) {
                String processId = getProcessId();
                System.out.println("Appium server is already running! Stopping it...");

                ProcessBuilder builder = new ProcessBuilder("kill", "-9", processId);
                process = builder.start();
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    System.out.println(line);
                }

                while (isRunning()) ;
                LOGGER.debug("Appium server stopped.");
            }
            System.out.println("Starting Appium server...");

            try {
                Process proc = Runtime.getRuntime().exec(new File("scripts/startServer.sh").getAbsolutePath()); //Whatever you want to execute
                BufferedReader read = new BufferedReader(new InputStreamReader(
                        proc.getInputStream()));
                try {
                    proc.waitFor();
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }

                while (read.ready()) {
                    LOGGER.debug(read.readLine());
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        } catch (IOException e) {
            LOGGER.error("Cannot start appium server!: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        try {
            new Socket("127.0.0.1", 4723).close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private String getProcessId() {

        try {
            ProcessBuilder builder = new ProcessBuilder("ps", "-e");
            Process process = builder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (line.indexOf("node") >= 0 && line.indexOf("appium") >= 0 && line.indexOf("java") < 0) {
                    return line.trim().split(" ")[0];
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot retrieve process ID of running Appium server!", e);
        }

        throw new IllegalStateException("Cannot find process of running Appium server!");
    }

    public void end() {
        if(process != null){
            try {
                process.destroy();
                process.waitFor();
                LOGGER.debug("Appium server stopped!");
            } catch (InterruptedException e) {
                LOGGER.error("Cannot stop appium server!: " + e.getMessage());
            }
        }
    }
}

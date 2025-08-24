package org.assignment4;

abstract class SmartDevice {
    String deviceName;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
    }

    public abstract void turnOn();
}

interface VoiceControl {
    void controlByVoice(String command);
}

class SmartLight extends SmartDevice implements VoiceControl {
    public SmartLight(String deviceName) {
        super(deviceName);
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on " + deviceName);
    }

    @Override
    public void controlByVoice(String command) {
        System.out.println(deviceName + " controlled by voice: '" + command + "'");
    }
}

class SmartAC extends SmartDevice implements VoiceControl {
    public SmartAC(String deviceName) {
        super(deviceName);
    }

    @Override
    public void turnOn() {
        System.out.println("Turning on " + deviceName);
    }

    @Override
    public void controlByVoice(String command) {
        System.out.println(deviceName + " controlled by voice: '" + command + "'");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartLight livingRoomLight = new SmartLight("Smart Light");
        SmartAC bedroomAC = new SmartAC("Smart AC");

        livingRoomLight.turnOn();
        livingRoomLight.controlByVoice("Turn on the lights");

        bedroomAC.turnOn();
        bedroomAC.controlByVoice("Set temperature to 22°C");
    }
}


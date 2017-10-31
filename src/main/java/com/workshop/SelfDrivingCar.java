package com.workshop;

public class SelfDrivingCar {
    private ObjectDetector objectDetector;
    private int speed = 0;
    private int destination;
    private boolean arrived = false;

    public SelfDrivingCar(int speed, int destination) {
        this.speed = speed;
        this.destination = destination;
    }

    private void accelerate() {
        this.speed += 1;
    }

    private void decelerate() {
        if (this.speed > 0) {
            this.speed -= 1;
        }
    }

    private void advanceToDestination() {
        int distance = calculateDistanceToObjectInFront();
        if (distance < 1) {
            this.stop();
            this.arrive();
        } else if (distance < this.speed / 2) {
            this.decelerate();
        } else if (this.speed < this.getSpeedLimit()){
            this.accelerate();
        }
    }

    private void arrive() {
        this.arrived = true;
    }

    private boolean hasArrived() {
        return this.arrived;
    }

    private int calculateDistanceToObjectInFront(){
        return this.objectDetector.calculateDistanceToObjectInFront(this.destination);
    }

    private int getSpeedLimit() {
        return 60;
    }

    public void stop() {
        this.speed = 0;
    }

    public void drive(int destination) {
        this.arrived = false;

        while (!this.hasArrived()){
            this.advanceToDestination();
        }
        this.stop();
    }
}

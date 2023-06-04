# Robot with Arduino

## Description
The Robot with Arduino project involves the development of a device controlled by an Arduino board. The objective is to enable the device to follow a predefined route using a search algorithm application in Java.

## Components
- Arduino Uno (or compatible)
- Two motors
- Motor driver (H-bridge)
- Display (for simulation purposes only)
- Bluetooth module HC-05
- Chassis, wheels, and battery

## Basic Functionality
1. The route mapping application is developed in Java and runs on a computer. It uses the A* search algorithm to map the desired route for the mobile robot. The application takes into account the initial position, endpoint, and obstacles along the path to generate an optimal route.
   
2. Once the route is defined, the application sends the necessary commands via Bluetooth to the Arduino, using the HC-05 module. These commands indicate the directions for the Arduino to follow and traverse the route.

3. The Arduino receives and interprets the Bluetooth commands. It activates the motors according to the instructions received, allowing movement in the specified direction.

4. The Arduino's display provides important information regarding the direction to be followed.

## Prototype
![image](https://github.com/sergiobriito/arduino-robot/assets/64617586/be05139a-48e5-4f61-ac2d-ce93a9eebeaa)
![image](https://github.com/sergiobriito/arduino-robot/assets/64617586/6f01ef6d-919c-4906-a40d-84f76b194d37)

## Application 
![image](https://github.com/sergiobriito/arduino-robot/assets/64617586/689dde7e-e8b8-4c61-80c0-aed54dd4a09a)
![image](https://github.com/sergiobriito/arduino-robot/assets/64617586/09acd12f-b0b7-40e0-a891-624cc6772fef)
![image](https://github.com/sergiobriito/arduino-robot/assets/64617586/dd90940d-587b-4fd5-aa1b-f3e74a3a1ee8)





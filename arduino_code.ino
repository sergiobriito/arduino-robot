#include <SoftwareSerial.h>
#include <Adafruit_LiquidCrystal.h>

int pinLeft_1 = 5;
int pinLeft_2 = 6;
int pinRight_1 = 3;
int pinRight_2 = 4;

Adafruit_LiquidCrystal lcd(0);

void moveForward() {
  Serial.println("SEGUIR EM FRENTE");
  lcd.clear();
  lcd.print("SEGUIR EM FRENTE");
  digitalWrite(pinLeft_1, HIGH);
  digitalWrite(pinLeft_2, LOW);
  digitalWrite(pinRight_1, HIGH);
  digitalWrite(pinRight_2, LOW);
  delay(2000);
  stop();
}

void turnLeft() {
  Serial.println("VIRAR A ESQUERDA");
  lcd.clear();
  lcd.print("VIRAR A ESQUERDA");
  digitalWrite(pinLeft_1, LOW);
  digitalWrite(pinLeft_2, HIGH);
  digitalWrite(pinRight_1, HIGH);
  digitalWrite(pinRight_2, LOW);
  delay(2000);
  stop();
}

void turnRight() {
  Serial.println("VIRAR A DIREITA");
  lcd.clear();
  lcd.print("VIRAR A DIREITA");
  digitalWrite(pinLeft_1, HIGH);
  digitalWrite(pinLeft_2, LOW);
  digitalWrite(pinRight_1, LOW);
  digitalWrite(pinRight_2, HIGH);
  delay(2000);
  stop();
}

void stop() {
  digitalWrite(pinLeft_1, LOW);
  digitalWrite(pinLeft_2, LOW);
  digitalWrite(pinRight_1, LOW);
  digitalWrite(pinRight_2, LOW);
}

void setup() {
  Serial.begin(9600);
  pinMode(pinLeft_1, OUTPUT);
  pinMode(pinLeft_2, OUTPUT);
  pinMode(pinRight_1, OUTPUT);
  pinMode(pinRight_2, OUTPUT);
  lcd.begin(16, 2);
  delay(1000);
  Serial.println("--Terminal--");
}

void loop() {
  if (Serial.available() > 0) {
    Serial.println("---Rota iniciada---");
    lcd.clear();
    lcd.print("ROTA INICIADA");
    String command = Serial.readStringUntil('\n');
    char currPos = 'N';

    for (char c : command) {
      switch (c) {
        case 'N':
          if (currPos == c) {
            moveForward();
          } else if (currPos == 'S') {
            turnRight();
            turnRight();
            moveForward();
          } else if (currPos == 'L') {
            turnLeft();
            moveForward();
          } else if (currPos == 'O') {
            turnRight();
            moveForward();
          }
          currPos = c;
          break;
        case 'S':
          if (currPos == c) {
            moveForward();
          } else if (currPos == 'N') {
            turnRight();
            turnRight();
            moveForward();
          } else if (currPos == 'L') {
            turnRight();
            moveForward();
          } else if (currPos == 'O') {
            turnLeft();
            moveForward();
          }
          currPos = c;
          break;
        case 'L':
          if (currPos == c) {
            moveForward();
          } else if (currPos == 'N') {
            turnRight();
            moveForward();
          } else if (currPos == 'S') {
            turnLeft();
            moveForward();
          } else if (currPos == 'O') {
            turnRight();
            turnRight();
            moveForward();
          }
          currPos = c;
          break;
        case 'O':
          if (currPos == c) {
            moveForward();
          } else if (currPos == 'N') {
            turnLeft();
            moveForward();
          } else if (currPos == 'S') {
            turnRight();
            moveForward();
          } else if (currPos == 'L') {
            turnRight();
            turnRight();
            moveForward();
          }
          currPos = c;
          break;
      }
      delay(2000);
    }
    lcd.clear();
    lcd.print("ROTA CONCLUIDA");
    Serial.println("---Rota concluida!---"); 
  }
}

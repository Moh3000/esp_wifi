#include <WiFi.h>
#include <WebServer.h>
#include <ESPmDNS.h>

const char* ssid = "nasser2023";
const char* password = "0458467NM";
const int ledPin = 2; // GPIO 2 (often built-in LED)

IPAddress local_IP(192, 168, 1, 50);    // your desired static IP
IPAddress gateway(192, 168, 1, 1);      // usually router IP
IPAddress subnet(255, 255, 255, 0);     // subnet mask
IPAddress primaryDNS(8, 8, 8, 8);       // optional
IPAddress secondaryDNS(8, 8, 4, 4);     // optional

WebServer server(80);

void handlePost() {
  String message = server.arg("plain");  // Read raw body
  Serial.println(" " + message);
  server.send(200, "text/plain", "Received: " + message);
  message.trim();
  if (message.equalsIgnoreCase("Relay_toggled")) {
    digitalWrite(ledPin, !digitalRead(ledPin));  // toggle LED
    Serial.println("LED toggled");
  }
}

void setup() {
  Serial.begin(115200);
  pinMode(ledPin, OUTPUT);

  // Set static IP
  if (!WiFi.config(local_IP, gateway, subnet, primaryDNS, secondaryDNS)) {
    Serial.println("STA Failed to configure");
  }

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("\nConnected. IP address: ");
  Serial.println(WiFi.localIP());

  if (!MDNS.begin("esp32")) {
    Serial.println("Error starting mDNS");
    return;
  }

  server.on("/", HTTP_POST, handlePost);
  server.begin();
  Serial.println("HTTP server started.");
}

void loop() {
  server.handleClient();
}

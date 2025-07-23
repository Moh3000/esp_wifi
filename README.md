# 📶 connect-android-app-with-esp32-using-WiFi  
# 🌐 HTTP Communication Between Android and ESP32

This project establishes **Wi-Fi communication** between an **Android app** and an **ESP32 board** using **HTTP POST requests**. The ESP32 connects to a Wi-Fi network, starts a basic HTTP server, and listens for POST data — which can be sent via an Android app, Postman, or curl.

---

## ✨ Features

- ESP32 connects to a local Wi-Fi network (Station mode).
- Starts an HTTP server on port 80.
- Receives raw HTTP POST requests.
- Logs the received data on the serial monitor.
- Easily testable using Android app or Postman.

---

## 🧰 Technologies Used

- **ESP32 (C++ / ESP-IDF)**: Uses `esp_wifi`, `esp_http_server`, and event handlers.
- **Android (Java or Kotlin)**: Sends HTTP POST requests using `HttpURLConnection` or libraries like `Retrofit`.
- **Postman / curl**: For manual testing of HTTP endpoints.

---

## 🔌 ESP32 Firmware

### ✅ Example ESP-IDF Code Snippet

#include <WiFi.h>
#include <WebServer.h>

const char* ssid = "Your_SSID";
const char* password = "Your_PASSWORD";

WebServer server(80);

void handlePost() {
  String message = server.arg("plain"); // Read POST body
  Serial.println("Received from client: " + message);
  server.send(200, "text/plain", "Data received");
}

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected! IP: " + WiFi.localIP().toString());

  server.on("/", HTTP_POST, handlePost);
  server.begin();
}

void loop() {
  server.handleClient();
}

---

## 📱 Android Client

You can use any HTTP library in Android to send a POST request.

### 📦 Using HttpURLConnection Example (Java)

URL url = new URL("http://<ESP_IP>/");
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("POST");
conn.setDoOutput(true);

OutputStream os = conn.getOutputStream();
os.write("Hello ESP32".getBytes());
os.flush();
os.close();

---

## 🧪 Testing With Postman or curl

curl -X POST http://<ESP_IP>/ -d "Test from curl"

Or use Postman to send a raw POST body to http://<ESP_IP>/.

---

## 🛠 Troubleshooting

Issue                     | Solution
------------------------- | --------------------------------------------------------
ESP32 not connecting      | Check Wi-Fi credentials and signal strength
Server not responding     | Ensure ESP IP is correct and port 80 is open
Android error             | Confirm permissions and network access on Android
non-fast-forward error    | Run git pull --allow-unrelated-histories before pushing

---

## 📄 License

This project is licensed under the MIT License. Feel free to use, modify, and distribute.

---

**Build seamless wireless communication with ESP32!** 🌍📲🚀

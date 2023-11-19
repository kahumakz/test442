package example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherClient {

    public static void main(String[] args) {
        try {
            String city = "New York"; // Replace with the desired city

            // Step 1: Create the URL for the weather service
            String apiUrl = "https://fake-weather-api.com/api/weather?city=" + city;

            // Step 2: Open a connection to the URL
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Step 3: Read the response from the service
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Step 4: Parse the XML response using JAXB
                JAXBContext jaxbContext = JAXBContext.newInstance(WeatherResponse.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                String responseXml = readResponse(connection);
                WeatherResponse weatherResponse = (WeatherResponse) unmarshaller.unmarshal(new StringReader(responseXml));

                // Step 5: Display the weather information
                displayWeather(weatherResponse);
            } else {
                System.out.println("Failed to fetch weather data. HTTP error code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readResponse(HttpURLConnection connection) throws Exception {
        // Implement the logic to read the response from the connection and return it as a String
        // This depends on the actual structure of the response from the weather service
        // For simplicity, we assume it's a well-formed XML response
        // In a real-world scenario, you might use a library like Apache HttpClient for a more robust solution
        // For brevity, this code doesn't handle errors or closing the connection properly
        // It's recommended to add proper error handling and resource management in a production environment

        // Example implementation (for educational purposes, not production-ready):
        StringBuilder response = new StringBuilder();
        while (true) {
            int c = connection.getInputStream().read();
            if (c == -1) {
                break;
            }
            response.append((char) c);
        }
        return response.toString();
    }

    private static void displayWeather(WeatherResponse weatherResponse) {
        // Implement the logic to display weather information to the user
        // This might involve updating a graphical user interface, printing to the console, etc.
        // For simplicity, we print the information to the console in this example

        System.out.println("Weather in " + weatherResponse.getCity());
        System.out.println("Temperature: " + weatherResponse.getTemperature() + "°C");
        System.out.println("Description: " + weatherResponse.getDescription());
        // Add more information as needed
    }
}

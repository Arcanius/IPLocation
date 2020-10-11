package org.example.iplocation;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HttpClient httpClient = HttpClient.newHttpClient();
        final String IP_API = "http://ip-api.com/json/";
        while (true) {
            System.out.print("Enter address or exit: ");
            String address = scanner.nextLine();
            if (address.equals("exit")) {
                break;
            }
            HttpRequest request = HttpRequest.newBuilder(URI.create(IP_API + address)).build();
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject responseText = new JSONObject(response.body());
                if (responseText.getString("status").equals("success")) {
                    String country = responseText.getString("country"), city =  responseText.getString("city");
                    System.out.println("Address: " + address + ", country: " + country + ", city: " + city);
                } else {
                    System.out.println("You entered wrong address");
                }
            } catch (Exception exception) {
                System.out.println("Connection error");
            }
        }
    }
}

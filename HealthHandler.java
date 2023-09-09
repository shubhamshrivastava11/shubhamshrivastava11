package org.openlogix;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

//import java.util.HashMap;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HealthHandler implements HttpHandler {
    
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String response= "Running";
        Gson gson;
         try {
            gson = new Gson();
            HashMap<String, String> json = new HashMap<String, String>();
            json.put("status" , "running"); 
            response = gson.toJson(json); 
        } catch (Exception e) {
            System.out.println("THROWING ERROR");
            e.printStackTrace();
            // TODO: handle exception
        }
        
        exchange.sendResponseHeaders(200, response.getBytes().length);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();   
    }
} 

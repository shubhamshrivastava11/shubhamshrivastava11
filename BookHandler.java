package org.openlogix;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BookHandler implements HttpHandler
{

    Gson gson;

    private String getbooks(BookDAO db)
    {
       HashMap<String, String> json = new HashMap<String, String>();
        for (Book book : db.searchBooks("Harry Potter")) 
        {
            json.put("name", book.name);
            json.put("id", String.valueOf(book.id));
            json.put("publisherName", book.publisherName);
            json.put("authorName", book.authorName);
        }
        
        Gson gson;
        return this.gson.toJson(json); 
    }

    private String createBook(BookDAO db , Book book) 
    {
        db.addBook(book);
        return null;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException 
    {
        System.out.println("BookHandler is triggered");
        System.out.println(exchange.getRequestMethod() + exchange.getRequestURI());
        gson = new Gson(); 
        BookDAO db = new BookDAO(); 
        String response;
        if(exchange.getRequestMethod().equals("GET"))
        {
         response = getbooks(db);
        } 
        else if(exchange.getRequestMethod().equals("POST"))
        {
          String input = IOUTils.toString(exchange.getRequestBody() , StandardCharsets.UTF_8);
          String data = gson.toJson (input);
          System.out.println(data);
          //response = data;
          Book payload = gson.fromJson(data, Book.class);
          Book book = new Book( payload.name, payload.publisherName, payload.authorName);
          response = createBook(db, book);
        }
        else
        {
            response = "Created";
    
        }

          
          //response = createBook(db);


        
        exchange.sendResponseHeaders(200, response.getBytes().length);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();  
}
}
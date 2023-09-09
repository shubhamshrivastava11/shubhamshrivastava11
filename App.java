package org.openlogix;

import java.io.IOException;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try 
        {
           Server server = new Server(8080); 
        }
        catch (IOException e)
        {
            //TODO Auto-generated catch block 
            e.printStackTrace();
        }
        
    }
}

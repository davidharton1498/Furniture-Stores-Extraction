package com.example.assignment2;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
//the ParseText class has the scope to parse the name of the product from the online shop
//within the class,we have the parseNameProduct method that it will parse the title of the product from the html page
//in order to realize parsing,I have used the jsoup library
public class ParseText {
    //the method ha 2 parameters
    //1 parameter is a variable with the value of the website link
    //2 parameter is a variable that contains the value of the id for extract the title between the tags
    //the function will return a value of type string
    public String parseNameProduct(String http, String id){

        Element link = null;
        String html="";
        try {

            URL url = new URL(http);

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            html="";
            String line;
            boolean isFound = false;
            boolean isExist=false;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                html=html+line;
                isFound = line.contains("h1");
                if(isFound){
                    //System.out.println(line);
                    isExist=true;
                }

            }
                Document document = Jsoup.parse(html);
                //Document doc = Jsoup.connect(html).get();
                //System.out.printf("Title: %s\n", doc.title());
                //a with href
                if (isExist) {
                    link = document.select(id).first();
                    //System.out.println(link.text());
                } else
                    link = null;
            //System.out.println(isExist);

        }
        catch (MalformedURLException e) {
            //System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            //System.out.println("I/O Error: " + e.getMessage());
        }
        if(link!=null&& html!="") {
            if(link.text().trim().isEmpty()) {
                return "null";
            }else {
                return link.text();
            }
        }
        else
            return "null";
    }
}
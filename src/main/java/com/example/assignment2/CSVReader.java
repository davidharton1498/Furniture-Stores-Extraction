package com.example.assignment2;
//we will import 2 packages in order to use to read data from file
import java.io.*;
import java.util.ArrayList;
//we will create a class which it will be public
//we are going to do it public because we want it to be access from anywhere
public class CSVReader {
    //we are going to create a variable called delimiter which it will be initialized with a character comma
    //the delimiter field is declared final because we want it to be a constant
    public static final String delimiter = ",";
    //we will instantiate an object of the type ArrayList<String>
    //we create an ArrayList of strings
    static ArrayList<String> titleArray = new ArrayList<>();;
    //we create a public and static method called read in order to read all data from the csv file
    //the read function has one parameter called csvFile.This parameter has the string type
    //the system executes the read function and executes the block try-catch
    //we have used a try-catch block because we want to catch all errors
    //after the system came inside the try-catch block it creates an object called file used to store the file

    public static void read(String csvFile) {
        try {
            //the class File is a default class located in the java.io.* package
            File file = new File(csvFile);
            //the system creates an object from the FileReader in order to read the file
            FileReader fr = new FileReader(file);
            //we create an object from the BufferedReader class
            BufferedReader br = new BufferedReader(fr);
            //we initialize a String variable called line with empty
            String line = "";
            //we declare an array of strings called tempArr
            String[] tempArr;
            //we initialize an int variable called id with the value 2
            //this variable represents the id of products
            //the variable is going to be 2 because the record with the id equal 1 will represent the name of records
            int id=2;
            //we initialize an int variable called k with the value 0
            int k=0;
            //we create an object from the ParseText class
            //we will use this class in order to parse the text from html page
            ParseText parseText= new ParseText();
            //we create an object from the WriteDataToExcel
            //we will use this object in order to call the methods that will perform the data storage and writing operations
            //the data will be written to the excel file
            WriteDataToExcel writeDataToExcel=new WriteDataToExcel();
            //we will read line to line from the csv file while the value of line is not equal null
            while ((line = br.readLine()) != null) {
                //we will store in the variable called tempArr every string separated through comma
                //in order to do the separate,we have used the split method
                tempArr = line.split(delimiter);
                //we go through array of strings and get every string that we will give as parameter for the parseNameProduct method
                for (String tempStr : tempArr) {
                    //we put this condition because we don't present interest the first line from the file
                    if(k!=0) {
                        //we will display on a new line every string from array
                                System.out.println(tempStr);
                        //we will display on a new line every output of the method
                                System.out.println(parseText.parseNameProduct(tempStr, "h1"));
                        //we will add to titleArray array the value of the method
                        //the value of the method represents the title of the product from online shop
                        //the method has 2 parameters
                        //1 parameter represents the extracted website from the file
                        //2 parameter represents the id by which the title of the product is searched in the html page
                                titleArray.add(parseText.parseNameProduct(tempStr,"h1"));
                        //we have put a condition for verify if the title of the product is not null
                        //if the title of the product is not null,we will call the storeData method from the WriteDataToExcel class
                        //the method has 3 parameters
                        //1 parameter represents the id of a row from the excel file
                        //2 parameter represents the link of the website
                        //3 parameter represents the title of the product
                                if(!parseText.parseNameProduct(tempStr, "h1").equals("null")) {
                                    writeDataToExcel.storeData(id, tempStr, parseText.parseNameProduct(tempStr, "h1"));
                                    id++;
                                }
                    }
                    k++;
                }
            }
            //we will call the writeData method to write the data in the file
            writeDataToExcel.writeData();
            //we call the close method to close the excel file
            br.close();

        //next we have the error exceptions
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(String title:titleArray)
            System.out.println(title);

    }
    }
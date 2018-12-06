import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

    JsonReader reader = new JsonReader(new FileReader("Companies.json"));
    JsonReader guestsJson = new JsonReader(new FileReader("Guests.json"));
    JsonReader messageJson = new JsonReader(new FileReader("Messages.json"));


        Gson gson = new Gson();
       Type typeOfList = new TypeToken<List<Company>>(){}.getType();
       List<Company> companies = gson.fromJson(reader, typeOfList);
        HashMap<Integer, Company> companyMap = new HashMap<>();
       for(Company c : companies){

           companyMap.put(c.getId(), c);


       }

       Type typeOfGuestList = new TypeToken<List<Guest>>(){}.getType();
       List<Guest> guests = gson.fromJson(guestsJson, typeOfGuestList);
       HashMap<Integer, Guest> guestMap = new HashMap<>();
       for(Guest g : guests){
           guestMap.put(g.getId(), g);


       }

       Type typeOfMessageList = new TypeToken<List<Message>>(){}.getType();
       List<Message> messages = gson.fromJson(messageJson, typeOfMessageList);
       HashMap<Integer, Message> messageMap = new HashMap<>();
       for(Message m : messages){
           messageMap.put(m.getId(), m);

       }
        int hotel_id = 0;
       Scanner scanner = new Scanner(System.in);
        int counter = 0;
       do{


         if(counter > 1){
             System.out.println("Invalid hotel id entered. \nHotel Legend:");
             for(int id : companyMap.keySet()){
                   System.out.print("(" + id + ") " + companyMap.get(id).getCompany() + ", ");
              }
              System.out.print("\n");

         }
           System.out.println("Please enter a hotel id:");
           String input = scanner.next();

           boolean isvalid = checkIntInput(input, companyMap.keySet());
           if(isvalid == true){
               hotel_id = Integer.parseInt(input);

           }
           counter++;

       }while(hotel_id <= 0);


//        do{
//            System.out.println("Please enter the hotel id number:");
//            while(!scanner.hasNextInt()){
//                System.out.println("Invalid input, please re-enter the hotel id. \nHotel legend:");
//
//                for(int id : companyMap.keySet()){
//                    System.out.print("(" + id + ") " + companyMap.get(id).getCompany() + ", ");
//                }
//                System.out.print("\n");
//                scanner.next();
//            }
//            hotel_id = scanner.nextInt();
//        }while(hotel_id <=0);
//
//
//
//
//        do{
//            System.out.println("There is no hotel on record with id of " + hotel_id + ", please re-enter the hotel id.");
//            System.out.println("Hotel Legend: ");
//            for(int id : companyMap.keySet()){
//                System.out.print("(" + id + ") " + companyMap.get(id).getCompany() + ", ");
//            }
//            System.out.print("\n");
//            hotel_id = scanner.nextInt();
//
//
//
//        }while(companyMap.get(hotel_id) == null);
        int guest_id = 0;
        counter = 0;
        do{


            if(counter > 1){
                System.out.println("Invalid guest id entered. \nGuest Listing:");
                for(int id : companyMap.keySet()){
                    System.out.print("(" + id + ") " + guestMap.get(id).getLastName() + ", " + guestMap.get(id).getFirstName());
                }
                System.out.print("\n");

            }
            System.out.println("Please enter a guest id:");
            String input = scanner.next();

            boolean isvalid = checkIntInput(input, guestMap.keySet());
            if(isvalid == true){
                guest_id = Integer.parseInt(input);

            }
            counter++;

        }while(guest_id <= 0);


//        do{
//            System.out.println("Please enter the guest id number:");
//            while(!scanner.hasNextInt()){
//                System.out.println("That's not a number. Please re-enter a valid guest id number:");
//                scanner.next();
//            }
//            guest_id = scanner.nextInt();
//            while(guestMap.get(guest_id) == null){
//                System.out.println("I have no guest with id " + guest_id + " \nGuest legend: ");
//                for(int id : guestMap.keySet()){
//                    System.out.print("(" + id + ") " + guestMap.get(id).getLastName() + ", " + guestMap.get(id).getFirstName());
//                }
//                System.out.print("\n");
//                guest_id = scanner.nextInt();
//
//            }
//
//        }while(guest_id <=0);

       System.out.println("Please enter the message id or enter a custom message with the following placeholders: **greeting**, **customer_name**, **hotel_name**, **room_number**: ");
       Scanner linescanner = new Scanner(System.in).useDelimiter("\\n");
       String input = linescanner.next();
       int message_id = 0;
       try{
           message_id = Integer.parseInt(input);
       }catch(NumberFormatException e){
           int key = messageMap.size();
           Message custommessage = new Message(key, input);
           messageMap.put(key, custommessage);
           message_id = key;

       }

        String greeting = Util.getGreeting(companyMap.get(hotel_id).getTimezone());
        Request request = new Request(greeting, companyMap.get(hotel_id), guestMap.get(guest_id), messageMap.get(message_id));
        String template = Util.processRequest(request);
        System.out.println(template);


    }
    public static boolean checkIntInput(String input, Set<Integer> keys){
        boolean result = true;
        try{
            int inp = Integer.parseInt(input);
            if(!keys.contains(inp)){
                result = false;
            }

        }catch(Exception e){
            result = false;

        }
        return result;


    }

}
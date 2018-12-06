import java.time.LocalDateTime;
import java.time.ZoneId;

public class Util {

    public static String getGreeting(String timezone){
        String greeting = "Greetings";
        ZoneId zone = ZoneId.of(timezone);
        LocalDateTime now = LocalDateTime.now(zone);

        if(now.getHour() < 12 && now.getHour() > 4){
            greeting = "Good Morning";
        }else if(now.getHour() >= 12 && now.getHour() < 16){
            greeting = "Good Afternoon";
        }else{
            greeting = "Good Evening";
        }
        return greeting;
    }
    public static String processRequest(Request request){

        String template = request.getMessage().getMessage();
        template = template.replace("**greeting**", request.getGreeting());

        template = template.replace("**customer_name**", request.getGuest().getFirstName() + " " + request.getGuest().getLastName());
        template = template.replace("**hotel_name**", request.getCompany().getCompany());
        template = template.replace("**room_number**", request.getGuest().getReservation().get("roomNumber").toString());
        return template;


    }

    public static void main(String[] args) {
        System.out.println(getGreeting("Asia/Jakarta"));
    }
}

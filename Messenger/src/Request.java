public class Request {
    private Company company;
    private Guest guest;
    private Message message;
    private String greeting;

    public Request(String greeting, Company company, Guest guest, Message message) {
     this.setGreeting(greeting);
     this.setCompany(company);
     this.setGuest(guest);
     this.setMessage(message);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
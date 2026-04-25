import java.util.*;
import java.io.*;


class HotelBooking {
    static Scanner sc = new Scanner(System.in);
    static Room[] hotelrooms = new Room[5];



    static class Room {
        int roomnumber;
        String status;
        String Customername;
        String type;
        int price;
        public Room(int roomnumber, String  status,String Customername, String type, int price){
            this.roomnumber = roomnumber;
            this.status=status;
            this.Customername= Customername;
            this.type =type;
            this.price = price;
        }
    }


    private static void login() {
        String username = "admin";
        String password = "1234";
        while (true) {
            System.out.println("===== ADMIN LOGIN =====");

            System.out.println("Enter Username : ");
            String enteredUsername = sc.next();

            System.out.println("Enter Password : ");
            String enteredPassword = sc.next();

            if (enteredUsername.equalsIgnoreCase(username) && enteredPassword.equals(password)) {
                System.out.println("Login Successful");
                break;
            } else {
                System.out.println("Invalid Credentials");
            }
        }
    }


    static void showmenu() {
        System.out.println("\n===========MENU===========");
        System.out.println("1 .Hotel DashBoard");
        System.out.println("2 .View Rooms");
        System.out.println("3 .Book Rooms");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Search Booking");
        System.out.println("6. Bulk Cancel Rooms");
        System.out.println("7. Exit");

    }

    private static void initialize() {   // initialized cause we only need this once so it should not run again
    hotelrooms[0] =new Room(101, "Available", "" , "Deluxe", 5000);
    hotelrooms[1] =new Room(102, "Available", "", "Standard", 3000);
    hotelrooms[2] =new Room(103, "Available", "", "Suite", 10000);
    hotelrooms[3] =new Room(104, "Available", "", "Standard", 3000);
    hotelrooms[4] =new Room(105, "Available", "", "Delux", 5000);

    }

    private static void dashBoard(){
        System.out.println("======HOTEL DASHBOARD=====");
        int booked = 0;
        int available = 0;
        int revenue =0;

        for(int i = 0; i< hotelrooms.length; i++){
            if (hotelrooms[i].status.equals("Booked")){
                booked++;
                revenue +=hotelrooms[i].price;
            }else{
                available++;
            }
        }
        System.out.println("Total Rooms : " + hotelrooms.length);
        System.out.println("Booked Rooms : " + booked );
        System.out.println("Available Rooms : " + available);
        System.out.println("Total Revenue : " + revenue);


    }

    private static void viewRooms() {
        System.out.println("=======ROOM STATUS======");

        for (int i = 0; i < hotelrooms.length; i++) {

            if (hotelrooms[i].status.equals("Available")) {
                System.out.println(hotelrooms[i].roomnumber + " - Available");
            } else {
                System.out.println(hotelrooms[i].roomnumber + " - Booked - " + hotelrooms[i].Customername);
            }

        }
    }

    private static void bookRooms() {
        System.out.println("======BOOK ROOM==========");

        System.out.println("Enter Customer name");
        String customername = sc.next();

        System.out.println("Enter room Number");
        int enteredroom = sc.nextInt();
        int index = enteredroom - 101;
        if (index >= hotelrooms.length || index < 0) {
            System.out.println("Invalid Room Number");
            return;
        } else if (hotelrooms[index].status.equals("Booked")) {
            System.out.println("Room not Available");

        } else {
            System.out.println("Room Booked for  "  + customername);
            hotelrooms[index].status="Booked";
            hotelrooms[index].Customername = customername; // Showing customer names parallel to the room index
            savetoFile();
            System.out.println("========BOOKING RECEIPT========");
            System.out.println("Customer Name : " + customername);
            System.out.println("Room number : " + hotelrooms[index].roomnumber );
            System.out.println("Room Type : " + hotelrooms[index].type);
            System.out.println("Price : " + hotelrooms[index].price);
            System.out.println("Status : Confirmed");



        }
    }

    private static void cancelBooking() {
        System.out.println("=====CANCELBOOKING======");
        System.out.println("Enter room Number");
        int enteredroom = sc.nextInt();
        int index = enteredroom - 101;
        if (index >= hotelrooms.length || index < 0) {
            System.out.println("Invalid Room Number");
            return;
        } else if (hotelrooms[index].status.equals("Available")) {
            System.out.println("Room is already available");
        } else {
            System.out.println("Room Cancelled Successfully");
            hotelrooms[index].status = "Available";
            hotelrooms[index].Customername="";
            savetoFile();
        }
    }
    private static void bulkCancel(){
        System.out.println("How Many Rooms to Cancel : ");
        int bulkcancel = sc.nextInt();
        for(int i =0; i<bulkcancel; i++){
            System.out.println("Enter Room Number : ");
            int enteredroom = sc.nextInt();
            int index = enteredroom - 101;
            if (index >= hotelrooms.length || index < 0) {
                System.out.println("Invalid Room Number");
            }
            else if (hotelrooms[index].status.equals("Available")) {
                System.out.println("Room is already available");
            }
            else {
                System.out.println("Room Cancelled Successfully");
                hotelrooms[index].status = "Available";
                hotelrooms[index].Customername = "";
            }
        }

        savetoFile();
    }





    private static void searchBooking(){
        System.out.println("======SEARCH BOOKING========");
        boolean found = false;
        System.out.println("Enter Name :");
        String searchname = sc.next();
        for(int i = 0; i< hotelrooms.length; i++){
            if(searchname.equals(hotelrooms[i].Customername)){
                System.out.println("Room Number :" + hotelrooms[i].roomnumber);
                System.out.println("Room Type : " + hotelrooms[i].type);
                System.out.println("Price : " + hotelrooms[i].price);;
                System.out.println("Status : " + hotelrooms[i].status);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Not Found");
        }
    }
    private static void savetoFile(){
        try{
            PrintWriter w = new PrintWriter("rooms.txt");
            for (int i=0; i<hotelrooms.length;i++){
                w.println(hotelrooms[i].roomnumber + "," +hotelrooms[i].status +"," + hotelrooms[i].Customername +"," + hotelrooms[i].type +"," + hotelrooms[i].price);
            }
            w.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static void loadFromfile(){
        try {
            Scanner reader = new Scanner(new File("rooms.txt"));
            int i = 0;
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String[] parts = line.split(",");
                int roomNo = Integer.parseInt(parts[0]);
                String status = parts[1];
                String name = parts[2];
                String type = parts[3];
                int price = Integer.parseInt(parts[4]);
                hotelrooms[i] = new Room(roomNo, status, name, type, price);
                i++;

            }
            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }




    public static void main(String[] args) {
        File file = new File("rooms.txt");
        if(file.exists()){
            loadFromfile();
        }else{
            HotelBooking.initialize();
        }

        while(true){
            showmenu();
            System.out.println("Enter Choice");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Hotel Dashboard ");
                    HotelBooking.dashBoard();
                    break;
                case 2:
                    System.out.println(" View Rooms");
                    HotelBooking.viewRooms();
                    break;
                case 3:
                    System.out.println(" Book Rooms");
                    HotelBooking.bookRooms();
                    break;

                case 4:
                    System.out.println(" Cancel Booking ");
                    HotelBooking.cancelBooking();
                    break;
                case 5:
                    System.out.println(" Search Booking ");
                    HotelBooking.searchBooking();
                    break;
                case 6:
                    System.out.println("Bulk Cancel Rooms");
                    HotelBooking.bulkCancel();
                    break;
                case 7:
                    System.out.println(" Exit");
                    return;

            }

        }
    }
}

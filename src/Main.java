import java.util.*;

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




    static void showmenu() {
        System.out.println("\n------------------------MENU----------------------");
        System.out.println("1 .View Rooms");
        System.out.println("2 .Book Rooms");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Search Booking");
        System.out.println("5. Exit");

    }

    private static void initialize() {   // initialized cause we only need this once so it should not run again
    hotelrooms[0] =new Room(101, "Available", "empty name" , "Deluxe", 5000);
    hotelrooms[1] =new Room(102, "Available", "empty name", "Standard", 3000);
    hotelrooms[2] =new Room(103, "Available", "empty name", "Suite", 10000);
    hotelrooms[3] =new Room(104, "Available", "empty name", "Standard", 3000);
    hotelrooms[4] =new Room(105, "Available", "empty name", "Delux", 5000);

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
        }
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



    public static void main(String[] args) {
        HotelBooking.initialize();

        while(true){
            showmenu();
            System.out.println("Enter Choice");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println(" View Rooms");
                    HotelBooking.viewRooms();
                    break;
                case 2:
                    System.out.println(" Book Rooms");
                    HotelBooking.bookRooms();
                    break;

                case 3:
                    System.out.println(" Cancel Booking ");
                    HotelBooking.cancelBooking();
                    break;
                case 4:
                    System.out.println(" Search Booking ");
                    HotelBooking.searchBooking();
                    break;
                case 5:
                    System.out.println(" Exit");
                    return;

            }

        }
    }
}

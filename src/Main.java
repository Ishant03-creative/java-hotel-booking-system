import java.util.*;

class HotelBooking {
    static Scanner sc = new Scanner(System.in);
    static String[] rooms = new String[5];
    static String[] names = new String[5]; // created new string for 5 rooms and 5 names to be assigned
    static String[] types = new String[5];
    static Integer[] prices = new Integer[5];



    static void showmenu() {
        System.out.println("\n------------------------MENU----------------------");
        System.out.println("1 .View Rooms");
        System.out.println("2 .Book Rooms");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Search Booking");
        System.out.println("5. Exit");

    }

    private static void initialize() {   // initialized cause we only need this once so it should not run again
        rooms[0] = "Available";
        names[0]="";
        types[0]="Delux";
        prices[0] = 5000;
        rooms[1] = "Available";
        names[1]="";
        types[1]="Standard";
        prices[1]= 3000;
        rooms[2] = "Available";
        names[2]="";
        types[2]= "Suite";
        prices[2]= 10000;
        rooms[3] = "Available";
        names[3]="";
        types[3]= "Standard";
        prices[3]= 3000;
        rooms[4] = "Available";
        names[4]="";
        types[4]= "Delux";
        prices[4]=5000;



    }

    private static void viewRooms() {
        System.out.println("=======ROOM STATUS======");

        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i].equals("Available")) {
                System.out.println((101 + i) + " - Available");
            } else {
                System.out.println((101 + i) + " - Booked - " + names[i]);
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
        if (index >= rooms.length || index < 0) {
            System.out.println("Invalid Room Number");
            return;
        } else if (rooms[index].equals("Booked")) {
            System.out.println("Room not Available");

        } else {
            System.out.println("Room Booked for  "  + customername);
            rooms[index]="Booked";
            names[index] = customername; // Showing customer names parallel to the room index

            System.out.println("========BOOKING RECEIPT========");
            System.out.println("Customer Name : " + customername);
            System.out.println("Room number : " + (index+101) );
            System.out.println("Room Type : " + types[index]);
            System.out.println("Price : " + prices[index]);
            System.out.println("Status : Confirmed");



        }
    }

    private static void cancelBooking() {
        System.out.println("=====CANCELBOOKING======");
        System.out.println("Enter room Number");
        int enteredroom = sc.nextInt();
        int index = enteredroom - 101;
        if (index >= rooms.length || index < 0) {
            System.out.println("Invalid Room Number");
            return;
        } else if (rooms[index].equals("Available")) {
            System.out.println("Room is already available");
        } else {
            System.out.println("Room Cancelled Successfully");
            rooms[index] = "Available";
        }
    }
    private static void searchBooking(){
        System.out.println("======SEARCH BOOKING========z");
        boolean found = false;
        System.out.println("Enter Name :");
        String searchname = sc.next();
        for(int i =0;i< names.length;i++){
            if(searchname.equals(names[i])){
                System.out.println("Room Number :" + (101+i));
                System.out.println("Room Type : " + types[i]);
                System.out.println("Price : " + prices[i]);;
                System.out.println("Status : " + rooms[i]);
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


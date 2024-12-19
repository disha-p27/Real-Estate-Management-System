import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; //import Scanner class from util package to take in inputs from the user
//Property is an abstract class since it contains an abstract method
abstract class Property 
{
    //encapsulation is executed by making the variables private
    private String id;
    private double price;
    private String location;
    private int size; // square feet
    private int numberOfRooms;
    private String propertyType;
    //constructor to initialise all the local variables
    public Property(String id, double price, String location, int size, int numberOfRooms, String propertyType) 
    {
        this.id = id;
        this.price = price;
        this.location = location;
        this.size = size;
        this.numberOfRooms = numberOfRooms;
        this.propertyType = propertyType;
    }
    
    public String getId() { 
        return id; 
    }
    public double getPrice() { 
        return price; 
    }
    public void setPrice(double price) { 
        this.price = price; 
    }
    public String getLocation() { 
        return location; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }
    public int getSize() { 
        return size; 
    }
    public void setSize(int size) { 
        this.size = size; 
    }
    public int getNumberOfRooms() { 
        return numberOfRooms; 
    }
    public void setNumberOfRooms(int numberOfRooms) { 
        this.numberOfRooms = numberOfRooms; 
    }
    public String getPropertyType() { 
        return propertyType; 
    }
    public double pricePerSqFt() {
        return price/size;
    }
    //abstract method inside an abstract class
    public abstract String displayDetails();
}

//class House inherits Property
class House extends Property 
{
    private boolean hasBackyard;
    public House(String id, double price, String location, int size, int numberOfRooms, boolean hasBackyard) 
    {
        super(id, price, location, size, numberOfRooms, "House");
        this.hasBackyard = hasBackyard;
    }
    //method overriding is taking place since the methods have the same name and same parameters but belong to different classes- run time polymorphism is exibited
    @Override
    public String displayDetails() {
        return "House ID: " + getId() + ", Price: Rs." + getPrice() + ", Location: " + getLocation() + ", Size: " + getSize() + " sqft, Rooms: " + getNumberOfRooms() + ", Backyard: " + (hasBackyard ? "Yes" : "No");
    }
}
//inheritance
class Apartment extends Property {
    private int floorNumber;
    public Apartment(String id, double price, String location, int size, int numberOfRooms, int floorNumber) 
    {
        super(id, price, location, size, numberOfRooms, "Apartment");
        this.floorNumber = floorNumber;
    }
    @Override
    public String displayDetails() 
    {
        return "Apartment ID: " + getId() + ", Price: Rs." + getPrice() + ", Location: " + getLocation() + ", Size: " + getSize() + " sqft, Rooms: " + getNumberOfRooms() + ", Floor: " + floorNumber;
    }
}

class PropertyComparator {
    //static function
    public static String compareProperties(Property p1, Property p2) {
        String comparisonResult = "Comparing properties:\n" + p1.displayDetails() + "\nPrice per sq ft: Rs." + p1.pricePerSqFt() + "\n" + p2.displayDetails() + "\nPrice per sq ft: Rs." + p2.pricePerSqFt() + "\n";
        if (p1.pricePerSqFt() < p2.pricePerSqFt()) {
            comparisonResult += p1.getId() + " has a better price per square foot";
        } 
        else {
            comparisonResult += p2.getId() + " has a better price per square foot";
        }
        return comparisonResult; //indicates which property is better
    }
}

public class RealEstateFinal {
    //creating an array of lists to store details of properties
    private List<Property> properties = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //creating a new instance and object of the class
        RealEstateFinal app = new RealEstateFinal();
        app.run();
    }
    
    private void run() {
        while (true) {
            System.out.println("\nReal Estate Management System");
            System.out.println("1. Add House");
            System.out.println("2. Add Apartment");
            System.out.println("3. List Properties");
            System.out.println("4. Compare Properties");
            System.out.println("5. Show Property Count");
            System.out.println("6. Delete Property");
            System.out.println("7. Update Property");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addHouse();
                case 2 -> addApartment();
                case 3 -> listProperties();
                case 4 -> compareProperties();
                case 5 -> showPropertyCount();
                case 6 -> deleteProperty();
                case 7 -> updateProperty();
                case 8 -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again");
            }
        }
    }
    //method overloading is exibited since 2 methods have same name but different parameters
    private void addHouse() {
        System.out.print("Enter House ID: ");
        String id = scanner.nextLine();
        if (getPropertyById(id) != null) {
            System.out.println("Property ID already exists");
            return;
        }
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Size (sqft): ");
        int size = scanner.nextInt();
        System.out.print("Enter Number of Rooms: ");
        int rooms = scanner.nextInt();
        System.out.print("Does it have a backyard? (yes/no): ");
        boolean hasBackyard = scanner.next().equalsIgnoreCase("yes");
        addHouse(id, price, location, size, rooms, hasBackyard);
    }

    private void addHouse(String id, double price, String location, int size, int rooms, boolean hasBackyard) {
        properties.add(new House(id, price, location, size, rooms, hasBackyard));
        System.out.println("House added successfully.");
    }

    private void addApartment() {
        System.out.print("Enter Apartment ID: ");
        String id = scanner.nextLine();
        if (getPropertyById(id) != null) {
            System.out.println("Property ID already exists!");
            return;
        }
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Size (sqft): ");
        int size = scanner.nextInt();
        System.out.print("Enter Number of Rooms: ");
        int rooms = scanner.nextInt();
        System.out.print("Enter Floor Number: ");
        int floorNumber = scanner.nextInt();

        addApartment(id, price, location, size, rooms, floorNumber);
    }

    private void addApartment(String id, double price, String location, int size, int rooms, int floorNumber) {
        properties.add(new Apartment(id, price, location, size, rooms, floorNumber));
        System.out.println("Apartment added successfully.");
    }

    private void listProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties to list.");
            return;
        }
        System.out.println("Listing all properties:");
        for (Property property : properties) {
            System.out.println(property.displayDetails());
            System.out.println("Price per square foot: Rs." + property.pricePerSqFt());
        }
    }

    private void compareProperties() {
        System.out.print("Enter the ID of the first property: ");
        String id1 = scanner.nextLine();
        System.out.print("Enter the ID of the second property: ");
        String id2 = scanner.nextLine();
        Property p1 = getPropertyById(id1);
        Property p2 = getPropertyById(id2);

        if (p1 != null && p2 != null) {
            System.out.println(PropertyComparator.compareProperties(p1, p2));
        } else {
            System.out.println("One or both property IDs not found.");
        }
    }

    private void showPropertyCount() {
        System.out.println("Total properties: " + properties.size());
    }

    private void deleteProperty() {
        System.out.print("Enter the ID of the property to delete: ");
        String id = scanner.nextLine();
        Property property = getPropertyById(id);

        if (property != null) {
            properties.remove(property);
            System.out.println("Property with ID " + id + " has been deleted successfully.");
        } else {
            System.out.println("Property ID not found.");
        }
    }

    private void updateProperty() {
        System.out.print("Enter the ID of the property to update: ");
        String id = scanner.nextLine();
        Property property = getPropertyById(id);

        if (property != null) {
            System.out.print("Enter new Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter new Location: ");
            scanner.nextLine(); // Consume newline
            String location = scanner.nextLine();
            System.out.print("Enter new Size (sqft): ");
            int size = scanner.nextInt();
            System.out.print("Enter new Number of Rooms: ");
            int rooms = scanner.nextInt();

            property.setPrice(price);
            property.setLocation(location);
            property.setSize(size);
            property.setNumberOfRooms(rooms);

            System.out.println("Property updated successfully.");
        } else {
            System.out.println("Property ID not found.");
        }
    }

    private Property getPropertyById(String id) {
        for (Property property : properties) {
            if (property.getId().equals(id)) {
                return property;
            }
        }
        return null;
    }
}

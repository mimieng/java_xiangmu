package pojo;

import java.time.LocalDate;

public class Manage {
    private int id;                   // Record ID
    private String name;              // Equipment name
    private String category;          // Equipment category
    private String borrowerName;      // Borrower's name
    private String borrowerStudentId; // Borrower's student ID
    private LocalDate borrowDate;     // Borrow date
    private LocalDate returnDate;     // Return date
    private int quantity;             // Quantity of borrowed equipment
    private String status;            // Status (borrowed, returned)

    // Default constructor
    public Manage() {}

    // Parameterized constructor
    public Manage(int id, String name, String category, String borrowerName, String borrowerStudentId,
                  LocalDate borrowDate, LocalDate returnDate, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.borrowerName = borrowerName;
        this.borrowerStudentId = borrowerStudentId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerStudentId() {
        return borrowerStudentId;
    }

    public void setBorrowerStudentId(String borrowerStudentId) {
        this.borrowerStudentId = borrowerStudentId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ToString method for easy logging and debugging
    @Override
    public String toString() {
        return "Manage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerStudentId='" + borrowerStudentId + '\'' +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}

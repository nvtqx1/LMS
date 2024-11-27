package BTL.DatabaseClasses;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowedBook {
    private Books book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private static LocalDate currentDate;
    private double fineAmountForThisBook;

    // Constructor mặc định
    public BorrowedBook() {
        setCurrentDate(LocalDate.now());
    }

    // Constructor với tham số sách
    public BorrowedBook(Books book) {
        setCurrentDate(LocalDate.now());
        this.setBook(book);
        this.setFineAmount();
    }

    // Các phương thức getter và setter

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        BorrowedBook.currentDate = currentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmountForThisBook;
    }

    public void setFineAmount() {
        // Tính phí phạt là 50% giá mượn sách
        fineAmountForThisBook = this.book.getPriceToBorrow() * 0.5;
    }

    // Phương thức tính số ngày còn lại để trả sách
    public long calculateDaysLeft() {
        if (currentDate.isBefore(dueDate)) {
            // Sử dụng ChronoUnit để tính số ngày chính xác hơn
            return ChronoUnit.DAYS.between(currentDate, dueDate);
        }
        return 0;
    }

    // Phương thức kiểm tra sách có quá hạn không
    private boolean isOverDue() {
        // Cải thiện điều kiện kiểm tra quá hạn
        return returnDate != null && returnDate.isAfter(dueDate);
    }

    // Phương thức tính số tiền phạt
    public double calculateFineAmount() {
        if (isOverDue()) {
            // Tính phí phạt dựa trên số ngày quá hạn
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            return fineAmountForThisBook * daysOverdue;
        }
        return 0;
    }
}
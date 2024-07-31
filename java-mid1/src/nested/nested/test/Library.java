package nested.nested.test;

public class Library {

    private Book[] books;
    private int bookCount;

    public Library(int count) {
        books = new Book[count];
        bookCount = 0;
    }

    public void addBook(String title, String author) {
        if (books.length > bookCount) {
            Book book = new Book(title, author);
            books[bookCount++] = book;
        } else {
            System.out.println("도서관 저장 공간이 부족합니다");
        }
    }

    public void showBooks(){
        System.out.println("== 책 목록 ==");
//        for (Book book : books) {
//            System.out.println("book.title + book.author = " + book.title + book.author);
//        }
        for (int i = 0; i < bookCount; i++) {
            System.out.println("books[i].title, books[i].author = " + books[i].title + books[i].author);
        }
    }

    private static class Book{
        private String title;
        private String author;
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
    }

}

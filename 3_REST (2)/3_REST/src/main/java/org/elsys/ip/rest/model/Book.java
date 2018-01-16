package org.elsys.ip.rest.model;


public class Book  {
    private int id;
    private String name;
    private String genre;
    private String author;
    private String summary;
    private String publishers;
    private double price;
    private int pages;
    private int year;
    private boolean isHardCover;
    private String language;


    public Book(int id, String name, String genre, String author, String summary, String publishers,
                double price, int pages, int year, boolean isHardCover,String language) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.summary = summary;
        this.publishers = publishers;
        this.price = price;
        this.pages = pages;
        this.year = year;
        this.isHardCover = isHardCover;
        this.language = language;
    }


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


    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublishers() {
        return publishers;
    }
    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public boolean isHardCover() {
        return isHardCover;
    }
    public void setHardCover(boolean hardCover) {
        isHardCover = hardCover;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}

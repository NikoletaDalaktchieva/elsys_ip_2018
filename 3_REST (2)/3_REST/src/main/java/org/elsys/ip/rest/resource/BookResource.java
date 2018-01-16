package org.elsys.ip.rest.resource;

import javax.ws.rs.Consumes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.elsys.ip.rest.model.Book;
import org.elsys.ip.rest.service.BookService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Path("books")
public class BookResource {

    private BookService bookService = new BookService();

    @GET
    @Produces("application/json")
    public List<Book> getCountryList(@Context UriInfo uriInfo) {

        MultivaluedMap<String, String> query = uriInfo.getQueryParameters();

        if(query.size() == 0) return getAllBooks();
        else if(query.get("id") != null) return getByIds(query);
        else return getByInfo(query);

    }


    private List<Book> getAllBooks(){ return bookService.getBookList(); }

    private List<Book> getByIds(MultivaluedMap<String, String> query){
        List<Book> bookList = new ArrayList<>();
        for(int i =0; i < query.get("id").size(); i++){
            bookList.add(bookService.getBookById(Integer.valueOf(query.get("id").get(i))));
        }
        return bookList;
    }

    private List<Book> getByInfo(MultivaluedMap<String, String> query) {

        //return getAllBooks();
        HashSet<Book> bookList = new HashSet<>();

        for(Book b : bookService.getBookList()){
            if((query.get("name") != null) && (query.get("genre") != null) && (query.get("summary") != null)
                    && (query.get("publishers") != null) && (query.get("price") != null) &&
                    (query.get("pages") != null) && (query.get("year") != null) && (query.get("language") != null)){
                if(query.get("name").get(0).equals(b.getName()) && (query.get("genre").get(0).equals(b.getGenre()))
                        && query.get("summary").get(0).equals(b.getSummary()) && query.get("publishers").get(0).equals(b.getPublishers())
                        && Double.valueOf(query.get("price").get(0)).equals(b.getPrice()) && Integer.valueOf(query.get("pages").get(0)).equals(b.getPages())
                        && Integer.valueOf(query.get("year").get(0)).equals(b.getYear()) && query.get("language").get(0).equals(b.getLanguage())){
                    bookList.add(b);
                }
            }
            if(query.get("name") != null){
                for(int i = 0; i < query.get("name").size(); i ++){
                    if(query.get("name").get(i).equals(b.getName())) bookList.add(b);
                }
            }else if(query.get("genre") != null){
                for(int i = 0; i < query.get("genre").size(); i ++){
                    if(query.get("genre").get(i).equals(b.getGenre())) bookList.add(b);
                }
            }else if(query.get("summary") != null){
                for(int i = 0; i < query.get("summary").size(); i ++){
                    if(query.get("summary").get(i).equals(b.getSummary())) bookList.add(b);
                }
            }else if(query.get("publishers") != null){
                for(int i = 0; i < query.get("publishers").size(); i ++){
                    if(query.get("publishers").get(i).equals(b.getPublishers())) bookList.add(b);
                }
            }else if(query.get("price") != null){
                for(int i = 0; i < query.get("price").size(); i ++){
                    if(Double.valueOf(query.get("price").get(0)).equals(b.getPrice())) bookList.add(b);
                }
            }else if(query.get("pages") != null){
                for(int i = 0; i < query.get("pages").size(); i ++){
                    if(Integer.valueOf(query.get("pages").get(0)).equals(b.getPages())) bookList.add(b);
                }
            }else if(query.get("year") != null){
                for(int i = 0; i < query.get("year").size(); i ++){
                    if(Integer.valueOf(query.get("year").get(0)).equals(b.getYear())) bookList.add(b);
                }
            }else if(query.get("language") != null){
                for(int i = 0; i < query.get("language").size(); i ++){
                    if(query.get("language").get(i).equals(b.getPublishers())) bookList.add(b);
                }
            }else if(query.get("hardCover") != null){
                for(int i = 0; i < query.get("hardCover").size(); i ++){
                    if(Boolean.valueOf(query.get("hardCover").get(0)).equals(b.isHardCover())) bookList.add(b);
                }
            }

        }

        return new ArrayList<>(bookList);
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Book getBook(@PathParam("id") Integer id) {
        return bookService.getBookById(id);
    }



    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Book saveBook(Book book) {
        return bookService.saveBook(book);
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Book updateBook(@PathParam("id") Integer id, Book book) {
        return bookService.updateBook(id, book);
    }


    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") Integer id) {
        bookService.deleteBook(id);
    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public List<Book> uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                                 @FormDataParam("file") FormDataContentDisposition fileDetail) {

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(uploadedInputStream));
        List<Book> bookList = new ArrayList<>();

        String line;
        try {
            while((line = fileReader.readLine()) != null) {
                String[] info = line.split(", ");
                Book book = new Book(Integer.valueOf(info[0]), info[1], info[2], info[3], info[4], info[5],
                        Double.valueOf(info[6]), Integer.valueOf(info[7]), Integer.valueOf(info[8]),
                        Boolean.valueOf(info[9]), info[10]);
                bookService.saveBook(book);
                bookList.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }


    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile() {

        File file = new File("/Users/Zori/Desktop/csvFile.csv");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);


            for(Book b : bookService.getBookList()) {
                String book = String.valueOf(b.getId()) + ", " + b.getName() + ", " + b.getGenre() + ", " +
                        b.getAuthor() + ", " + b.getSummary() + ", " + b.getPublishers() + ", " +
                        String.valueOf(b.getPrice()) + ", " + String.valueOf(b.getPages()) + ", " +
                        String.valueOf(b.getYear()) + ", " + String.valueOf(b.isHardCover()) + ", " + b.getLanguage() + "\r\n";
                fw.write(book);

            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.ok(file).header("Books", "attachment; filename=" + file.getName()).build();
    }

    @POST
    @Path("/multiple")
    @Consumes("application/json")
    @Produces("application/json")
    public List<Book> multiple(String json) {
        List<Book> bookList = bookService.getBookList();
        JsonArray books = (JsonArray) new JsonParser().parse(json);

        for(int i = 0; i < books.size(); i++){
            JsonObject book = books.get(i).getAsJsonObject();
            System.out.println("Here");
            Book b = new Book(book.get("id").getAsInt(), book.get("name").getAsString(), book.get("genre").getAsString(),
                    book.get("author").getAsString(), book.get("summary").getAsString(), book.get("publishers").getAsString(),
                    book.get("price").getAsDouble(), book.get("pages").getAsInt(), book.get("year").getAsInt(),
                    book.get("isHardCover").getAsBoolean(), book.get("language").getAsString());

            bookList.add(b);
        }

        return bookList;
    }

}

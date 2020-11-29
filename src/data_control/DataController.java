package Data_control;

import Theatre_elements.Movie;
import Theatre_elements.Showing;
import Theatre_elements.Theatre;
import User.User;

import java.util.ArrayList;

public class DataController {

    private DatabaseManager dbM;
    private MovieCatalogue movieCatalogue;
    private TheatreCatalogue theatreCatalogue;
    private ArrayList<Showing> showings;
    TicketList ticketList;
    private ArrayList<User> users;
    public TicketManagement ticketManager;

    private static DataController dataController;

    private DataController(){
        dbM = new DatabaseManager("localhost\\SQLEXPRESS02:60490");
        ticketList = new TicketList(dbM.tickets);
        users = dbM.users;
        movieCatalogue = new MovieCatalogue(dbM.movies);
        theatreCatalogue = new TheatreCatalogue(dbM.theatres);
        ticketManager = new TicketManagement(this);
        showings = dbM.showings;
    }

    public ArrayList<Movie> getMovies(){
        return movieCatalogue.getMovies();
    }
    public ArrayList<Theatre> getTheatres(){
        return theatreCatalogue.getTheatres();
    }

    public ArrayList<Showing> getShowings(String movieName, String theatreName){
        ArrayList<Showing> goodShowings = new ArrayList<>();
        for(Showing s: showings){
            if(s.getMovie().getName().equals(movieName) && s.getTheatre().getName().equals(theatreName)){
                goodShowings.add(s);
            }
        }
        return goodShowings;
    }

    public User loginUser(String username, String password){
        for(User u:users){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return u;
            }
        }
        return null;
    }

    public void registerUser(String name, String username, String password, String email, float credit){
        //generate new account num
        //users.add(new User(name, username, password, email, credit));
    }

    public static DataController dataController(){
        if(dataController == null){
            dataController = new DataController();
        }
        return dataController;
    }

    public static void main(String[] args){
        dataController().getMovies();
        dataController().getTheatres();
        dataController().getShowings("Kung Fu Hustle", "Theatre");
    }

}
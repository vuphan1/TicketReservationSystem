package data_control;

import theatre_elements.Movie;

import java.util.ArrayList;

public class MovieCatalogue {
    private ArrayList<Movie> movies;

    public MovieCatalogue(ArrayList<Movie> movies){
        this.movies = movies;
    }

    public Movie searchMovie(String search){
        for(Movie m:movies){
            if(m.getName().toLowerCase().equals(search.toLowerCase())){
                return m;
            }
        }
        return null;
    }
}
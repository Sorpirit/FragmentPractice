package com.practice.fragmentpractice;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader extends AsyncTask<Integer,Void,ArrayList<FilmStats>> {


    private static final String url = "https://www.imdb.com/movies-coming-soon/";

    public LoaderListener getLoaderListener() {
        return loaderListener;
    }

    public void setLoaderListener(LoaderListener loaderListener) {
        this.loaderListener = loaderListener;
    }

    DataLoader(ArrayList<FilmStats> list){
        this.list = list;
    }

    private LoaderListener loaderListener;
    private ArrayList<FilmStats> list;

    @Override
    protected ArrayList<FilmStats> doInBackground(Integer... integers) {

        Document document = null;
        try {
            document = Jsoup.connect(url + "2019-07").get();


            Elements posters = document.getElementsByClass("poster shadowed");
            Elements info = document.getElementsByClass("overview-top");
            Elements cert_runtime_genre = document.getElementsByClass("cert-runtime-genre");

            if(posters.size() == info.size()){
                for(int i=0;i < posters.size()-1;i++){

                    String name = "";
                    String posterUrl = "";
                    String runTime = "";
                    String ageRating = "";


                    name = info.get(i).getElementsByTag("h4").first().children().first().attr("title");

                    posterUrl = posters.get(i).attr("src");

                    List<String> tempGenres = cert_runtime_genre.get(i).getElementsByTag("span").eachText();
                    List<Genres> genres = new ArrayList<>();
                    for(String tempGenre:tempGenres){
                        if(!tempGenre.trim().equals("|")){
                            if(tempGenre.trim().equals("Sci-Fi")){
                                genres.add(Genres.SciFi);
                                continue;
                            }else if(tempGenre.trim().equals("Short film")){
                                genres.add(Genres.ShortFilm);
                                continue;
                            }

                            genres.add(Genres.valueOf(tempGenre));
                        }
                    }

                    runTime = cert_runtime_genre.get(i).getElementsByTag("time").text().trim();


                    Elements rating = cert_runtime_genre.get(i).getElementsByAttributeValue("class","certRating");
                    Elements rating2 = cert_runtime_genre.get(i).getElementsByClass("absmiddle certimage");
                    if(rating.size() != 0){
                        ageRating = rating.first().text();
                    } else if(rating2.size() != 0){
                        ageRating = rating2.first().attr("title");
                    }



                    list.add(new FilmStats(name,genres.toArray(new Genres[0]),runTime,-1,posterUrl,ageRating));

                }
            }


        } catch (IOException e) {
        }

        return list;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<FilmStats> filmStats) {
        super.onPostExecute(filmStats);
        if(loaderListener!=null) loaderListener.onResult();
    }


}

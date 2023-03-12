package com.example.mybestyoutube.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mybestyoutube.Db.YoutubeDbHelper;
import com.example.mybestyoutube.Model.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class YoutubeDao extends Dao{

    public YoutubeDao(Context context) {

        super(new YoutubeDbHelper(context));
    }

    public YoutubeVideo find(Long id){

        //cree
        YoutubeVideo youtubeVideo = null;

        //ouvre la base de donnée
        open();

        //recupere un cursor apres avoir exécuter la requette select
        Cursor cursor = db.rawQuery("Select * from " + YoutubeDbHelper.TABLE_NAME +
                " Where " + YoutubeDbHelper.KEY + " = ? ", new String[] {String.valueOf(id)});


        //positionne le cursor sur le premiere enregistrement
        if (cursor != null && cursor.moveToFirst()){


            youtubeVideo = new YoutubeVideo();
            youtubeVideo.setId(cursor.getLong(YoutubeDbHelper.KEY_COLUMN_INDEX));
            youtubeVideo.setTitre(cursor.getString(YoutubeDbHelper.TITRE_COLUMN_INDEX));
            youtubeVideo.setDescription(cursor.getString(YoutubeDbHelper.DESCRIPTION_COLUMN_INDEX));
            youtubeVideo.setUrl(cursor.getString(YoutubeDbHelper.URL_COLUMN_INDEX));
            youtubeVideo.setCategorie(cursor.getString(YoutubeDbHelper.CATEGORIE_COLUMN_INDEX));

        }

        //ferme la base de donnée
        close();

        return youtubeVideo;
    }


    public List<YoutubeVideo> list(){

        List<YoutubeVideo> youtubeVideos = new ArrayList<>();

        //ouvre la base
        open();

        //recupere le cursor apres avoir executer le requette select
        Cursor cursor = db.rawQuery(" select * from " + YoutubeDbHelper.TABLE_NAME , null);

        //position le cursor sur le premier enregistrement
        if (cursor != null && cursor.moveToFirst()){

            //boucle tant que y'a des enregistrement
            while (!cursor.isAfterLast()){

                YoutubeVideo  youtubeVideo = new YoutubeVideo();
                youtubeVideo.setId(cursor.getLong(YoutubeDbHelper.KEY_COLUMN_INDEX));
                youtubeVideo.setTitre(cursor.getString(YoutubeDbHelper.TITRE_COLUMN_INDEX));
                youtubeVideo.setDescription(cursor.getString(YoutubeDbHelper.DESCRIPTION_COLUMN_INDEX));
                youtubeVideo.setUrl(cursor.getString(YoutubeDbHelper.URL_COLUMN_INDEX));
                youtubeVideo.setCategorie(cursor.getString(YoutubeDbHelper.CATEGORIE_COLUMN_INDEX));

                //ajout dans la liste
                youtubeVideos.add(youtubeVideo);

                //passe au curser suivant
                cursor.moveToNext();

            }
        }

        //ferme le db
        close();

        return  youtubeVideos;

    }

    public void add(YoutubeVideo youtubeVideo){

        //ourvre db
        open();

        //cree l'objet ContentValues qui permet de renseigner les valeurs a inserer
        ContentValues values = new ContentValues();
        values.put(YoutubeDbHelper.TITRE, youtubeVideo.getTitre());
        values.put(YoutubeDbHelper.DESCRIPTION, youtubeVideo.getDescription());
        values.put(YoutubeDbHelper.URL, youtubeVideo.getUrl());
        values.put(YoutubeDbHelper.CATEGORIE, youtubeVideo.getCategorie());


        //recupere l'id genere par la base de données
        Long id = db.insert(YoutubeDbHelper.TABLE_NAME, null, values);

        //met a jour l'id géneré
        youtubeVideo.setId(id);

        //ferme la bd
        close();

    }


    public int update(YoutubeVideo youtubeVideo){

        //ourvre db
        open();

        //cree l'objet ContentValues qui permet de renseigner les valeurs a mettre a jours
        ContentValues values = new ContentValues();
        values.put(YoutubeDbHelper.TITRE, youtubeVideo.getTitre());
        values.put(YoutubeDbHelper.DESCRIPTION, youtubeVideo.getDescription());
        values.put(YoutubeDbHelper.URL, youtubeVideo.getUrl());
        values.put(YoutubeDbHelper.CATEGORIE, youtubeVideo.getCategorie());


        //execute la requtte update avec la clause where sur id
        int dbResult = db.update(YoutubeDbHelper.TABLE_NAME, values,YoutubeDbHelper.KEY + " = ?", new String[]{youtubeVideo.getId().toString()});

        //ferme
        close();

        return dbResult;
    }

    public int delete(YoutubeVideo youtubeVideo){

        //ouvre bdd
        open();

        //execute la requtte suprimer avec la clause where sur id
        int dbResult = db.delete(YoutubeDbHelper.TABLE_NAME,YoutubeDbHelper.KEY + " = ?", new String[]{youtubeVideo.getId().toString()});

        //ferme
        close();

        return dbResult;
    }
}

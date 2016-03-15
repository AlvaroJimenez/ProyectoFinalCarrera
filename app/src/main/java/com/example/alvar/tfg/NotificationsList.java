package com.example.alvar.tfg;

import android.text.Html;

import java.util.ArrayList;

/**
 * Created by alvar on 13/03/2016.
 */
public class NotificationsList {

    private ArrayList<String> ListNotification = new ArrayList<String>();

    public NotificationsList()
    {

    }
    public void addNotification(String not)
    {
        ListNotification.add(not);

    }
    public int getSize()
    {
        return ListNotification.size();
    }

    public String[] getNombres()
    {
        String[] values= new String[ListNotification.size()];

        for(int i=0;i<ListNotification.size();++i) {
           values[i] = ListNotification.get(i);

        }
        return  values;
    }

}

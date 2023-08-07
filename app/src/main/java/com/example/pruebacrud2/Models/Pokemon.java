package com.example.pruebacrud2.Models;

import java.util.List;

public class Pokemon {


    private int number;

    private String name;

    private String url;

    private List<String> types;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

        public int getNumber() {

         String[] urlPartes = url.split("/");


            return Integer.parseInt(urlPartes[urlPartes.length-1]);
        }

    public void setNumber(int number) {
        this.number = number;
    }


}




package com.example.photoapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhotoData {
    /*public static ArrayList<Photo> generatePhotoData(){
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(0, "https://product.hstatic.net/200000325223/product/4777_8a54f00cc40cb99e68c691b9829bf233_022fea4372ab447e98d66ba75702389e_dc05f8afc4d24af18cb2b8d747939422_master.jpg", "Pineapple", "Đây là quả dứa"));
        photos.add(new Photo(1, "https://cdn.britannica.com/22/187222-050-07B17FB6/apples-on-a-tree-branch.jpg", "Apple", "Đây là quả táo"));
        photos.add(new Photo(2, "https://cdn.britannica.com/24/174524-050-A851D3F2/Oranges.jpg", "Orange", "Đây là quả cam"));
        photos.add(new Photo(3, "https://cdn.tgdd.vn/2022/05/CookDish/cherry-la-qua-gi-co-may-loai-phan-biet-cherry-my-voi-cherry-avt-1200x676.jpg", "Cherry", "Đây là quả chery"));
        photos.add(new Photo(4, "https://healthjade.com/wp-content/uploads/2017/10/pear.jpg", "Pear", "Đây là quả lê"));
        photos.add(new Photo(5, "", "Lemon", "Đây là quả chanh"));
        photos.add(new Photo(6, "", "Water Melon", "Đây là quả dưa hấu"));
        photos.add(new Photo(7, "", "Durian", "Đây là sầu riêng"));
        photos.add(new Photo(8, "", "Coconut", "Đây là quả dừa"));

        return photos;
    }*/

    /*public static Photo getPhotoFromId(int id){
        ArrayList<Photo> phs = generatePhotoData();
        for (int i = 0; i < phs.size(); i++){
            if (phs.get(i).getId() == id){
                return phs.get(i);
            }
        }
        return null;
    }*/

    private static Context context;

    public static void init(Context context) {
        PhotoData.context = context;
    }

    public static ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                photos.add(new Photo(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photos;
    }

    private static String loadJSONFromAsset(String fileName) throws IOException {
        String jsonString;
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonString;
    }
    public static Photo getPhotoById(int id) {
        Photo photo = null;

        try {
            String jsonString = loadJSONFromAsset("Photodata.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int photoId = jsonObject.getInt("id");

                if (photoId == id) {
                    String source = jsonObject.getString("source_photo");
                    String title = jsonObject.getString("title");
                    String description = jsonObject.getString("description");
                    photo = new Photo(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photo;
    }
}

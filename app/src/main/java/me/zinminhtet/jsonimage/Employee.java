package me.zinminhtet.jsonimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Employee extends HashMap<String, String> {

    final static String baseURL = "http://172.27.240.226:8090/workhere/Service.svc/";

    public Employee(String name, String id, String address, String salary) {
        put("Name", name);
        put("Id", id);
        put("Salary", salary);
        put("Address", address);
    }

    public static List<String> list() {
        List<String> list = new ArrayList<>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "Employee");
        try {
            for (int i = 0; i < a.length(); i++)
                list.add(a.getString(i));
        } catch (Exception e) {
            Log.e("Employee.list()", "JSONArray error");
        }
        return (list);
    }

    public static Employee getEmp(String eid) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "employee/" + eid);
        try {
            return new Employee(b.getString("Name"), b.getString("Id"),
                    b.getString("Address"), b.getString("Salary"));
        } catch (Exception e) {
            Log.e("Employee.getEmp()", "JSONArray error");
        }
        return (null);
    }


    final static String imageURL = "http://172.27.240.226:8090/workhere/photo";

    public static Bitmap getPhoto(boolean thumbnail, String id) {
        try {
            URL url = (thumbnail ? new URL(String.format("%s/%s-s.jpg", imageURL, id)) :
                    new URL(String.format("%s/%s.jpg", imageURL, id)));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Employee.getPhoto()", "Bitmap error");
        }
        return (null);
    }
}

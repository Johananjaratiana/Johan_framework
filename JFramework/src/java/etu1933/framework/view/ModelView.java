package etu1933.framework.view;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ModelView<T> {
    String view;
    HashMap<String, T> data;

    public HashMap<String, T> getData() {
        return data;
    }

    public void setData(HashMap<String, T> data) {
        this.data = data;
    }

    public String getView() {
        return view;
    }

    public ModelView<T> setView(String view) {
        this.view = view;
        return this;
    }

    public ModelView() {
        this.setData(new HashMap<>());
    }

    public void additem(String key, T value){
        this.getData().put(key,value);
    }

    public void sendData(HttpServletRequest request, HttpServletResponse response){
        this.getData().forEach((key, value) -> {
            request.setAttribute(key, value);
        });
    }
}

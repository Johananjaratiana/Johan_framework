package etu1933.framework.view;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelView
{
    String view;
    HashMap<String, Object> data;
    HashMap<String, Object> session;

    boolean invalidateSession = false;

    List<String> removeSession = new ArrayList<>();
    boolean isJson = false;

    public boolean isJson() {  return isJson;}

    public void setJson(boolean json) {isJson = json;}

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public String getView() {
        return view;
    }

    public ModelView setView(String view)
    {
        this.view = view;
        return this;
    }

    public HashMap<String, Object> getSession()
    {
        return session;
    }

    public Object getSession(String sessionName)
    {
        return this.getSession().get(sessionName);
    }

    public void setSession(HashMap<String, Object> session)
    {
        this.session = session;
    }

    public void addSession(String sessionName, Object object)
    {
        this.getSession().put(sessionName, object);
    }

    public ModelView()
    {
        this.setSession(new HashMap<>());
        this.setData(new HashMap<>());
    }

    public void additem(String key, Object value){
        this.getData().put(key,value);
    }

    public void sendData(HttpServletRequest request)
    {
        this.getData().forEach((key, value) -> {
            request.setAttribute(key, value);
        });
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }

    public List<String> getRemoveSession() {
        return removeSession;
    }

    public void setRemoveSession(List<String> removeSession) {
        this.removeSession = removeSession;
    }
}

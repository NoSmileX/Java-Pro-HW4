package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static final Users usersList = new Users();
    private final List<User> list = new ArrayList<>();
    private final Gson gson;

    public static Users getInstance() {
        return usersList;
    }

    private Users() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(User user) {
        list.add(user);
    }

    public synchronized String toJson() {
        if (list.isEmpty()) return null;
        return gson.toJson(new JsonUserList(list));
    }
}

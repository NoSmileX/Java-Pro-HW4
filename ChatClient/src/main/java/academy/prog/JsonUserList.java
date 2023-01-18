package academy.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUserList {
    private final List<User> list = new ArrayList<>();

    public JsonUserList(List<User> userList) {
        list.addAll(userList);
    }

    public List<User> getList() {
        return Collections.unmodifiableList(list);
    }
}


package academy.prog;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetUsers {
    public static void getUsers() throws IOException {
        Gson gson = new Gson();
        URL url = new URL(Utils.getURL() + "/users");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try (InputStream is = http.getInputStream()) {
            byte[] buf = GetThread.responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            JsonUserList userList = gson.fromJson(strBuf, JsonUserList.class);
            if (userList != null) {
                for (User u : userList.getList()) {
                    System.out.println(u);
                }
            } else {
                System.out.println("No active users :( ");
            }
        }
    }

}


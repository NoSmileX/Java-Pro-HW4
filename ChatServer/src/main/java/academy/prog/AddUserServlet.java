package academy.prog;


// POST (json) -> /register -> AddUserServlet -> Users
// GET -> /users -> GetUsersServlet  <- Users (<- json)

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static academy.prog.AddServlet.getBytes;

@WebServlet(name = "Registration", value = "/register")
public class AddUserServlet extends HttpServlet {
    private Users usersList = Users.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = User.fromJSON(bufStr);
        if (user != null)
            usersList.add(user);
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        return getBytes(req);
    }
}

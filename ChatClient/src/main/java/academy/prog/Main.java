package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String login;
            String password;
            while (true) {
                System.out.println("Enter your login: ");
                login = scanner.nextLine();
                System.out.println("Enter your password: ");
                password = scanner.nextLine();

                User u = new User(login, password);
                int res = u.send(Utils.getURL() + "/register");
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occurred: " + res);
                } else {
                    break;
                }
            }

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                String toUserName = "All";
                String[] temp = text.split(" ");
                if (temp.length == 1 && temp[0].equals("/users")) {
                    GetUsers.getUsers();
                } else {
                    if (text.startsWith("@")) {
                        toUserName = temp[0].substring(1);
                    }

                    if (text.isEmpty()) break;


                    Message m = new Message(login, text, toUserName);
                    int res = m.send(Utils.getURL() + "/add");

                    if (res != 200) { // 200 OK
                        System.out.println("HTTP error occurred: " + res);
                        return;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package kr.gagaotalk.server;

import kr.gagaotalk.server.table.ChatroomTables;
import kr.gagaotalk.server.table.OnlineUserTable;
import kr.gagaotalk.server.table.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseEG {
    static Connection con = null;

    private static void makeConnection(String url, String userName, String password) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            System.out.println(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws ClassNotFoundException, ErrorInProcessingException {
        /*System.out.println("Enter the SQL server password:");
        Scanner kb = new Scanner(System.in);
        String pw = kb.nextLine(); // or create password String variable*/
        String pw = "1234";
        makeConnection("jdbc:mysql://localhost", "root", pw);

        ChatroomTables chatroomInUser1 = new ChatroomTables(con, "user1");
        chatroomInUser1.makeTable();

        UserTable user1 = new UserTable(con, "userTable");
        user1.makeTable();

        OnlineUserTable onlineUserTable = new OnlineUserTable(con, "onlineUserTable");
        onlineUserTable.makeTable();

        // below : just test code
        System.out.println(user1.signup("user1", "ddong", "01012345678", "20021001", "1234"));
        System.out.println(user1.updatePassword("user1", "1111", "1234"));
        System.out.println(user1.updateUserInfo("user1", "ddong", "20021001", "sss"));
        System.out.println(user1.getUserInfo("user"));
        //System.out.println(user1.findPW("user1", "01012345678"));
        System.out.println(user1.login("user1", "1233"));
        System.out.println(user1.logout("user1"));
        System.out.println(user1.login("user1", "1233"));
        //System.out.println(user)



    }
}

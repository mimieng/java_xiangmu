import dao.UserDao;
import dao.UserDaoImpl;
import pojo.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    System.out.println("*********欢迎来到学生管理系统********");
    System.out.println("***************请登录*************");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的用户名");
        String count = scanner.next();
        System.out.println("请输入您的密码");
        String psw = scanner.next();
        UserDao userDao = new UserDaoImpl();
        User user = userDao.login(count,psw);
        if(user != null){
            System.out.println("欢迎【"+user.getName()+"】登录成绩管理系统");
        }else{
            System.out.println("登录失败");
        }

    }
}

package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Press 1 to Login");
        System.out.println("Press 2 to SignUp");
        System.out.println("Press 0 to Exit");
        int choice;
        try{
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (choice){
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Email: ");
        String email = sc.nextLine();
        try {
            if(UserDAO.Exists(email)){
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the otp: ");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                    new UserView(email).home();
                    System.out.println("Welcome");
                } else{
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User not Found");
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();

        User user = new User(name, email);
        int response= UserService.saveUser(user);
        switch (response) {
            case 0 -> System.out.println("User already exists");
            case 1 -> {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the otp: ");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                    System.out.println("User Registered");
                } else{
                    System.out.println("Wrong OTP");
                }
            }
        }
    }
}

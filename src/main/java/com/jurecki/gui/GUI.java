package com.jurecki.gui;

import com.jurecki.db.GameRepository;
import com.jurecki.model.Game;

import java.util.Scanner;

/**
 * Author Dawid Jurecki
 */

public class GUI {

    private static int menu(){
        while(true){
            System.out.println("1. Show game");
            System.out.println("2. Add game");
            System.out.println("3. Update game");
            System.out.println("4. Delete game");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5")) {
                return Integer.parseInt(choice);
            }
        }
    }

    public static void showGUI(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            GameRepository gameRepository = new GameRepository();
            int choice = menu();
            switch (choice){
                case 1:
                    System.out.println("Enter car id: ");
                    Game game = gameRepository.getGameById(scanner.nextInt());
                    System.out.println(game);
                    break;
                case 2:
                    gameRepository.persistGame(createGame());
                    break;
                case 3:
                    System.out.println("Enter car id: ");
                    gameRepository.updateCar(scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Enter car id: ");
                    gameRepository.deleteCar(scanner.nextInt());
                    break;
                case 5:
                    return;
            }
        }
    }

    private static Game createGame(){
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Enter title: ");
        game.setTitle(scanner.nextLine());
        System.out.println("Enter publisher: ");
        game.setPublisher(scanner.nextLine());
        return game;
    }
}

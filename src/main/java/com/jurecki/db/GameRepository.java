package com.jurecki.db;

import com.jurecki.model.Game;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

/**
 * Author Dawid Jurecki
 */

public class GameRepository {

    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public void persistGame(Game game) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(game);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }

    public Game getGameById(int id) {
        Session session = factory.openSession();
        Game games = (Game) session.createQuery("FROM com.jurecki.model.Game WHERE id = " + id).uniqueResult();
        session.close();
        return games;
    }

    public void updateGame(int id) {
        Game game = getGameById(id);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Update title: ");
        game.setTitle(scanner.nextLine());
        System.out.println("Update publisher: ");
        game.setPublisher(scanner.nextLine());

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(game);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteGame(int id) {
        Game game = getGameById(id);
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(game);
            tx.commit();
        } catch (HibernateException e){
            if(tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}

/*

    public void updateCar(Game game){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter correct title: ");
        String newTitle = scanner.nextLine();
        System.out.println("Enter correct publisher: ");
        String newPublisher = scanner.nextLine();

        Session session = factory.openSession();
      //  Query q = session.createQuery("UPDATE game SET title = :newTitle AND publisher = :newPublisher" +
        //        "where title = :currectTitle AND publisher = :currentPublisher");
        Query q = session.createQuery("UPDATE Game SET title = " + newTitle + ", publisher = " + newPublisher
        + " WHERE title = " + game.getTitle() + " AND publisher = " + game.getPublisher());
        q.executeUpdate();
        session.close();
    }
*/
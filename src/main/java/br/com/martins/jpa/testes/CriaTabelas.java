package br.com.martins.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaTabelas {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        em.close();
    }
}

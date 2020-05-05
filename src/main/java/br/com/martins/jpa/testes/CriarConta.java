package br.com.martins.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Conta;

public class CriarConta {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        Conta conta = new Conta();
        conta.setAgencia(234);
        conta.setNumero(7263);
        conta.setTitular("Paulo");
        conta.setSaldo(500.00);
        
        em.getTransaction().begin();
        em.persist(conta);
        em.getTransaction().commit();
        em.close();
    }
}

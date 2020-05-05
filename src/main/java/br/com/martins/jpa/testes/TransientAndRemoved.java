package br.com.martins.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Conta;

public class TransientAndRemoved {
    
    public static void main(String[] args) {
        
        // estado Transient (não possui vínculo com o JPA)
        // é apenas um objeto em memória (Bean), candidato a se tornar Managed
        Conta conta = new Conta();
        conta.setTitular("Joana");
        conta.setAgencia(8989);
        conta.setNumero(2324);
        conta.setSaldo(23.00);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        // passagem do estado Transient -> Managed
        em.persist(conta);
        
        System.out.println("--------");
        
        // Removed: objeto removido do contexto JPA, e por isso, removido do banco
        // passagem do estado Managed -> Removed
        em.remove(conta);
        
        em.getTransaction().commit();
        em.close();
    }
}

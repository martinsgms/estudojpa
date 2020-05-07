package br.com.martins.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Cliente;
import br.com.martins.jpa.modelo.Conta;

public class RelacionamentoClienteConta {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        Conta conta = new Conta();
        conta.setId(2L);
        
        Cliente segundoDono = new Cliente();
        segundoDono.setId(3L);
        
        // adicionando um segundo cliente a uma conta já existente
        conta.setCliente(segundoDono);
        
        em.getTransaction().begin();
        
        // resultado java.sql.SQLIntegrityConstraintViolationException
        em.merge(conta);
        
        em.getTransaction().commit();

        em.close();
    }
}

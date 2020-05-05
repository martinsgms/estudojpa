package br.com.martins.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Conta;

public class ManagedAndDetached {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        // devolvida a refer�ncia do banco
        Conta conta = em.find(Conta.class, 1L);
        
        // estado Managed (sincroniza��o autom�tica com a refer�ncia)
        em.getTransaction().begin();
        conta.setSaldo(1000.00);
        em.getTransaction().commit();
        em.close();
        // EM fechado, a partir daqui os objetos pertencer�o ao estado Detached (sem sincroniza��o autom�tica)
        
        EntityManager em2 = emf.createEntityManager();
        
        // esta linha sem o EM2 n�o � suficiente p/ sincronizar o objeto
        conta.setAgencia(999);
        
        em2.getTransaction().begin();
        // o merge far� a varredura (select) dos atributos e verificar� uma altera��o de estado (Agencia) e ir� sincronizar (update) com a refer�ncia 
        em2.merge(conta);
        em2.getTransaction().commit();
        em2.close();
    }
}

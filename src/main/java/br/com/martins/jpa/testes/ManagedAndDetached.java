package br.com.martins.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Conta;

public class ManagedAndDetached {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        // devolvida a referência do banco
        Conta conta = em.find(Conta.class, 1L);
        
        // estado Managed (sincronização automática com a referência)
        em.getTransaction().begin();
        conta.setSaldo(1000.00);
        em.getTransaction().commit();
        em.close();
        // EM fechado, a partir daqui os objetos pertencerão ao estado Detached (sem sincronização automática)
        
        EntityManager em2 = emf.createEntityManager();
        
        // esta linha sem o EM2 não é suficiente p/ sincronizar o objeto
        conta.setAgencia(999);
        
        em2.getTransaction().begin();
        // o merge fará a varredura (select) dos atributos e verificará uma alteração de estado (Agencia) e irá sincronizar (update) com a referência 
        em2.merge(conta);
        em2.getTransaction().commit();
        em2.close();
    }
}

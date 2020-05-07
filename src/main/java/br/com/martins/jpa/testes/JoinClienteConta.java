package br.com.martins.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.martins.jpa.modelo.Conta;

public class JoinClienteConta {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        //             select * from conta co join cliente cl on co.cliente_id = cl.id;
        String jpql = "select co from Conta co join co.cliente";
        
        TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
        List<Conta> contas = query.getResultList();
        
        contas.forEach(c -> {
            System.out.println(c.getTitular() + " é " + c.getCliente().getProfissao());
        });
        
    }
}

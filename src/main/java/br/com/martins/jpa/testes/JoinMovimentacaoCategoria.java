package br.com.martins.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.martins.jpa.modelo.Movimentacao;

public class JoinMovimentacaoCategoria {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        String jpql = "select m from Movimentacao m join m.categoria";
        
        TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
        List<Movimentacao> movimentacoes = query.getResultList();
        
        movimentacoes.forEach(m -> {
            System.out.println("titular: " + m.getConta().getTitular() + ", fez as movimentações: " + m.getCategoria());
        });
    }
}

package br.com.martins.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Conta;
import br.com.martins.jpa.modelo.Movimentacao;
import br.com.martins.jpa.modelo.TipoMovimentacao;

public class ManyToOne {

    public static void main(String[] args) {
        
        Conta conta = new Conta();
        conta.setAgencia(9827);
        conta.setNumero(2833);
        conta.setTitular("Mariana");
        conta.setSaldo(500.00);
        
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setConta(conta);
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setValor(new BigDecimal(20.0));
        movimentacao.setDescricao("Churrasco");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(conta);
        em.persist(movimentacao);
        
        em.getTransaction().commit();
        
        em.close();
    }
}

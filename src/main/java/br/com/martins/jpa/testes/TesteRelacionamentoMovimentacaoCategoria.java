package br.com.martins.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.CategoriaMovimentacao;
import br.com.martins.jpa.modelo.Conta;
import br.com.martins.jpa.modelo.Movimentacao;
import br.com.martins.jpa.modelo.TipoMovimentacao;

public class TesteRelacionamentoMovimentacaoCategoria {
    
    public static void main(String[] args) {
        
        Conta conta = new Conta();
        conta.setId(6L);
        
        CategoriaMovimentacao c1 = new CategoriaMovimentacao("Viagem");
        CategoriaMovimentacao c2 = new CategoriaMovimentacao("Negócios");
        
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setConta(conta);
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setValor(new BigDecimal(200.0));
        movimentacao.setDescricao("Viagem ao RJ");
        movimentacao.setCategoria(Arrays.asList(c1, c2));

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setConta(conta);
        movimentacao2.setData(LocalDateTime.now());
        movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao2.setValor(new BigDecimal(300.0));
        movimentacao2.setDescricao("Viagem a SP");
        movimentacao2.setCategoria(Arrays.asList(c1, c2));
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(c1);
        em.persist(c2);
        
        em.persist(movimentacao);
        em.persist(movimentacao2);
        
        em.getTransaction().commit();
        
        em.close();
    }
}

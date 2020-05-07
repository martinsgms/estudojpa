package br.com.martins.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.martins.jpa.modelo.Cliente;
import br.com.martins.jpa.modelo.Conta;

public class TesteJPQL {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        // jpql -> query orientada a objetos
        String jpql = "select c from Conta c where c.cliente = :pcliente";
        
        // passamos um objeto como parâmetro contendo o filtro que desejamos
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        
        // a query é criada e os parâmetros setados
        Query query = em.createQuery(jpql);
        query.setParameter("pcliente", cliente);
        
        // enfim obtemos o resultado em lista
        List<Conta> resultList = query.getResultList();
        
        for (Conta c : resultList) {
            System.out.println("---------------");
            System.out.println("titular: " + c.getTitular() + "; saldo: R$ " + c.getSaldo());
        }
        
    }
}

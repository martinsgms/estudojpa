package br.com.martins.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
        
        // o tipo do retorno da query neste caso é indefinido, podendo dar problema ao obter o resultado (ClassCast)
        // a query é criada e os parâmetros setados
//        Query query = em.createQuery(jpql);
//        query.setParameter("pcliente", cliente);
        
        // por isso, tipamos a query p/ na hora de receber o resultado na lista de Conta, não dê problema de cast
        TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
        query.setParameter("pcliente", cliente);
        
        // enfim obtemos o resultado em lista
        List<Conta> resultList = query.getResultList();
        
        for (Conta c : resultList) {
            System.out.println("---------------");
            System.out.println("titular: " + c.getTitular() + "; saldo: R$ " + c.getSaldo());
        }
        
    }
}

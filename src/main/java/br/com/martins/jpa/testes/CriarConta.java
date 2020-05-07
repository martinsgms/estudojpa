package br.com.martins.jpa.testes;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.martins.jpa.modelo.Cliente;
import br.com.martins.jpa.modelo.Conta;

public class CriarConta {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudojpa");
        EntityManager em = emf.createEntityManager();
        
        // carga de clientes e contas
        
        Cliente c1 = new Cliente("Maria", "Professor", "Av Auróra, 1");
        Cliente c2 = new Cliente("Kleber", "Engenheiro", "Av Bahia, 20");
        Cliente c3 = new Cliente("José", "Mecânico", "Est. Cosmos, 3");
        Cliente c4 = new Cliente("Joana", "Eletricista", "Rua Dentatus, 44");
        Cliente c5 = new Cliente("Paula", "Bombeiro", "Av Ereumana, 13");
        
        List<Cliente> clientes = Arrays.asList(c1, c2, c3, c4, c5);

        List<Conta> contas =  Arrays.asList(
            new Conta(c1 ,123, 2233, "Maria", 300.0),
            new Conta(c2 ,334, 2233, "Kleber", 1300.0),
            new Conta(c3 ,654, 2233, "José", 200.0),
            new Conta(c4 ,454, 2233, "Joana", 20.0),
            new Conta(c5 ,4545, 5454, "Paula", 2000.0)
        );
        
        em.getTransaction().begin();
        
        clientes.forEach(c -> em.persist(c));
        contas.forEach(c -> em.persist(c));
        
        em.getTransaction().commit();
        em.close();
    }
}

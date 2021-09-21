package no.hvl.dat250.jpa.basicexample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "people";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select t from Family t");
        List<Family> familyList = q.getResultList();
        for (Family family : familyList) {
            System.out.println(family);
        }
        System.out.println("Size: " + familyList.size());

        // create new family
        em.getTransaction().begin();
        Family family = new Family();
        family.setDescription("This is a test");
        em.persist(family);
        em.getTransaction().commit();

        em.close();
    }
}

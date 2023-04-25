package java.repository.db_repo;

import exceptions.IllegalIdException;
import model.Advert;
import model.Transaktion;
import repository.TransactionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

public class DBTransactionRepository implements TransactionRepository
{
    private final EntityManager manager;

    /**
     * initializer for the database repository
     * @throws PersistenceException if the connection with the database fails
     */
    public DBTransactionRepository()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
    }

    @Override
    public void add(Transaktion transaktion)
    {
        manager.getTransaction().begin();
        manager.persist(transaktion);
        manager.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) throws IllegalIdException {
        manager.getTransaction().begin();
        Transaktion a = manager.find(Transaktion.class, id);
        if(a!=null)
            manager.remove(a);
        else
            throw new IllegalIdException();
        manager.getTransaction().commit();
    }

    @Override
    public void update(Integer id, Transaktion transaktion) throws IllegalIdException {
        manager.getTransaction().begin();
        Transaktion a = manager.find(Transaktion.class, id);
        if(a!=null)
        {
            a.update(transaktion);
            manager.merge(a);
        }
        else
            throw new IllegalIdException();
        manager.getTransaction().commit();
    }

    @Override
    public Transaktion findId(Integer id) throws IllegalIdException {
        manager.getTransaction().begin();
        Transaktion a = manager.find(Transaktion.class, id);
        manager.getTransaction().commit();
        if(a==null)
            throw new IllegalIdException();
        return a;
    }

    @Override
    public List<Transaktion> findAll()
    {
        manager.getTransaction().begin();
        List<Transaktion> list = manager.createQuery("SELECT a FROM Transaktion a", Transaktion.class ).getResultList();
        manager.getTransaction().commit();

        return list;
    }

    @Override
    public List<Transaktion> getTransactionsByCar(Advert advert)
    {
        manager.getTransaction().begin();
        List<Transaktion> list = manager.createQuery("SELECT a FROM Transaktion a where a.ad=:advert", Transaktion.class ).setParameter("advert", advert).getResultList();
        manager.getTransaction().commit();

        return list;
    }
}

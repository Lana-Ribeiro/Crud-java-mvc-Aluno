package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class EmpresaDao {

    private EntityManager manager;
    
    
      //Conectando com o banco de dados
    private void conectar() {
        manager = Persistence.createEntityManagerFactory("siteAlunoPU").createEntityManager();
    }

     //Salvando no banco de dados
    public <T> int salvar(T obj) {
        conectar();
        try {
            manager.getTransaction().begin();
            manager.persist(obj);
            manager.getTransaction().commit();
            return 1;
        } catch (RollbackException erro) { // duplicação de PK   
            return 2;
        } catch (Exception erro) {
            return 0;
        }
    }

    
     //Mostrar os departamentos listados
    public <T> List listar(String queryNomeada, Class<T> classe) {
        conectar();
        try {
            return manager.createNamedQuery(queryNomeada,classe).getResultList();
        } catch (NoResultException erro) {
            return null;
        }
    }
    
    //Excluir departamentos
    public <T>int excluir(int pk, Class<T> classe) {
        conectar();
        try {
            T obj = manager.find(classe, pk);
            if (obj == null) {
                return 2;
            } else { 
                manager.getTransaction().begin();
                manager.remove(obj);
                manager.getTransaction().commit();
                return 1;
            }
        } catch (Exception erro) {
            return 0;
        }
    }
    //alterar 
    public <T> int alterar(T obj) {
    conectar();
    try {
        manager.getTransaction().begin();
        T objetoAtualizado = manager.merge(obj);
        manager.getTransaction().commit();
        return 1;
    } catch (RollbackException erro) {
        return 0;
    } catch (Exception erro) {
        manager.getTransaction().rollback();
        return 2;
    } finally {
        manager.close();
    }
    }
    
        
        //buscar departamento
        public <T> T buscar(Class<T> classe, int id) {
        conectar();
        try {
           return manager.find(classe, id);
        } catch (Exception x) {
            return null;
        }
    }

        
}


package com.example.aula03.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.aula03.entity.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoriaRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Categoria inserir(Categoria categoria) {
        entityManager.persist(categoria);
        return categoria;
    }

    @Transactional
    public Categoria atualizar(Categoria categoria) {
        entityManager.merge(categoria);
        return categoria;
    }

    @Transactional
    public void excluir(Categoria categoria) {
        entityManager.remove(categoria);
    }

    @Transactional
    public void excluir(int id_categoria) {
        excluir(entityManager.find(Categoria.class, id_categoria));
    }

    @Transactional(readOnly = true)
    public List<Categoria> obterPorId(int id_categoria) {
        String jpql = "select u from Produto u where u.id_categoria like :id_categoria";
        TypedQuery<Categoria> query = entityManager.createQuery(jpql, Categoria.class);
        query.setParameter("id_categoria", "%" + id_categoria + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Categoria> obterTodos() {
        return entityManager.createQuery("from Produto", Categoria.class).getResultList();
    }

}

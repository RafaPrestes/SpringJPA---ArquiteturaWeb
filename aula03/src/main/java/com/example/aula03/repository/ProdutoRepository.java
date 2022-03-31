package com.example.aula03.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.example.aula03.entity.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Produto inserir(Produto produto) {
        entityManager.persist(produto);
        return produto;
    }

    @Transactional
    public Produto atualizar(Produto produto) {
        entityManager.merge(produto);
        return produto;
    }

    @Transactional
    public void excluir(Produto produto) {
        entityManager.remove(produto);
    }

    @Transactional
    public void excluir(int id_produto) {
        excluir(entityManager.find(Produto.class, id_produto));
    }

    @Transactional(readOnly = true)
    public List<Produto> obterPorId(int id_produto) {
        String jpql = "select u from Produto u where u.id_produto like :id_produto";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("id_produto", "%" + id_produto + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Produto> obterTodos() {
        return entityManager.createQuery("from Produto", Produto.class).getResultList();
    }

}

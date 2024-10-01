package com.opticamarcos.repository;

import com.opticamarcos.model.Armazon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ArmazonRepositoryCustomImpl implements ArmazonRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    final Integer pageSize = 10;

//    @Override
//    public List<Armazon> findByTieneDescuento(Integer currentPage, Boolean tieneDescuento) {
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Armazon> query = cb.createQuery(Armazon.class);
//        Root<Armazon> root = query.from(Armazon.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (tieneDescuento) {
//            predicates.add(cb.gt(root.get("precioDescuento"), 0));
//        }else{
//            predicates.add(cb.equal(root.get("precioDescuento"), 0));
//        }
//
//        query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//
//        return em.createQuery(query).setFirstResult(currentPage).setMaxResults(pageSize).getResultList();
//    }

    @Override
    public List<Armazon> findArmazonesByPrecio(Integer currentPage, Double regularDesde, Double regularHasta) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Armazon> query = cb.createQuery(Armazon.class);
        Root<Armazon> root = query.from(Armazon.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.between(root.get("precioRegular"), regularDesde, regularHasta));

        query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return em.createQuery(query).setFirstResult(currentPage).setMaxResults(pageSize).getResultList();
    }

//    @Override
//    public List<Armazon> findArmazonesByPreciosDescuento(Integer currentPage, Double descuentoDesde, Double descuentoHasta) {
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Armazon> query = cb.createQuery(Armazon.class);
//        Root<Armazon> root = query.from(Armazon.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.between(root.get("precioDescuento"), descuentoDesde, descuentoHasta));
//
//        query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//
//        return em.createQuery(query).setFirstResult(currentPage).setMaxResults(pageSize).getResultList();
//    }
//
//    @Override
//    public List<Armazon> findArmazonesByPreciosTarjeta(Integer currentPage, Double tarjetaDesde, Double tarjetaHasta) {
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Armazon> query = cb.createQuery(Armazon.class);
//        Root<Armazon> root = query.from(Armazon.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(cb.between(root.get("precioTarjeta"), tarjetaDesde, tarjetaHasta));
//
//        query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//
//        return em.createQuery(query).setFirstResult(currentPage).setMaxResults(pageSize).getResultList();
//    }
}

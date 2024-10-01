package com.opticamarcos.repository;

import com.opticamarcos.model.Ficha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FichaRepositoryCustomImpl implements FichaRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ficha> getFichasByFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Integer estaSeniado, Integer estaPago) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ficha> query = criteriaBuilder.createQuery(Ficha.class);
        Root<Ficha> root = query.from(Ficha.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.between(root.get("fecha"), fechaDesde, fechaHasta));
        predicates.add(criteriaBuilder.between(root.get("total"), totalDesde, totalHasta));
        //(criteriaBuilder.ge(root.get("senia"), 0) MAYOR O IGUAL
        switch (estaSeniado){
            case 1:
                predicates.add(criteriaBuilder.gt(root.get("senia"), 0));
                break;
            case 2:
                predicates.add(criteriaBuilder.equal(root.get("senia"), 0));
                break;
        }

        switch (estaPago){
            case 1:
                predicates.add(criteriaBuilder.equal(root.get("saldo"), 0));
                break;
            case 2:
                predicates.add(criteriaBuilder.gt(root.get("saldo"), 0));
                break;
        }

        query.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}

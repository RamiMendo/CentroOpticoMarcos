package com.opticamarcos.repository;

import com.opticamarcos.model.entity.Ficha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FichaRepositoryCustomImpl implements FichaRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Ficha> getFichasByFiltro(Pageable pageable, LocalDate fechaDesde, LocalDate fechaHasta, Double totalDesde, Double totalHasta, Integer estaSeniado, Integer estaPago) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ficha> query = criteriaBuilder.createQuery(Ficha.class);
        Root<Ficha> root = query.from(Ficha.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.between(root.get("fecha"), fechaDesde, fechaHasta));

        if (!Objects.equals(totalDesde, totalHasta)){
            predicates.add(criteriaBuilder.between(root.get("total"), totalDesde, totalHasta));
        }

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

        TypedQuery<Ficha> typedQuery = entityManager.createQuery(query);
        int total = typedQuery.getResultList().size();

        List<Ficha> listaFichas = typedQuery
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(listaFichas, pageable, total);
    }
}

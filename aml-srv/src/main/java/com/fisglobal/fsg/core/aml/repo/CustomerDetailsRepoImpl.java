package com.fisglobal.fsg.core.aml.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fisglobal.fsg.core.aml.entity.CustomerDetailsEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class CustomerDetailsRepoImpl {
	
	@Autowired
	EntityManager em;

	public List<CustomerDetailsEntity> getCustomerDetailsbyCriteria(String custId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CustomerDetailsEntity> cq = cb.createQuery(CustomerDetailsEntity.class);

		Root<CustomerDetailsEntity> book = cq.from(CustomerDetailsEntity.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(book.get("customerId"), custId));
		// Predicate authorNamePredicate = cb.equal(book.get("acknowledgementNo"),
		// ackNo);
		// Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
		cq.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<CustomerDetailsEntity> query = em.createQuery(cq);
		return query.getResultList();

	}
	
	
}
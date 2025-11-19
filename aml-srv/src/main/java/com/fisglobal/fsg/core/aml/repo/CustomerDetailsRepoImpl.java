package com.fisglobal.fsg.core.aml.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger LOGGER = LoggerFactory.getLogger(CustomerDetailsRepoImpl.class);

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

	public CustomerDetailsEntity getCustomerDetailsByCustId(String custId) {
		CriteriaBuilder cb = null;
		CriteriaQuery<CustomerDetailsEntity> cq = null;
		Root<CustomerDetailsEntity> book = null;
		List<Predicate> predicates = null;
		TypedQuery<CustomerDetailsEntity> query = null;
		try {
			cb = em.getCriteriaBuilder();
			cq = cb.createQuery(CustomerDetailsEntity.class);
			book = cq.from(CustomerDetailsEntity.class);
			predicates = new ArrayList<Predicate>();
			predicates.add(cb.equal(book.get("customerId"), custId));
			cq.where(predicates.toArray(new Predicate[] {}));

			query = em.createQuery(cq);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		} finally {

		}
	}
}
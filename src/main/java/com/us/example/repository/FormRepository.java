package com.us.example.repository;

import com.us.example.domain.Form;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {
	@Override
	Form getOne(Integer aLong);

	@Override
	List<Form> findAll();

	@Override
	void delete(Integer aLong);

	@Override
	Form saveAndFlush(Form form);

	List<Form> findAllByUserid(Integer userid);

	List<Form> findAll(Specification<Form> spec);
}
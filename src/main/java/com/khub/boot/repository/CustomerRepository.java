/**
 * @author NagiReddy
 */

package com.khub.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khub.boot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
	//Custom method using Query DSL (Domine Specific language)
	//After findBy must keep column Name
	public List<Customer> findByFirstName(String firstName);
	
	public List<Customer> findByFirstNameOrderByFirstName(String firstName);
	
	public List<Customer> findByFirstNameOrderById(String firstName);
	
	public List<Customer> findByFirstNameOrderByIdDesc(String firstName);
	
	//@Query Annotation
	
	@Query(value = "select d.* from customer d where d.first_name = :firstName", nativeQuery = true)
	public List<Customer> findByCustomerName(@Param("firstName") String firstName);
	
	@Query(value = "select d.* from customer d where d.first_name = :firstName", nativeQuery = true)
	public List<Customer> findByName(@Param("firstName") String firstname);

}

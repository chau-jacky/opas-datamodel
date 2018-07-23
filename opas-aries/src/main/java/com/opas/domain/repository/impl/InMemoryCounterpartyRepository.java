package com.opas.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.opas.domain.Counterparty;
import com.opas.domain.repository.CounterpartyRepository;
import com.opas.exception.CounterpartyNotFoundException;

@Repository
public class InMemoryCounterpartyRepository implements CounterpartyRepository{
  
   @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

   @Override
   public List<Counterparty> getAllCounterparties() {
      Map<String, Object> params = new HashMap<String, Object>();
        List<Counterparty> result = jdbcTemplate.query("SELECT * FROM counterparties", params, new CounterpartyMapper());
      
        return result;
   }

   private static final class CounterpartyMapper implements RowMapper<Counterparty> {
      public Counterparty mapRow(ResultSet rs, int rowNum) throws SQLException {
    	 Counterparty counterparty = new Counterparty();
    		
    	 counterparty.setCounterpartyId(rs.getString("ID"));
    	 counterparty.setCounterpartyAcronym(rs.getString("ACRONYM"));
    	 counterparty.setCustomerIndicator(rs.getString("CUSTOMER_IND"));
    	 counterparty.setSwiftAddress(rs.getString("SWIFT_ADR"));
    	 counterparty.setFaxNumber(rs.getString("FAX_NUM"));
    	 counterparty.setCounterpartyType(rs.getString("TYPE"));
         return counterparty;
      }
   }
      
   @Override
   public Counterparty getCounterpartyById(String counterpartyID) {
      String SQL = "SELECT * FROM counterparties WHERE ID = :id";  
      Map<String, Object> params = new HashMap<>();
      params.put("id", counterpartyID);  
         
      try {
         return jdbcTemplate.queryForObject(SQL, params, new CounterpartyMapper());
      } catch (DataAccessException e) {
         throw new CounterpartyNotFoundException(counterpartyID);
      }   
   }

}

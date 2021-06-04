package com.crowleyworks.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.crowleyworks.endpoints.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Value("${systemValue:Default Secret Message}")
    private String systemValue;

    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
    
    public BookingService() {}

    public void sayStuff() {
        System.out.println("My secret message is: " + this.systemValue);
    }

	public void create(Booking x) {
		final Booking c = x;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	        	StringBuffer query = new StringBuffer("insert into bookings(charter_id, customer_name, comments");
	        	query.append(" VALUES (?, ?, ?)");
	            PreparedStatement ps = connection.prepareStatement(query.toString(), new String[] {"id"});
	            ps.setLong(1, c.getCharterId());
	            ps.setString(2, c.getCustomerName());
	            ps.setString(3, c.getComments());
	            return ps;
	        }
	    };
		jdbcTemplate.update(psc, keyHolder);
		x.setId(keyHolder.getKey().longValue());
	}

	public void update(Booking x) {
		// Don't allow the ID to be updated
    	StringBuffer query = new StringBuffer("update bookings set customer_name=?, comments=?, ");
    	query.append("charter_id=? where id=?");
			jdbcTemplate.update(query.toString(), new Object[] { x.getCustomerName(), x.getComments(), x.getCharterId(),
				 x.getId()});
	}

	public void delete(long charterId, long x) {
		jdbcTemplate.update("delete from charters where charter_id=? and id=?", new Object[] { charterId, x });
	}


    public Booking get(long charterId) throws Exception {
		StringBuffer query = new StringBuffer("select id, charter_id, customer_name, comments from bookings where charter_id=?");
		return jdbcTemplate.queryForObject(query.toString(), new BookingRowMapper(), charterId);
	}

	public List<Booking> getAll() {
		StringBuffer query = new StringBuffer("select id, charter_id, customer_name, comments from bookings order by customerName");
		return jdbcTemplate.query(query.toString(), new BookingRowMapper());
	}


    public static final class BookingRowMapper implements RowMapper<Booking> {
		
        @Override
		public Booking mapRow(ResultSet rs, int arg1) throws SQLException {
            long id = rs.getLong("id");
            long charterId = rs.getLong("charter_id");
            String customerName = rs.getString("customer_name");
            String comments = rs.getString("comments");
			Booking x = new Booking(id, charterId, customerName, comments);
			return x;
		}
	}

}

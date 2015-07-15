------------------------- CONSUMERS ---------------------------------------------

-- all
select * 
from consumer;

-- With active connection
select * 
from consumer
	left outer join cons_conn 
		on consumer.id = cons_conn.consumer_id
	left outer join connection
		on connection.id = cons_conn.connection_id
where connection.active = true;

------------------------- CONNECTIONS ---------------------------------------------

-- all
select * 
from connection;

-- all active
select * 
from connection
where active = true;
    
-- all belonging to a pwd_class
select * 
from connection
	inner join pwd_class
		on connection.pwd_class_id = pwd_class.id
where pwd_class_id = 0;

-- all connections with arrears
select * 
from connection
where current_balance < 0;

-- all connections with extra payment
select * 
from connection
where current_balance > 0; 

-- all belonging to a constituency
select * 
from connection
	inner join constituency
		on connection.constituency_id = constituency.id
where constituency_id = 0;

-- all belonging to a political_zone
select * 
from connection
	inner join political_zone
		on connection.political_zone_id = political_zone.id
where political_zone_id = 0;

-- units billed in period

-- revenue billed in period

-- revenue collected in period
        
------------------------- METER_READER ---------------------------------------------

-- all
select * 
from meter_reader;
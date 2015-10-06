CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_new_reading`(
	IN in_conn_id INT,
    IN in_read_date DATE,
    IN in_reading DOUBLE,
    IN in_fault_id INT,
    IN in_sundry DOUBLE
)
BEGIN

DECLARE var_cycle_id INT;
DECLARE var_cycle_period INT ;
DECLARE var_units_for_cycle DOUBLE;
DECLARE var_water_charges DOUBLE;
DECLARE var_sewage_charges DOUBLE;
DECLARE var_total_charges DOUBLE;
DECLARE var_meter_rent DOUBLE;
DECLARE var_from_unit INT;
DECLARE var_to_unit INT;
DECLARE var_tariff DOUBLE;
    
DECLARE tariff_slabs_for_conn CURSOR FOR
	select tariff_slab.from_unit,tariff_slab.to_unit,tariff_slab.tariff
    from connection conn
		inner join connection_type on conn.connection_type_id = connection_type.id
        inner join tariff_class on connection_type.tariff_class_id = tariff_class.id
        inner join tariff_slab on tariff_slab.tariff_class_id = tariff_class.id
	where conn.id = in_conn_id;
        
DECLARE exit handler for sqlexception
 BEGIN
	-- ERROR
 ROLLBACK;
END;

DECLARE exit handler for sqlwarning
 BEGIN
    -- WARNING
 ROLLBACK;
END;

START TRANSACTION;
    
SELECT 
    MAX(read_cycle_id) + 1
INTO var_cycle_id FROM
    conn_reading
WHERE
    connection_id = in_conn_id;
    
SELECT 
    DATEDIFF(in_read_date,
            (SELECT 
                    MAX(read_date)
                FROM
                    conn_reading
                WHERE
                    connection_id = in_conn_id))
INTO var_cycle_period;
    
SELECT 
    (var_cycle_period * meter_type.rent_per_day)
INTO var_meter_rent FROM
    meter
        INNER JOIN
    meter_type ON meter.meter_type_id = meter_type.id
        INNER JOIN
    meter_rent ON meter_type.id = meter_rent.meter_type_id
WHERE
    meter.connection_id = in_conn_id
        AND meter.active = 1
        AND meter_rent.active = 1;
    
    
SELECT 
    (reading - (SELECT 
            conn_status.current_reading
        FROM
            connection conn
                INNER JOIN
            conn_status ON conn.id = conn_status.conn_id
        WHERE
            conn.id = in_conn_id))
INTO var_units_for_cycle;
    
# Calculate water charges
read_loop: LOOP
	FETCH tariff_slabs_for_conn into var_from_unit,var_to_unit,var_tariff;
       
	IF done THEN
		LEAVE read_loop;
	END IF;
  
END LOOP;
    
SELECT 
    sewage_charge_multiplier * var_water_charges
INTO var_sewage_charges FROM
    connection conn
        INNER JOIN
    connection_type ON conn.connection_type_id = connection_type.id;
    
# Insert new record in conn_reading
insert into conn_reading(connection_id,read_cycle_id,read_date,units,
				water_charges,fault_id,sundry,sewage_charges,meter_rent_charges)
values(in_conn_id,var_cycle_id,in_read_date,var_units_for_cycle,
	var_water_charges,in_fault_id,in_sundry,var_sewage_charges,var_meter_rent);

SET var_total_charges = var_water_charges + var_sewage_charges + in_sundry + var_meter_rent;
    
UPDATE conn_status 
SET 
    current_reading = in_reading,
    current_balance = (current_balance - var_total_charges)
WHERE
    conn_id = in_conn_id;
    
COMMIT;    

END
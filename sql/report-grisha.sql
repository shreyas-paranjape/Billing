-- -----------------------------------------------------
-- Definitions and formulas
-- -----------------------------------------------------

LPCD : Litre per consumer per DAY
DPC : Delayed payment charges
Arrears : Amount which is due to be paid

last_date_for_pymt : issue_date + 14 days
disconnection_date : ?




-- -----------------------------------------------------
-- Category Wise Consumption
-- -----------------------------------------------------

SELECT
    -- conn_details.id SrNo, ** Generate Sr number in the report
    conn_details.category,
    SUM(DATEDIFF(DATE_ADD(cr.read_date, INTERVAL 10 DAY),
            cr.read_date)) / COUNT(cr.id) no_of_days,
    'per_day_cons',
    conn_details.no_of_conn no_of_conn,
    conn_details.no_of_users no_of_users,
    cr.units * 1000
      / (conn_details.no_of_users
          * (SUM(DATEDIFF(DATE_ADD(cr.read_date, INTERVAL 10 DAY),cr.read_date))
          / COUNT(cr.id))) lpcd,
    cr.units AS Total_units,
    SUM(cr.water_charges) water_charges,
    'meter_rent',
    (conn_details.sewage_charge_multiplier * cr.water_charges) sewage_charges,
    'ArrCred',
    'DPC',
    'Total_chrgs'
FROM
    conn_reading AS cr
        INNER JOIN
    (SELECT
        conn.id,
            cat.id cat_id,
            cat.description,
            cat.sewage_charge_multiplier,
            COUNT(conn.id) no_of_conn,
            SUM(conn.no_of_users) no_of_users
    FROM
        connection AS conn
    INNER JOIN connection_category AS cat ON conn.connection_category_id = cat.id
    GROUP BY conn.id) conn_details ON cr.connection_id = conn_details.id
GROUP BY conn_details.cat_id;


-- -----------------------------------------------------
-- Report List of Consumers with Arrears
-- -----------------------------------------------------

SELECT
    consumer.id AS srno,
    consumer.cons_code,
    consumer.consumer_name,
    connection_category.category,
    (SELECT
            MAX(n.read_date)
        FROM
            conn_reading AS n
        WHERE
            n.connection_id = m.connection_id
                AND n.read_date < MAX(m.read_date)) AS from_date,
    MAX(m.read_date) AS issue_date,
    DATEDIFF(MAX(m.read_date),
            (SELECT
                    MAX(n.read_date)
                FROM
                    conn_reading AS n
                WHERE
                    n.connection_id = m.connection_id
                        AND n.read_date < MAX(m.read_date))) AS cycle_period,
    meter.meter_number,
    conn_status.current_balance AS arrears,
    ADDDATE(MAX(m.read_date), 14) AS last_date_for_pymt,
    conn_payment.payment_date AS since_when,
    cons_conn.termination_date AS disconnection_date
FROM
    consumer
        LEFT JOIN
    cons_conn ON consumer.id = cons_conn.consumer_id
        LEFT JOIN
    connection ON connection.id = cons_conn.connection_id
        LEFT JOIN
    conn_status ON conn_status.id = connection.conn_status_id
        LEFT JOIN
    connection_category ON connection.connection_category_id = connection_category.id
        LEFT JOIN
    meter ON meter.connection_id = connection.id
        LEFT JOIN
    conn_reading m ON connection.id = m.connection_id
        LEFT JOIN
    conn_payment ON conn_payment.conn_reading_id = m.id
GROUP BY consumer.id
having arrears < 0 and last_date_for_pymt < now();

-- -----------------------------------------------------
-- Report Disconnection List
-- -----------------------------------------------------

SELECT
    consumer.id AS srno,
    consumer.cons_code,
    consumer.consumer_name,
    connection_category.category,
    meter.meter_number,
    (SELECT
            MAX(n.read_date)
        FROM
            conn_reading AS n
        WHERE
            n.connection_id = m.connection_id
                AND n.read_date < MAX(m.read_date)) AS from_date,
    MAX(m.read_date) AS to_date,
    meter_type.meter_size,
    conn_status.current_balance AS arrears
FROM
    consumer
        LEFT JOIN
    cons_conn ON consumer.id = cons_conn.consumer_id
        LEFT JOIN
    connection ON connection.id = cons_conn.connection_id
        LEFT JOIN
    conn_reading m ON connection.id = m.connection_id
        LEFT JOIN
    conn_status ON conn_status.id = connection.conn_status_id
        INNER JOIN
    connection_category ON connection.connection_category_id = connection_category.id
        LEFT JOIN
    meter ON meter.connection_id = connection.id
        INNER JOIN
    meter_type ON meter.meter_type_id = meter_type.id
GROUP BY consumer.cons_code , m.connection_id;



-- -----------------------------------------------------
-- Report Faulty Meter List
-- -----------------------------------------------------


SELECT
    consumer.id AS srno,
    consumer.cons_code,
    consumer.consumer_name,
    (SELECT
            MAX(n.read_date)
        FROM
            conn_reading AS n
        WHERE
            n.connection_id = m.connection_id
                AND n.read_date < MAX(m.read_date)) AS from_date,
    MAX(m.read_date) AS issue_date,
    DATEDIFF(MAX(m.read_date),
            (SELECT
                    MAX(n.read_date)
                FROM
                    conn_reading AS n
                WHERE
                    n.connection_id = m.connection_id
                        AND n.read_date < MAX(m.read_date))) AS cycle_period,
    fault.description AS Meter_fault,
    meter.meter_number,
    meter_type.meter_size
FROM
    consumer
        INNER JOIN
    cons_conn ON cons_conn.consumer_id = consumer.id
        INNER JOIN
    connection ON connection.id = cons_conn.connection_id
        INNER JOIN
    conn_reading m ON connection.id = m.connection_id
        INNER JOIN
    fault ON fault.id = m.fault_id
        INNER JOIN
    meter ON meter.connection_id = connection.id
        INNER JOIN
    meter_type ON meter_type.id = meter.meter_type_id
GROUP BY consumer.cons_code , m.connection_id;



-- -----------------------------------------------------
-- Report Bills for Ledger
-- -----------------------------------------------------
SELECT
    m.bill_number,
    consumer.cons_code AS code,
    consumer.consumer_name,
    (SELECT
            MAX(n.read_date)
        FROM
            conn_reading AS n
        WHERE
            n.connection_id = m.connection_id
                AND n.read_date < MAX(m.read_date)) AS from_date,
    MAX(m.read_date) AS to_date,
    DATEDIFF(MAX(m.read_date),
            (SELECT
                    MAX(n.read_date)
                FROM
                    conn_reading AS n
                WHERE
                    n.connection_id = m.connection_id
                        AND n.read_date < MAX(m.read_date))) AS no_of_days,
    ADDDATE(MAX(m.read_date), 14) AS due_date,
    (conn_status.current_reading - (SELECT
            units
        FROM
            conn_reading
        WHERE
            read_date = (MAX(m.read_date)))) AS prev_reading,
    conn_status.current_reading,
    (SELECT
            units
        FROM
            conn_reading
        WHERE
            read_date = (MAX(m.read_date))) AS units_billed,
    m.water_charges,
    meter_type.meter_rent,
    m.sundry AS sundry_chrgs,
    (connection_category.sewage_charge_multiplier * m.water_charges) AS sew_charges,
    'arrCred',
    'Total_bill',
    conn_payment.payment_date,
    conn_payment.amount AS paid_amt
FROM
    consumer
        LEFT JOIN
    cons_conn ON consumer.id = cons_conn.consumer_id
        LEFT JOIN
    connection ON connection.id = cons_conn.connection_id
        LEFT JOIN
    conn_status ON conn_status.id = connection.conn_status_id
        LEFT JOIN
    connection_category ON connection.connection_category_id = connection_category.id
        LEFT JOIN
    conn_reading m ON connection.id = m.connection_id
        LEFT JOIN
    conn_payment ON conn_payment.conn_reading_id = m.id
        LEFT JOIN
    meter ON meter.connection_id = connection.id
        LEFT JOIN
    meter_type ON meter_type.id = meter.meter_type_id
GROUP BY consumer.id;



-- -----------------------------------------------------
-- Report Meter Reader List
-- -----------------------------------------------------

SELECT
    consumer.id AS srno,
    consumer.cons_code,
    consumer.consumer_name,
    meter.meter_number,
    (SELECT
            MAX(n.read_date)
        FROM
            conn_reading AS n
        WHERE
            n.connection_id = m.connection_id
                AND n.read_date < MAX(m.read_date)) AS from_date,
    (conn_status.current_reading - (SELECT
            units
        FROM
            conn_reading
        WHERE
            read_date = (MAX(m.read_date)))) AS prev_reading,
    fault.description AS Meter_fault
FROM
    consumer
        INNER JOIN
    cons_conn ON cons_conn.consumer_id = consumer.id
        INNER JOIN
    connection ON connection.id = cons_conn.connection_id
        LEFT JOIN
    conn_status ON conn_status.id = connection.conn_status_id
        INNER JOIN
    meter ON meter.connection_id = connection.id
        LEFT JOIN
    conn_reading m ON connection.id = m.connection_id
        INNER JOIN
    fault ON fault.id = m.fault_id
        AND m.connection_id = connection.id
GROUP BY consumer.cons_code , m.connection_id;






-- -----------------------------------------------------
-- Report Summary of Bills
-- -----------------------------------------------------

SELECT
    consumer.id AS srno,
    m.bill_number,
    consumer.cons_code,
    connection_category.category_code AS cat_code,
    (SELECT
            MAX(n.read_date)
        FROM
            conn_reading AS n
        WHERE
            n.connection_id = m.connection_id
                AND n.read_date < MAX(m.read_date)) AS from_date,
    MAX(m.read_date) AS issue_date,
    DATEDIFF(MAX(m.read_date),
            (SELECT
                    MAX(n.read_date)
                FROM
                    conn_reading AS n
                WHERE
                    n.connection_id = m.connection_id
                        AND n.read_date < MAX(m.read_date))) AS cycle_period,
    (conn_status.current_reading - (SELECT
            units
        FROM
            conn_reading
        WHERE
            read_date = (MAX(m.read_date)))) AS prev_reading,
    conn_status.current_reading,
    (SELECT
            units
        FROM
            conn_reading
        WHERE
            read_date = (MAX(m.read_date))) AS units_billed,
    'bill_base',
    m.water_charges,
    meter_type.meter_rent,
    m.sundry AS Sundry_chrgs,
    (connection_category.sewage_charge_multiplier * m.water_charges) AS sew_charges,
    'arrCred',
    'Total_bill_amt',
    'DPC',
    ADDDATE(MAX(m.read_date), 14) AS due_date,
    connection.no_of_users,
    m.units * 1000 / (connection.no_of_users * (SUM(DATEDIFF(DATE_ADD(m.read_date, INTERVAL 10 DAY),
            m.read_date)) / COUNT(m.id))) lpcd
FROM
    consumer
        INNER JOIN
    cons_conn ON cons_conn.consumer_id = consumer.id
        INNER JOIN
    connection ON connection.id = cons_conn.connection_id
        LEFT JOIN
    conn_reading m ON connection.id = m.connection_id
        LEFT JOIN
    conn_status ON conn_status.id = connection.conn_status_id
        INNER JOIN
    connection_category ON connection.connection_category_id = connection_category.id
        LEFT JOIN
    meter ON meter.connection_id = connection.id
        INNER JOIN
    meter_type ON meter.meter_type_id = meter_type.id
GROUP BY consumer.cons_code , m.connection_id

;

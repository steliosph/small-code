
# Write a procedure in MySQL to split a column into rows using a delimiter. 

CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag');

# For (2), example rows would look like >> “3, white”, “3, Snow” ...

DELIMITER ||
DROP FUNCTION IF EXISTS SPLIT_STR;
CREATE FUNCTION SPLIT_STR(x VARCHAR(255), delim VARCHAR(12), pos INT) RETURNS VARCHAR(255)
 RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1), delim, '');


DROP PROCEDURE IF EXISTS splitProcedure;

CREATE PROCEDURE splitProcedure(dID INT)
BEGIN
  DECLARE i INTEGER;
  DECLARE size INTEGER;

  DROP TEMPORARY TABLE IF EXISTS temp_table;
  CREATE TEMPORARY TABLE temp_table( id INT NOT NULL,value VARCHAR(255) NOT NULL) ENGINE=Memory;

  SET i = 1;
  SET size = 0;
  REPEAT
      INSERT INTO temp_table (id, value)
        SELECT id, SPLIT_STR(name, '|', i) FROM sometbl WHERE id=dID AND ( length(SPLIT_STR(name, '|', i)) != 0);
	
      SELECT length(SPLIT_STR(name, '|', i)) INTO size FROM sometbl WHERE id=dID;
    SET i = i + 1;
    UNTIL size = 0
  END REPEAT;
  SELECT * FROM temp_table ;
END ||

DELIMITER ;

CALL splitProcedure(3);

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  thanglongsp
 * Created: Apr 11, 2019
 */
-- Table Students
Columns:
id          int(11)         PK 
name        varchar(30) 
school      varchar(45) 
age         int(11)

-- DeleteById
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteById`(IN pId int(11))
BEGIN
	delete from students where id=pId; 
END

-- insertStudent
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertStudent`(
		IN pId int(11), 
        IN pName varchar(30), 
        IN pSchool varchar(45),
        IN pAge int(11)
	)
BEGIN
	insert into students value(pId, pName, pSchool, pAge);
END

-- selectAll
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectAll`()
BEGIN
	select * from students;
END

-- selectStudentById
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectStudentById`(IN pId int(8))
BEGIN
	select * from students where id=pId;
END

-- selectStudentByName
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectStudentByName`(IN stName VARCHAR(20))
BEGIN
	select * from students where name=stName;
END
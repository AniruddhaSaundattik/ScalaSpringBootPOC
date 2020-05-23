DROP TABLE IF EXISTS Tbl_TaskList;

CREATE TABLE Tbl_TaskList (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  task_name VARCHAR(250) NOT NULL,
  task_description VARCHAR(250),
);
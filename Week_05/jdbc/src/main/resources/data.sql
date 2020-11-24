DROP TABLE IF EXISTS student;

CREATE TABLE student (
  id INT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO student (id, name) VALUES
  ('1', 'good student'),
  ('2', 'bad student')


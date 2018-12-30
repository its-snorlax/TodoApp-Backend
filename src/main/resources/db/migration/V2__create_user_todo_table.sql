CREATE TABLE user_todo (
  id int PRIMARY KEY AUTO_INCREMENT,
  user_id int NOT NULL,
  title varchar(100) NOT NULL,
  description varchar (1000) NOT NULL ,
  request_Id VARCHAR(100) UNIQUE NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT FK_userId FOREIGN KEY (user_id) REFERENCES user_data(id)
);
DROP TABLE IF EXISTS user;
 
CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  passwd VARCHAR(250) NOT NULL
);

CREATE TABLE image (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,
  delete_hash VARCHAR(250) NOT NULL
);
 
INSERT INTO user (username, passwd) VALUES
  ('admin', '$2a$10$RC9DsvbJivsb1yclTyoUpOg/yC9wRQgNaWnJaMx3Tga/bZX1.T9le');

/*
Photosharing SQL
 */

CREATE TABLE IF NOT EXISTS users (
	id int(11) NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(64) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS images (
	id int(11) NOT NULL AUTO_INCREMENT,
	image blob NOT NULL,
	user_id int(11) NOT NULL,
	description varchar(1000),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS comments (
	id int(11) NOT NULL AUTO_INCREMENT,
	body varchar(5000) NOT NULL,
	image_id int(11) NOT NULL,
	user_id int(11) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (image_id) REFERENCES images(id) ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS roles (
	user_id int(11) NOT NULL AUTO_INCREMENT,
	role varchar(100) NOT NULL,
	PRIMARY KEY (user_id, role),
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS image_users (
	image_id int(11) NOT NULL, 
	user_id int(11) NOT NULL,
	PRIMARY KEY (image_id, user_id),
	FOREIGN KEY (image_id) REFERENCES images(id) ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



/*
 INSERT DUMMY DATA
 */
INSERT INTO users (username, password) VALUES ('admin', '3c11440050cbedc97d35541159636783b81482d81880a4ef22cc7e6c460bdcb8');
INSERT INTO roles (user_id, role) VALUES (1, 'admin');	



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
	url varchar(1000) NOT NULL,
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
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS streams (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(1000) NOT NULL,
	user_id int(11) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS streams_users (
	stream_id int(11) NOT NULL, 
	user_id int(11) NOT NULL,
	PRIMARY KEY (stream_id, user_id),
	FOREIGN KEY (stream_id) REFERENCES streams(id) ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS streams_images (
	stream_id int(11) NOT NULL,
	image_id int(11) NOT NULL,
	PRIMARY KEY (stream_id, image_id),
	FOREIGN KEY (stream_id) REFERENCES streams(id) ON DELETE CASCADE,
	FOREIGN KEY (image_id) REFERENCES images(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
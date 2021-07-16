drop table if exists craft_beer CASCADE;

create table craft_beer
(
	id integer PRIMARY KEY AUTO_INCREMENT, 
	abv double not null, 
	brewery varchar(255), 
	beer_name varchar(255), 
	nice boolean not null
);
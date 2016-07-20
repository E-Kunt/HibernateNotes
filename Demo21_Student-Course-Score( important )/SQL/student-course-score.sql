    create table course (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

    create table student (
        id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    );

	create table score (
        id integer not null auto_increment,
        point integer,
        course_id integer references course (id),
        student_id integer references student (id),
        primary key (id)
    );
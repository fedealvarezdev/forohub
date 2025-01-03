create table usuario
(
    id          bigint          not null auto_increment,
    username    varchar(100)    not null unique,
    email       varchar(200)    not null unique,
    password    varchar(300)    not null,
    activo      tinyint         DEFAULT (1) not null,
    primary key (id)
);

insert into usuario (username, password, email) values ("admin", "$2a$10$JJXYaMZqPr6dAIJGLN8T5e3ryr2yzh3rTEBzQ3c.N8G51K2qatZGG", "admin@admin.com");

create table topico
(
    id              bigint          not null auto_increment,
    titulo          varchar(100)    not null,
    mensaje         varchar(300)    not null,
    fecha_creacion  datetime        not null DEFAULT CURRENT_TIMESTAMP,
    curso           varchar(100)    not null,
    publicado       tinyint         DEFAULT (1) not null,
    id_usuario      bigint          not null,
    primary key (id),
    constraint fk_usuario_topico_id foreign key (id_usuario) references usuario(id)
);

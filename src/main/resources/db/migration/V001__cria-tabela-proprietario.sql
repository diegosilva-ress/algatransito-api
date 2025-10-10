CREATE TABLE proprietario (
                              id bigint not null auto_increment,
                              nome varchar(60) not null ,
                              email varchar(255) not null ,
                              telefone varchar(20) not null ,
                              primary key (id)
);

ALTER TABLE proprietario
    ADD CONSTRAINT uk_proprietario_email UNIQUE (email);
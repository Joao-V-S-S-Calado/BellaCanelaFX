CREATE TABLE Funcionarios (
	fun_cod SERIAL,
	fun_nome varchar(50) NOT NULL,
	fun_idade INTEGER NOT NULL,
	fun_telefone varchar(11) NOT NULL,
	fun_salario decimal(10,2),
	CONSTRAINT pk_fun PRIMARY KEY (fun_cod)
);

CREATE TABLE Usuarios (
	user_login varchar(16),
	user_senha varchar(32),
	CONSTRAINT pk_usuarios PRIMARY KEY (user_login)
);

create table cliente(
    cli_cod SERIAL,
    cli_nome varchar(50) not null,
    cli_cpf varchar(14) not null,
    cli_email varchar(30) not null,
    cli_fone varchar(14) not null,
    constraint pk_cli primary key (cli_cod)
);

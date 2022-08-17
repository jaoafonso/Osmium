CREATE DATABASE osmium;
USE osmium;

CREATE TABLE usuario (
	id_usuario INTEGER(9) AUTO_INCREMENT,
    nome_usuario VARCHAR(20),
    email_usuario VARCHAR(256),
    senha_usuario VARCHAR(128),
    desc_usuario VARCHAR(190),
    idade_usuario INTEGER(2),
    foto_usuario INTEGER(50),
    banner_usuario INTEGER(50),
    tema_escuro BOOLEAN,
    administrador BOOLEAN,
    PRIMARY KEY(id_usuario),
    UNIQUE(nome_usuario)
);

CREATE TABLE plataformas_jogadas (
	id_usuario INTEGER(9),
    plataforma VARCHAR(20),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE categoria_de_jogo (
	id_categoria INTEGER(9) AUTO_INCREMENT,
    nome_categoria VARCHAR(45),
    PRIMARY KEY(id_categoria)
);

CREATE TABLE jogos (
	id_jogo INTEGER(9) AUTO_INCREMENT,
    nome_jogo VARCHAR(45),
    desc_jogo VARCHAR(300),
    PRIMARY KEY(id_jogo)
);

CREATE TABLE categorias_do_jogo (
	id_jogo INTEGER(9),
    id_categoria INTEGER(9),
    FOREIGN KEY(id_jogo) REFERENCES jogos(id_jogo),
    FOREIGN KEY(id_categoria) REFERENCES categoria_de_jogo(id_categoria)
);

CREATE TABLE interesses_do_usuario (
	id_usuario INTEGER(9),
    id_categoria INTEGER(9),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY(id_categoria) REFERENCES categoria_de_jogo(id_categoria)
);

CREATE TABLE jogos_jogados_pelo_usuario (
	id_usuario INTEGER(9),
    id_jogo INTEGER(9),
    carac_jogabilidade VARCHAR(45),
    favorito BOOLEAN,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY(id_jogo) REFERENCES jogos(id_jogo)
);

#Categorias de Jogos
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Ação");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Arcade");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Luta");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Aventura");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Casual");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Quebra-Cabeça");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("RPG");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Construção");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Estratégia");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Esportes");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("FPS");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Corrida");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Anime");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Ficção Científica");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Mundo Aberto");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Sobrevivência");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Terror");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Multijogador");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Um Jogador");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Indie");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Ritmo");
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("2D");

#Alguns jogos (Futuramente disponibilizar um acesso de Admin via Netbeans para adidionar novos jogos)
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Marvel’s Spider-Man Remastered", "Em Marvel's Spider-Man Remasterizado, os mundos de Peter Parker e Spider-Man entram em conflito em uma história original cheia de ação. Jogue como um Peter Parker experiente que combate as maiores ameaças do crime e vilões icônicos na Nova York da Marvel.");
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 14);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 19);

INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Geometry Dash", "Salte e voe pelo perigo neste jogo de plataforma de ação baseado em ritmo!");
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 2);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 5);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 20);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 21);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 22);

INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Mortal Kombat X", "Experimente a próxima geração da maior franquia de jogos de luta. Mortal Kombat X combina uma apresentação cinematográfica sem igual com uma nova jogabilidade.");
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 3);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 22);

INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Stray", "Perdido, sozinho e separado da sua família, um gato de rua precisa desvendar um mistério ancestral para fugir de uma cibercidade esquecida e encontrar o caminho para casa.");
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (4, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (4, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (4, 20);

INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Stumble Guys", "Corra por percursos de obstáculos contra até 32 jogadores on-line. Corra, pule e trombe até a linha de chegada para que o melhor jogador leve a coroa!");
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 5);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 20);

INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Portal 2", 'A "Iniciativa de Testes Perpétuos" foi expandida para permitir que cries puzzles co-op para ti e os teus amigos!');
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 6);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 14);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 19);

INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("ELDEN RING", "O NOVO RPG DE AÇÃO E FANTASIA. Levante-se, Maculado, e seja guiado pela graça para portar o poder do Anel Prístino e se tornar um Lorde Prístino nas Terras Intermédias.");
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 7);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 19);


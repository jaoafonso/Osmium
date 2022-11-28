CREATE DATABASE osmium;
USE osmium;

CREATE TABLE usuario (
	id_usuario INTEGER(9) AUTO_INCREMENT,
	nome_usuario VARCHAR(20),
	email_usuario VARCHAR(256),
	senha_usuario VARCHAR(128),
	desc_usuario VARCHAR(300),
	dataNasc_usuario DATE,
	foto_usuario INTEGER(2),
	administrador BOOLEAN,
	perfil_concluido BOOLEAN,
	PRIMARY KEY(id_usuario),
	UNIQUE(nome_usuario),
	UNIQUE(email_usuario)
);

CREATE TABLE mensagens (
	id_mensagem INTEGER(9) AUTO_INCREMENT,
	id_remetente INTEGER(9),
	id_destinatario INTEGER(9),
	mensagem VARCHAR(300),
	lida BOOLEAN,
	FOREIGN KEY(id_remetente) REFERENCES usuario(id_usuario),
	FOREIGN KEY(id_destinatario) REFERENCES usuario(id_usuario),
	PRIMARY KEY(id_mensagem)
);

CREATE TABLE plataformas (
	id_usuario INTEGER(9) UNIQUE,
	pc BOOLEAN,
	console BOOLEAN,
	mobile BOOLEAN,
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE categoria_de_jogo (
	id_categoria INTEGER(9) AUTO_INCREMENT,
	nome_categoria VARCHAR(45),
	PRIMARY KEY(id_categoria),
	UNIQUE(nome_categoria)
);

CREATE TABLE jogos (
	id_jogo INTEGER(9) AUTO_INCREMENT,
	nome_jogo VARCHAR(45),
	desc_jogo VARCHAR(300),
	PRIMARY KEY(id_jogo),
	UNIQUE(nome_jogo)
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

CREATE TABLE jogos_favoritos (
	id_usuario INTEGER(9),
	id_jogo INTEGER(9),
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY(id_jogo) REFERENCES jogos(id_jogo)
);

CREATE TABLE convites (
	id_convite INTEGER(9) AUTO_INCREMENT,
	remetente INTEGER(9),
	destinatario INTEGER(9),
	id_jogo INTEGER(9),
	FOREIGN KEY(remetente) REFERENCES usuario(id_usuario),
	FOREIGN KEY(destinatario) REFERENCES usuario(id_usuario),
	FOREIGN KEY(id_jogo) REFERENCES jogos(id_jogo),
	PRIMARY KEY(id_convite)
);

CREATE TABLE seguidores (
	id_interacao INTEGER(9) AUTO_INCREMENT,
	id_usuario INTEGER(9),
	id_seguidor INTEGER(9),
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY(id_seguidor) REFERENCES usuario(id_usuario),
	PRIMARY KEY(id_interacao)
);

CREATE TABLE publicacoes (
	id_publicacao INTEGER(9) AUTO_INCREMENT,
	id_usuario INTEGER(9),
	assunto VARCHAR(40),
	titulo VARCHAR(40),
	descricao VARCHAR(300),
	FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario),
	PRIMARY KEY(id_publicacao)
);

#Administradores
INSERT INTO usuario VALUES (1, "admin", "admin@gmail.com", "admin", "admin", "2001-01-01", 21, true, false);

#Usuários para Testes
INSERT INTO usuario VALUES (2, "user", "user@gmail.com", "user", "Lorem ipsum facilisis curabitur quisque gravida vestibulum nulla scelerisque, fames hac lacinia taciti hendrerit class lobortis quisque tempor, nibh ante sociosqu phasellus mauris purus egestas. est velit potenti in dapibus non, rhoncus hendrerit mauris ligula mollis, platea torquent et sed.", "2001-01-01", 22, false, false);
INSERT INTO usuario VALUES (3, "jorge", "jorge@gmail.com", "jorge", "Lorem ipsum facilisis curabitur quisque gravida vestibulum nulla scelerisque, fames hac lacinia taciti hendrerit class lobortis quisque tempor, nibh ante sociosqu phasellus mauris purus egestas. est velit potenti in dapibus non, rhoncus hendrerit mauris ligula mollis, platea torquent et sed.", "2001-01-01", 1, false, false);
INSERT INTO usuario VALUES (4, "joao3003", "joao@gmail.com", "joao3003", "Lorem ipsum facilisis curabitur quisque gravida vestibulum nulla scelerisque, fames hac lacinia taciti hendrerit class lobortis quisque tempor, nibh ante sociosqu phasellus mauris purus egestas. est velit potenti in dapibus non, rhoncus hendrerit mauris ligula mollis, platea torquent et sed.", "2001-01-01", 2, false, false);

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
INSERT INTO categoria_de_jogo (nome_categoria) VALUES ("Tiro");
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
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Geometry Dash", "Salte e voe pelo perigo neste jogo de plataforma de ação baseado em ritmo!");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Mortal Kombat X", "Experimente a próxima geração da maior franquia de jogos de luta. Mortal Kombat X combina uma apresentação cinematográfica sem igual com uma nova jogabilidade.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Stray", "Perdido, sozinho e separado da sua família, um gato de rua precisa desvendar um mistério ancestral para fugir de uma cibercidade esquecida e encontrar o caminho para casa.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Stumble Guys", "Corra por percursos de obstáculos contra até 32 jogadores on-line. Corra, pule e trombe até a linha de chegada para que o melhor jogador leve a coroa!");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Portal 2", 'A "Iniciativa de Testes Perpétuos" foi expandida para permitir que cries puzzles co-op para ti e os teus amigos!');
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("ELDEN RING", "O NOVO RPG DE AÇÃO E FANTASIA. Levante-se, Maculado, e seja guiado pela graça para portar o poder do Anel Prístino e se tornar um Lorde Prístino nas Terras Intermédias.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Minecraft", "Minecraft é um jogo eletrônico, que tem por objetivo básico construir e quebrar blocos.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("DOTA 2", "Diariamente, milhões de jogadores mundo afora batalham como um dentre os mais de cem heróis do Dota. Estejam jogando há 10 ou 1.000 horas, há sempre algo novo para descobrir. Com atualizações constantes das mecânicas, recursos e heróis, o Dota 2 se tornou mais que um simples jogo.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("FIFA 22", "Powered by Football™, EA SPORTS™ FIFA 22 deixa o jogo ainda mais real com avanços fundamentais de jogabilidade e uma nova temporada de inovações em todos os modos.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Counter-Strike: Global Offensive", "O Counter-Strike: Global Offensive (CS:GO) melhora a jogabilidade de ação baseada em equipes na qual foi pioneiro quando lançado há 19 anos. O CS:GO contém novos mapas, personagens e armas, além de contar com versões atualizadas de conteúdos do CS clássico (como de_dust2).");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Forza Horizon 5", "Sua maior aventura Horizon te espera! Explore o mundo aberto vibrante e em constante evolução nas terras mexicanas. Participe de corridas divertidas e sem limites enquanto pilota centenas dos melhores carros do mundo.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("NARUTO SHIPPUDEN: Ultimate Ninja STORM 4", "O mais recente capítulo da aclamada série STORM leva você a uma viagem de tirar o fôlego e cheia de cores! Tire proveito do sistema de batalha totalmente renovado e prepare-se para mergulhar nas lutas mais épicas que você já viu!");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Cyberpunk 2077", "Cyberpunk 2077 é um RPG de ação e aventura em mundo aberto que se passa em Night City, uma megalópole perigosa onde todos são obcecados por poder, glamour e alterações corporais.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Grand Theft Auto V", "Grand Theft Auto V para PC oferece aos jogadores a opção de explorar o gigantesco e premiado mundo de Los Santos e Blaine County em resoluções de até 4K e além, assim como a chance de experimentar o jogo rodando a 60 FPS (quadros por segundo).");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("No Man’s Sky", "No Man’s Sky é um jogo de ficção científica de exploração e sobrevivência num universo com geração processual infinita.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Dead by Daylight", "Dead by Daylight é um jogo de terror multiplayer (4vs1) onde um jogador assume o papel do assassino selvagem, e os outros quatro jogadores jogam como sobreviventes, tentando escapar do assassino e evitar ser pego e morto.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("PUBG: BATTLEGROUNDS", "Jogue PUBG: BATTLEGROUNDS de graça. Pouse em locais estratégicos, saqueie armas e suprimentos e sobreviva para se tornar a última equipe de pé nos mais variados campos de batalha.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("The Witcher® 3: Wild Hunt", "Enquanto a guerra assola todos os Reinos do Norte, você enfrenta o maior conflito de sua vida: ir em busca da criança da profecia, uma arma senciente capaz de alterar o mundo.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Hollow Knight", "Forje seu caminho em Hollow Knight! Uma aventura de ação épica em um vasto reino arruinado de insetos e heróis. Explore cavernas serpenteantes, lute contra criaturas malignas e alie-se a insetos bizarros num estilo clássico 2D desenhado à mão.");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Osu!", "O ritmo está a apenas um *clique* de distância!");
INSERT INTO jogos (nome_jogo, desc_jogo) VALUES ("Terraria", "Cave, lute, explore, construa! Nada é impossível neste jogo de aventura cheio de ação.");

#Categorias Marvel’s Spider-Man Remastered
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 14);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (1, 19);

#Categorias Geometry Dash
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 2);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 5);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 20);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 21);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (2, 22);

#Categorias Mortal Kombat X
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 3);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (3, 22);

#Categorias Stray
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (4, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (4, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (4, 20);

#Categorias Stumble Guys
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 5);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (5, 20);

#Categorias Portal 2
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 6);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 14);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (6, 19);

#Categorias ELDEN RING
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 7);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (7, 19);

#Categorias Minecraft
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (8, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (8, 8);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (8, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (8, 16);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (8, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (8, 19);

#Categorias DOTA 2
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (9, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (9, 9);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (9, 18);

#Categorias FIFA 22
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (10, 10);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (10, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (10, 19);

#Categorias Counter-Strike: Global Offensive
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (11, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (11, 11);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (11, 18);

#Categorias Forza Horizon 5
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (12, 12);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (12, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (12, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (12, 19);

#Categorias NARUTO SHIPPUDEN: Ultimate Ninja STORM 4
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 3);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 13);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (13, 19);

#Categorias Cyberpunk 2077
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (14, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (14, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (14, 7);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (14, 14);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (14, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (14, 19);

#Categorias Grand Theft Auto V
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 11);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 12);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (15, 19);

#Categorias No Man's Sky
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 14);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 16);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (16, 19);

#Categorias Dead by Daylight
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (17, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (17, 16);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (17, 17);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (17, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (17, 19);

#Categorias PUBG: BATTLEGROUNDS
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (18, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (18, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (18, 11);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (18, 16);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (18, 18);

#Categorias The Witcher® 3: Wild Hunt
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (19, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (19, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (19, 7);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (19, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (19, 19);

#Categorias Hollow Knight
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (20, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (20, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (20, 20);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (20, 22);

#Categorias Osu!
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (21, 13);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (21, 21);

#Categorias Terraria
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 1);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 4);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 7);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 8);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 15);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 16);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 18);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 19);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 20);
INSERT INTO categorias_do_jogo (id_jogo, id_categoria) VALUES (22, 22);

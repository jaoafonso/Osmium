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
	UNIQUE(nome_usuario)
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

#A seguir estão os scipts para popular o banco de dados para a realização de testes.

#Administradores
INSERT INTO usuario VALUES (1, "Admin", "admin@gmail.com", "admin", "admin", "2001-01-01", 21, true, false);

#Usuários para Testes (A maioria das descrições eu copiei de usuários aleatórios do Discord)
INSERT INTO usuario VALUES (2, "User", "user@gmail.com", "user1234", "Lorem ipsum facilisis curabitur quisque gravida vestibulum nulla scelerisque, fames hac lacinia taciti hendrerit class lobortis quisque tempor, nibh ante sociosqu phasellus mauris purus egestas. est velit potenti in dapibus non, rhoncus hendrerit mauris ligula mollis, platea torquent et sed.", "2001-01-01", 22, false, false);
INSERT INTO usuario VALUES (3, "Joao3003", "joao@gmail.com", "joao3003", "•17 anos •Criador do Osmium •Viciado em jogos de mundo aberto!", "2005-03-30", 1, false, false);
INSERT INTO usuario VALUES (4, "PedroLima", "pedrol@gmail.com", "pedrolima0710", "•lvl18 •Viciado em The Witcher •Futuro Dublador •Adoro café • Main Akali • Phonk e New Metal • Love is Wars •", "2004-07-23", 2, false, true); # Adicionar informações para concluir o perfil de todos
INSERT INTO usuario VALUES (5, "LuizMine7", "luiz7mine@gmail.com", "luiz1234", '"Sonhe e serás livre de espírito, lute e serás livre na vida."', "2001-05-16", 3, false, true);
INSERT INTO usuario VALUES (6, "xXLuizaXx", "luiza2022@gmail.com", "luizadomine", "Faça tudo para conseguir oque você quer! mesmo q ninguem acredite!!", "2009-01-18", 4, false, true);
INSERT INTO usuario VALUES (7, "Gabi2006", "gabi0710@gmail.com", "20081234", "Você não precisa saber sobre mim, precisa?", "2008-12-30", 5, false, true);
INSERT INTO usuario VALUES (8, "MineBoy69", "minejohn@gmail.com", "0911mine", "ISTJ | Sp •religião: Felinos Supremacy •pronomes: Emo/Trevas •idade: depende da sua •Estado: Morto.   Faço miojo gostoso :)", "2001-09-11", 6, false, true);
INSERT INTO usuario VALUES (9, "HenrGuedes1", "henr7294@gmail.com", "henrique1234", "Bora jogar Apeirophobia no Bobox?", "1998-11-06", 7, false, true);
INSERT INTO usuario VALUES (10, "TonhaoMatador", "tonhaomatadordeporco@gmail.com", "antony2002", "Dono atual da maior comunidade brasileira de Minecraft no Discord", "2002-08-01", 8, false, true);
INSERT INTO usuario VALUES (11, "Creeper13", "creeperbot12@gmail.com", "minefan123", "He/him; Futuro programador e designer; Hm...não sei o que colocar aqui...", "2000-01-07", 9, false, true);
INSERT INTO usuario VALUES (12, "Paulista54", "paulista627@gmail.com", "pietro123g", "Nunca é tarde para recomeços, pior que errar, é não querer mudar.", "2001-02-21", 10, false, true);
INSERT INTO usuario VALUES (13, "EmillyCSGO", "emilly91783@gmail.com", "millymendes123", "Jogo lol, desenho, assisto anime e escuto rock clássico - Shitpost é a oitava maravilha do mundo", "2007-04-28", 11, false, true);
INSERT INTO usuario VALUES (14, "Candango420", "candango420@gmail.com", "robertocand", "•18yo •valorant, cs, roblox •listen to The Symposium", "1967-06-21", 12, false, true);
INSERT INTO usuario VALUES (15, "JulioTCS", "juliogames12@gmail.com", "juliodatcs", "Desenhista. Toco piano, ukulele e guitarra. Jogo lolzinho, osu e mine. ", "2010-05-27", 13, false, true);
INSERT INTO usuario VALUES (16, "MarceloHS", "hsmaster@gmail.com", "hsmaster12", "a verdadeira felicidade se esconde na virtude e determinaçao da sua vida, acredite em vc pois esse mundo e seu!!", "2006-09-20", 14, false, true);
INSERT INTO usuario VALUES (17, "GaaraMINE", "gaaraminecrafter@gmail.com", "gaaraenart", "20y. Estudante de Ciências Biológicas. Fanático por Zoologia, Etologia e Paleontologia", "1999-10-10", 15, false, true);
INSERT INTO usuario VALUES (18, "MCSD2007", "marcelosd@gmail.com", "2007hehe", "Abra suas asas, voe! Antes que o mal ressoe. Em torno de cada palavra cruel da minha boca só ditas para te machucar.", "2002-11-05", 16, false, true);
INSERT INTO usuario VALUES (19, "CassiaPDF", "cassiapdf@gmail.com", "suvfdp123", "| Definitivamente GPO é meu jogo favorito | Vencer todas não dá, mas aparentemente perder todas dá sim", "2007-07-27", 17, false, true);
INSERT INTO usuario VALUES (20, "GilMendes", "gilbertomendes@gmail.com", "supm8721", "Desenhista amador. Toco Violão. Desgustador de queijos profissional. Beatlemaniaco di cria :)", "2001-10-12", 18, false, true);
INSERT INTO usuario VALUES (21, "EndermanAF", "robertao12@gmail.com", "afgovs21", "•16 years  •Morrinhos-GO  •veteran rhythm gamer", "2008-11-10", 19, false, true);
INSERT INTO usuario VALUES (22, "Bezin", "bernardo@gmail.com", "bernardo123", "•17y •Streamer •Futuro gerenciador de crises da PMMG •Acima de tudo o amor pela Furia ", "2001-12-06", 20, false, true);

#Seguidores de Joao3003
INSERT INTO seguidores (id_usuario, id_seguidor) VALUES (3, 4);
INSERT INTO seguidores (id_usuario, id_seguidor) VALUES (3, 5);
INSERT INTO seguidores (id_usuario, id_seguidor) VALUES (3, 6);
INSERT INTO seguidores (id_usuario, id_seguidor) VALUES (3, 7);
INSERT INTO seguidores (id_usuario, id_seguidor) VALUES (3, 8);

#Seguidos por Joao3003
INSERT INTO seguidores (id_seguidor, id_usuario) VALUES (3, 4);
INSERT INTO seguidores (id_seguidor, id_usuario) VALUES (3, 5);
INSERT INTO seguidores (id_seguidor, id_usuario) VALUES (3, 6);
INSERT INTO seguidores (id_seguidor, id_usuario) VALUES (3, 7);
INSERT INTO seguidores (id_seguidor, id_usuario) VALUES (3, 8);

#Mensagens para Joao3003
INSERT INTO mensagens (id_destinatario, id_remetente, mensagem) VALUES (3, 4, "Oii Joao3003, vi sua publicação sobre 'Me indiquem jogos de pc', minhas indicações são 'Osu!' e 'Minecraft', são otimos jogos para jogar no tempo livre.");
INSERT INTO mensagens (id_destinatario, id_remetente, mensagem) VALUES (3, 5, "Oi João, vamos jogar minecraft hoje mais tarde? Ja fiz vários avanços no nosso mundo");
INSERT INTO mensagens (id_destinatario, id_remetente, mensagem) VALUES (3, 6, "Quais jogos você esta jogando atualmente, João?");
INSERT INTO mensagens (id_destinatario, id_remetente, mensagem) VALUES (3, 21, "Vamos jogar Valorant em call no Discord?");
INSERT INTO mensagens (id_destinatario, id_remetente, mensagem) VALUES (3, 22, "Oi Joao3003, vi que você se interessa por Minecraft, esse também é meu jogo favorito atualmente, haha.");

#Publicações (Algumas são publicações copiadas do Reddit)
INSERT INTO publicacoes (id_usuario, assunto, titulo, descricao) VALUES (3, "Ajuda", "Me indiquem jogos de pc", "ganhei um notebook gamer no trabalho, to animadasso, queria indicações pra quem joga poucas horas por dia. fui viciado em Wow e Lol durante a adolescência inteira, mas hoje tenho pouco horário livre pra jogar e esses jogos demandam tempo demais.");
INSERT INTO publicacoes (id_usuario, assunto, titulo, descricao) VALUES (4, "Ajuda", "Rola de comprar mídia secundária no ps4?", "Queria jogar Sekiro, mas meu leitor de disco está estragado, entao nao dá pra arranjar e revender depois. Estou querendo arranjar jogos atraves de aluguel de mídia secundaria, mas nao sei se vale a pena...opiniões por favor.");
INSERT INTO publicacoes (id_usuario, assunto, titulo, descricao) VALUES (5, "Convite", "Alguém quer jogar umas partidas no R6?", "Alguém p/ jogar algumas partidas rápidas no rainbow six? Sou ruim, acabei de sair do recém chegado, se alguém não for muito habilidoso (assim como eu kkk) e se quiser jogar só pra divertir mesmo me manda mensagem.");
INSERT INTO publicacoes (id_usuario, assunto, titulo, descricao) VALUES (6, "Conquista", "Conquista mais difícil do Minecraft!", "Depois de 10 horas juntando os materiais necessários, consegui a conquista 'A que ponto chegamos?' no Minecraft! Tive todos os efeitos aplicados ao mesmo tempo no meu personagem.");
INSERT INTO publicacoes (id_usuario, assunto, titulo, descricao) VALUES (7, "Informação", "Encontrando ilhas flutuantes no Terraria", "Ilhas Flutuantes e Lagos Flutuantes são estruturas geradas geralmente muito acima da terra e podem ser encontradas por todo o caminho até o Espaço. A localização delas as faz suscetíveis a Harpias no Pré-Modo Difícil e Serpes e Arqui-serpe no Modo Difícil.");

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

#Alguns jogos
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

#Interesses dos Usuários

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (4, 6);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (4, 7);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (4, 8);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (4, 9);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (4, 10);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (5, 11);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (5, 12);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (5, 13);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (5, 14);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (5, 15);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (6, 16);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (6, 17);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (6, 18);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (6, 19);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (6, 20);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (7, 1);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (7, 2);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (7, 3);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (7, 4);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (7, 5);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (8, 6);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (8, 7);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (8, 8);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (8, 9);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (8, 10);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (9, 11);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (9, 12);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (9, 13);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (9, 14);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (9, 15);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (10, 16);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (10, 17);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (10, 18);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (10, 19);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (10, 20);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (11, 1);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (11, 2);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (11, 3);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (11, 4);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (11, 5);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (12, 6);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (12, 7);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (12, 8);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (12, 9);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (12, 10);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (13, 11);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (13, 12);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (13, 13);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (13, 14);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (13, 15);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (14, 16);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (14, 17);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (14, 18);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (14, 19);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (14, 20);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (15, 1);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (15, 2);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (15, 3);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (15, 4);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (15, 5);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (16, 6);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (16, 7);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (16, 8);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (16, 9);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (16, 10);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (17, 11);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (17, 12);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (17, 13);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (17, 14);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (17, 15);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (18, 16);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (18, 17);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (18, 18);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (18, 19);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (18, 20);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (19, 1);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (19, 2);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (19, 3);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (19, 4);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (19, 5);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (20, 6);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (20, 7);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (20, 8);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (20, 9);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (20, 10);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (21, 11);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (21, 12);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (21, 13);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (21, 14);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (21, 15);

INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (22, 16);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (22, 17);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (22, 18);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (22, 19);
INSERT INTO interesses_do_usuario (id_usuario, id_categoria) VALUES (22, 20);

#Jogos Favoritos dos Usuários
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (4, 6);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (4, 7);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (4, 8);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (4, 9);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (4, 10);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (5, 11);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (5, 12);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (5, 13);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (5, 14);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (5, 15);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (6, 16);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (6, 17);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (6, 18);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (6, 19);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (6, 22);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (7, 1);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (7, 2);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (7, 3);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (7, 4);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (7, 21);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (8, 6);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (8, 7);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (8, 8);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (8, 9);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (8, 10);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (9, 11);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (9, 12);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (9, 22);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (9, 14);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (9, 15);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (10, 16);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (10, 17);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (10, 18);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (10, 19);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (10, 20);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (11, 1);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (11, 2);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (11, 3);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (11, 4);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (11, 5);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (12, 6);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (12, 7);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (12, 8);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (12, 22);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (12, 10);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (13, 11);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (13, 12);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (13, 13);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (13, 14);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (13, 21);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (14, 16);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (14, 17);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (14, 18);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (14, 19);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (14, 20);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (15, 1);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (15, 2);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (15, 3);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (15, 4);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (15, 21);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (16, 6);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (16, 7);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (16, 8);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (16, 9);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (16, 10);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (17, 11);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (17, 12);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (17, 13);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (17, 14);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (17, 15);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (18, 16);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (18, 17);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (18, 18);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (18, 19);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (18, 20);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (19, 1);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (19, 2);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (19, 3);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (19, 4);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (19, 5);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (20, 6);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (20, 7);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (20, 8);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (20, 9);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (20, 10);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (21, 11);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (21, 12);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (21, 13);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (21, 14);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (21, 15);

INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (22, 16);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (22, 17);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (22, 18);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (22, 19);
INSERT INTO jogos_favoritos (id_usuario, id_jogo) VALUES (22, 20);

#Plataformas jogadas pelos Usuários
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (4, 1, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (5, 1, 0, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (6, 0, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (7, 0, 1, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (8, 0, 0, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (9, 1, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (10, 0, 1, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (11, 0, 0, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (12, 1, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (13, 1, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (14, 1, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (15, 1, 1, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (16, 0, 1, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (17, 0, 0, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (18, 1, 0, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (19, 1, 0, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (20, 0, 1, 0);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (21, 0, 1, 1);
INSERT INTO plataformas (id_usuario, pc, console, mobile) VALUES (22, 0, 0, 1);

#Convites para Joao3003
INSERT INTO convites (destinatario, remetente, id_jogo) VALUES (3, 4, 8);
INSERT INTO convites (destinatario, remetente, id_jogo) VALUES (3, 5, 7);
INSERT INTO convites (destinatario, remetente, id_jogo) VALUES (3, 6, 6);
INSERT INTO convites (destinatario, remetente, id_jogo) VALUES (3, 7, 5);
INSERT INTO convites (destinatario, remetente, id_jogo) VALUES (3, 8, 4);

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
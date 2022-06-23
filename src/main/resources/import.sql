
-- Insert para usuários utilizados no perfil de testes.
insert into usuario(id, nome, email, username_github, senha) values(1, 'Felipe Amorim', 'felipeamorim.dev@gmail.com', 'felipeamorim-dev', '123');
insert into carteira(id, saldo, id_usuario) values(1, 500.0, 1);
insert into carrinho(id_usuario) values(1);

update usuario set id_carrinho = 1 where usuario.id = 1

-- Insert para os produtos utilizados no perfil de testes.
insert into produtos(id, nome, descricao, preco) values(1, 'Xbox One S', 'Console da Microsoft', 1200.0);
insert into produtos(id, nome, descricao, preco) values(2, 'Xbox One X', 'Console da Microsoft', 3200.0);
insert into produtos(id, nome, descricao, preco) values(3, 'Xbox Series S', 'Console da Microsoft', 3000.0);

-- Iserte de itens no carrinho de compras
insert into items(id, preco_unitario, quantidade, produto_id) values(1, 3200, 1, 2);
insert into item_carrinho(carrinho_id, item_id) values(1, 1);


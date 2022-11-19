
-- Insert para usuários utilizados no perfil de testes.
insert into usuario(id, nome, email, username_github, senha, url_image_perfil) values(1, 'Felipe Amorim', 'felipeamorim.dev@gmail.com', 'felipeamorim-dev', '$2a$12$pNjV.n4.NtlKf5JL6LSWbeESMXKGQyX9k1pbUXa0yiUlPTuvJuztG', 'https://avatars.githubusercontent.com/u/49344697?v=4');
insert into perfis(usuario_id, perfis) values (1, 0);
insert into carteira(id, saldo, id_usuario) values(1, 1300.0, 1);
insert into carrinho(id_usuario) values(1);

update usuario set id_carrinho = 1 where usuario.id = 1

-- Insert para os produtos utilizados no perfil de testes.
insert into produtos(nome, descricao, preco, url_image) values('Xbox Series S', 'Console da Microsoft de Nova Geração', 1700.0, 'https://i5.walmartimages.com/asr/b4e726b8-5c6c-4cfc-b564-cebd00b00585.0de41b3c1706bbcbba90a40d6bd98b3a.jpeg');
insert into produtos(nome, descricao, preco, url_image) values('Xbox Series X', 'Console da Microsoft de Nova Geração', 4700.0, 'https://sm.pcmag.com/t/pcmag_il/photo/default/54d4e049-3167-4700-ac7e-b6e3ab683657_wn3d.1200.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Xbox One S', 'Console da Microsoft', 1200.0, 'https://imgix.bustle.com/inverse/e9/49/93/c2/c11c/46be/a3f4/31bd7ef05db5/screen-shot-2019-04-17-at-63523-pmpng.png?w=1200&h=630&q=70&fit=crop&crop=faces&fm=jpg');
insert into produtos(nome, descricao, preco, url_image) values('Headset Havit H2002d', 'Headset Gamer', 250.0, 'https://static3.tcdn.com.br/img/img_prod/670412/fone_de_ouvido_headset_gamer_havit_h2002d_microfone_removivel_3683_2_20201027182540.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Headset HyperX Cloud Alpha HX-HSCARD', 'Headset Gamer', 350.0, 'https://images.tcdn.com.br/img/img_prod/487288/fone_de_ouvido_headset_gamer_hyperx_cloud_alpha_hx_hsca_rd_5117_1_20191219162052.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Teclado Mecânico Redragon Lakshmi k606R', 'Teclado Mecânico', 320.0, 'https://cdn.awsli.com.br/1000x1000/1318/1318167/produto/101719298/36cf1b42d6.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Teclado Mecânico GamaKay k66', 'Teclado Mecânico', 450.0, 'https://imgaz.staticbg.com/images/oaupload/banggood/images/64/C4/839f34f1-e92f-4404-9bbd-c97d6539791e.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Monitor Dell SE2419HR 24"', 'Monitor Full HD', 1200.0, 'https://teracomputers.com/wp-content/uploads/2020/12/812L6oSmmxL._AC_SL1500_.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Monitor LG Ultrawide Um58-p 25"', 'Monitor Ultrawide', 900.0, 'https://http2.mlstatic.com/monitor-led-ips-lg-ultrawide-25-um58-pawz-D_NQ_NP_719177-MLB31029700692_062019-F.jpg');
insert into produtos(nome, descricao, preco, url_image) values('Notebook Gamer Acer Nitro 5', 'Notebook', 5500.0, 'https://ib.canaltech.com.br/20753.jpeg');
insert into produtos(nome, descricao, preco, url_image) values('Notebook Gamer Lenovo Ideapad Gaming 3', 'Notebook', 6600.0, 'https://lojaibyte.vteximg.com.br/arquivos/ids/203211-1200-1200/notebook-gamer-lenovo-ideapad-gaming-3-intel-core-i7-10750h-8gb-256gb-ssd-gtx-1650-4gb-15-6-full-hd-windows-10-home-azul-2.jpg?v=637330196824300000');
insert into produtos(nome, descricao, preco, url_image) values('Notebook Dell Inspiron 5000', 'Notebook', 4300.0, 'https://d3qoj2c6mu9s8x.cloudfront.net/Custom/Content/Products/21/04/21041_notebook-dell-inspiron-5000-5667-branco-15-6-8gb-1tb-windows-10-intel-core-e-amd-radeon-r7-m445_z5_636427921264784649.jpg');

-- Inserte de itens no carrinho de compras
--insert into items(id, preco_unitario, quantidade, produto_id) values(1, 4700, 1, 2);
--insert into item_carrinho(carrinho_id, item_id) values(1, 1);


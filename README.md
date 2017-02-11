Universidade Federal de Campina Grande
Centro de Engenharia Elétrica e Informática
Departamento de Sistemas e Computação

Disciplina: Laboratório de Programação 2 – 2016.1

Relatório do Projeto Hotel Urbano de Gotemburgo (HUG)

Professor : Matheus Gaudêncio

Equipe:  Dales Ewerton 
	       Tarsila Souza 
	       Thaynnara Raiany
	       Paulo Vitor


Introdução 

	Esse projeto foi realizado como parte das atividades realizadas na disciplina Labóratorio de Programação 2 do curso de Ciência da Computação da Universidade Federal de Campina Grande, sendo esse o passo final da disciplina, tinha como um dos objetivos a aplicação de todos os conhecimentos adquiridos ao longo do semestre.

Especificação do Projeto
	
	O projeto especificava que deveria ser implementado um sistema informatizado para o Hotel  Urbano de Gotemburgo (HUG). As responsabilidades do sistema foram dividas em nove casos de uso, que terão suas implementações explicadas a seguir.

Caso de uso 1 : CRUD de Hospede.

	Para o caso de uso 1 foram criadas uma interface de hospede e uma classe que implementa essa interface, a ideia da criação de interfaces no projeto é de garantir que elas sejam o meio de conexão entre as classes que controlam o sistema (Controllers) e as classes que estavam em um nível mais profundo do sistema, protegendo melhor o sistema.

Caso de uso 2: Checkin de hóspedes
	
	Para que hóspedes pudessem se cadastrar no hotel e ocupar estádias primeiramente foram necessárias a criação de classes que representassem os quartos e as estádias do hotel. Os quartos possuem um ID que o identifica, o preço de sua diária e um tipo, que pode ser Simples, Luxo ou Presidencial, também foi criado um enum para representar os valores constantes desse tipo de quarto. As estadias por sua vez, são representadas por uma classe que relaciona um quarto especifico de hotel a quantidade de dias que esse quarto ficará ocupado, a estádia representada através de uma classe foi criada para não possibilitar a criação de estadias com o mesmo quarto. 
	Tendo toda a base pronta, criamos a classe HotelController para realizar o cadastro de hospedes e seu checkin no hotel. O checkin relaciona um hospede a suas estadias, para isso utilizamos a coleção HashMap para relacionar uma chave, do tipo Hóspede, a um valor, que continha uma lista de estadias. Esse mapa definia quais hospedes estavam hospedados no hotel, removendo sempre aqueles que não possuírem estadias ativas, contudo, os dados dos mesmos permanecem armazenados na lista de Hóspedes cadastrados no hotel.

Caso de uso 3: Checkout de hóspedes e histórico de lucros.

	Para realizar o checkout de hóspedes implementamos um método no HotelController para realizar essa operação, esse método remove a estadia desejada e retorna o valor que deve ser pago pela Hóspede. Para guardar o histórico de tais transações, criamos uma classe chamada Transação, que representa cada transação feita no Hotel, e antes de excluir a hospedagem da lista de hospedagens guardamos o nome do hóspede que realizou a transação, a data, o total pago e o ID do quarto ocupado em um objeto dessa classe e o adicionamos a uma lista que guarda todas as transações realizadas no hotel.

Caso de uso 4: Restaurante do Hotel;

	Como base para o restaurante do hotel criamos as classes Prato e Refeição. Um prato possui um nome, um preço e uma descrição. Uma refeição por sua vez possui um nome, um preço, uma descrição e uma lista de pratos que a compõe. Com a base pronta, criamos a classe CardapioController, que guarda uma coleção de pratos e refeições que o restaurante possui e é responsável por cadastrar novos pratos e refeições de maneira correta e por repassar as informações referentes aos pratos, as refeições e ao cardápio do restaurante aos clientes.

Caso de uso 5: Organização do cardápio e pedidos.

	Nesse passo foi necessária a criação de uma nova classe que representasse tanto pratos quanto refeições para que ambos pudessem ser comparaveis e assim ordenaveis para serem apresentados no menu do restaurante. Criamos então a classe Menu, que possui um nome e uma descrição além de um método abstrado que calcula seu preço. A partir desse passo, as classes Refeição e Prato passaram a herdar a classe Menu e implementar o seu método abstrato que calcula o preço o item do Menu, seja ele prato ou refeição. Isso possibilitou que os itens do menu, pratos e refeições, pudessem ser ordenados juntos por nome ou preço.
	Nesse passo também foi criado o método que realiza pedidos de hóspedes específicos no HotelController, esse método informa o preço de um prato ou refeição pedido por um determinado hóspede para que seja realizada a cobrança, cria um objeto da classe Transação que guarda o nome do hóspede que realizou o pedido, o total gasto, a data da transação e os detalhes sobre o prato ou refeição pedida, depois salva esse objeto criado na lista de transações do Hotel.

Caso de uso 6: Cartão e Pontos de Fidelidade.

	Para esse caso de uso, primeiramente, foi criada a classe CartaoDeFidelidade, que encapsula a quantidade de pontos que um cartão possui e seu tipo, que pode ser Padrão, Premium ou Vip. Cada tipo tem formas de adicionar novos pontos e descontos diferentes, por isso e para que o upgrade e downgrade do tipo de cartão fosse feito de forma automática, decidimos utilizar o padrão Strategy, sendo assim, foi criada uma interface chamada TipoDeCartaoIF, e essa interface foi implementada pelas classes Padrão, Premium e Vip. Foi adicionado a classe Hóspede o atributo cartão de fidelidade e os novos métodos disponibilizados pelo cartão e nos métodos que realizam transações no HotelController foi adicionada funcionalidade que calcula o desconto do hóspede e realiza a adição de pontos no cartão do mesmo.

Caso de uso 7: Descontos e Benefícios da Fidelidade.

	Nesse caso tiramos proveito do Padrão Strategy já implementado para atribuir os benefícios e descontos específicos de cada cartão via chamadas polimórficas. Criamos então o método converte pontos na interface do tipo de cartão e implementamos os comportamentos específicos em cada tipo de cartão, depois, bastou fazer o cartão delegar a tarefa para o tipo de cartão.

Caso de uso 8: Geração de Relatórios do Sistema

	Foram criados os métodos SalvaHospedesEmTxt, SalvaTransaçoesEmTxt, SalvaResumoDoHotelEmTxt e EscreveNoArquivoDeResumoDeHotel na classe HotelController para criar arquivos de relatórios de Hóspedes, Transações e resumo do hotel, caso não existam ainda e então escrever os dados nos arquivos usando a classe BufferedWriter. Na classe CardapioController foi criado o método SalvaMenuEmTxt para criar um arquivo que salve o cardapio do restaurante e escrever o cardápio nele seguindo a ordenação atual..


Caso de uso 9: Persistencia do Sistma

	Nesse caso, optamos por salvar apenas o objeto de HotelController instanciado pela fachada para simplificar. Implementamos então o método fechaSistema para salvar o conteúdo desse objeto mque encapsula todos os outros objetos do sistema e o método iniciaSistema para ler e restaurar o conteúdo salvo de volta ao sistema.

Conclusão

	A participação e o desenvolvimento de um projeto, mesmo que para fins didáticos foi estimulante para os integrantes da equipe. Uma das maiores lições foi sobre a importância das boas práticas de programação facilitam a adição de novas funcionalidades ao sistema enquanto más práticas dificultam e muito a manutenção.
	No nosso ponto de vista, conseguimos aplicar a maior parte do conhecimento adquirido na disciplina.

Agradecimentos

	Gostaríamos de agradecer a todos os que participaram direta e indiretamente aos professores e monitores da disciplina que trabalharam para que a realização desse projeto fosse possível. Agradecemos em especial ao professor e orientador do projeto Matheus Gaudencio e ao monitor responsável pela equipe Hector Medeiros.
	Pedimos desculpas pelas eventuais gambiarras encontradas.

										     CoffeeCN Coders.

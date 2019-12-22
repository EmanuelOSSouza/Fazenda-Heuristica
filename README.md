# Fazenda Heuristica
###  Autor: [Emanuel Souza](https://github.com/EmanuelOSSouza) 
###  Autor: [Iarley Moraes](https://github.com/Rytokirin21)
###  Autor: Caio Luiz
###  Matéria: Inteligência Artificial
###  Busca heuristica - Fazenda
### Descrição:
O trabalho consiste em implementar um robô de inspeção (agente) capaz de locomover-se pela fazenda (representada pelo mapa ilustrado na Figura 1) e encontrar um ou mais gados (ponto de destino) de forma inteligente. Para isso, você deve utilizar o algoritmo de busca heurística A*. O agente deve ser capaz de calcular automaticamente a melhor rota. A melhor rota é a rota de menor custo levando em consideração o terreno. [Ver descritivo.](https://github.com/EmanuelOSSouza/Fazenda-Heuristica/blob/master/Descritivo.pdf)

Para isto foi utilizado o algoritmo de [busca em largura](https://pt.wikipedia.org/wiki/Busca_em_largura) (Breadth First Search) e o [algoritmo de Dijkstra](https://pt.wikipedia.org/wiki/Algoritmo_de_Dijkstra). O algoritmo A* tem como função: f(x) = g(n)+h(n), no qual "n" é o próximo nó no caminho, "g(n)" é o custo do caminho do nó inicial para "n" e "h(n)" é uma função heurística (neste caso, algoritmo de Dijkstra), que estima o custo do caminho mais barato de "n" para a meta. 

Para estes tipos de problemas existem diversos tipos de algoritmos, como por exemplo através do calculo da [distância euclidiana](https://pt.wikipedia.org/wiki/Dist%C3%A2ncia_euclidiana). Porém por este problema ter sido modelado em matriz, torna-se inviável. Mas existe outro algoritmo que funciona similar a euclidiana, modelado em matriz, que a priori resolveria este problema, que seria [Distância de Manhattan](https://pt.wikipedia.org/wiki/Geometria_pombalina). Porém este algoritmo não leva em consideração os "pesos", que são valores atribuídos em determinados pontos que caracteriza aos custos. Por estes motivos o algoritmo A* foi escolhido. 

Esta problematica se assemelha ao problema do caxeiro viajante (PCV), no qual é um problema que tenta determinar a menor rota para percorrer uma série de cidades (visitando uma única vez cada uma delas), retornando à cidade de origem. Neste caso, são caminhos para chegar até as vacas.

Portanto neste tipo de problema nem sempre se chega a uma resposta ótima. As vezes, no pior caso, chega em resultados aproximados. Por se tratar de soluções heuristicas. Por isso em alguns casos será notório "caminhos desnecessários" para chegar a tal resolução.
Notação assintotica de complexidade: O(bᵈ). 

### Funcionamento: 

> Para verificar a execução da aplicação deve-se importar para o NetBeans e executar a classe principal (MainFrameBase.java).

> Baseado no [mapa](https://github.com/EmanuelOSSouza/Fazenda-Heuristica/blob/master/Descritivo.pdf), deve-se escolher a posição (X e Y) que contém grama (na cor verde) e clicar em iniciar.

> Ao longo da execução e chegada ao destino (as posições das vacas), será mostrado o custo total (g(n)+h(n)), novas janelas serão instanciadas e reiniciando o algoritmo de busca em lagrura(exibido através dos "@") e o algoritmo de Dijkstra (exibido através de "#").

> Todos os locais de recomeço ficara com a marcar vermelha.   

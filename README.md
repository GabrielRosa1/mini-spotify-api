# 🎧 Mini Spotify API

API REST desenvolvida com **Spring Boot** para simular funcionalidades básicas de uma plataforma de streaming de música, inspirada no Spotify.

O projeto permite o gerenciamento de:

- Usuários
- Artistas
- Álbuns
- Músicas
- Playlists
- Estatísticas

## Ordem ideal para testar

1. Criar usuário
2. Criar artista
3. Criar álbum
4. Criar música
5. Criar playlist
6. Reproduzir música
7. Adicionar música à playlist
8. Buscar estatísticas
9. Buscar top músicas

Além do CRUD tradicional, a aplicação também implementa regras de negócio específicas, como reprodução de músicas, adição de músicas em playlists e relatório de músicas mais reproduzidas.

---

## 📌 Objetivo do Projeto

Desenvolver uma API REST em Java com Spring Boot, aplicando conceitos de:

- Modelagem de entidades
- Separação em camadas
- Regras de negócio
- Boas práticas REST
- Validações de entrada
- Exclusão lógica

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| **Java** | Linguagem principal |
| **Spring Boot** | Framework da aplicação |
| **Maven** | Gerenciador de dependências |
| **UUID** | Identificação das entidades |
| **HashMap** | Armazenamento em memória |

---

## 🧱 Estrutura do Projeto

A aplicação foi organizada em camadas:

- **Controller** — responsável por expor os endpoints da API
- **Service** — responsável pelas regras de negócio e manipulação dos dados

```
src/main/java/com/insper/mini_spotify_api
├── Album.java
├── AlbumController.java
├── AlbumService.java
├── Artista.java
├── ArtistaController.java
├── ArtistaService.java
├── Estatisticas.java
├── EstatisticasController.java
├── EstatisticasService.java
├── Musica.java
├── MusicaController.java
├── MusicaService.java
├── Playlist.java
├── PlaylistController.java
├── PlaylistService.java
├── Usuario.java
├── UsuarioController.java
├── UsuarioService.java
└── MiniSpotifyApiApplication.java
```

---

## 🗂 Entidades do Projeto

### 1. Usuário

Representa um usuário da plataforma.

| Atributo | Tipo |
|----------|------|
| `id` | UUID |
| `nome` | String |
| `email` | String |
| `tipoPlano` | ENUM: `FREE`, `PREMIUM` |
| `ativo` | boolean |
| `dataCriacao` | LocalDateTime |

### 2. Artista

Representa um artista ou banda.

| Atributo | Tipo |
|----------|------|
| `id` | UUID |
| `nome` | String |
| `generoMusical` | String |
| `paisOrigem` | String |
| `albuns` | List\<Album\> |
| `ativo` | boolean |

### 3. Álbum

Representa um álbum musical.

| Atributo | Tipo |
|----------|------|
| `id` | UUID |
| `titulo` | String |
| `dataLancamento` | LocalDate |
| `artista` | Artista |
| `musicas` | List\<Musica\> |
| `ativo` | boolean |

### 4. Música

Representa uma música disponível na plataforma.

| Atributo | Tipo |
|----------|------|
| `id` | UUID |
| `titulo` | String |
| `duracaoSegundos` | int |
| `numeroFaixa` | int |
| `album` | Album |
| `artista` | Artista |
| `totalReproducoes` | long |
| `ativo` | boolean |

### 5. Playlist

Representa uma playlist criada por um usuário.

| Atributo | Tipo |
|----------|------|
| `id` | UUID |
| `nome` | String |
| `publica` | Boolean |
| `dataCriacao` | LocalDateTime |
| `usuario` | Usuario |
| `musicas` | List\<Musica\> |
| `ativo` | boolean |

### 6. Estatísticas

Entidade adicional criada para representar o histórico e preferências de consumo musical de um usuário.

| Atributo | Tipo |
|----------|------|
| `usuario` | Usuario |
| `musicasReproduzidas` | Integer |
| `artistaFavorito` | Artista |
| `albumFavorito` | Album |
| `musicaFavorita` | Musica |
| `tempoReproducao` | Integer |

---

## 🔗 Relacionamentos

- Um **Artista** pode ter vários **Álbuns**
- Um **Álbum** pode ter várias **Músicas**
- Um **Usuário** pode ter várias **Playlists**
- Uma **Playlist** pode conter várias **Músicas**

> Como o projeto utiliza armazenamento em memória, esses relacionamentos são representados através de referências entre objetos.

---

## 🌐 Endpoints CRUD

### Usuários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/usuarios` | Criar usuário |
| `GET` | `/usuarios` | Listar usuários |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID |
| `PUT` | `/usuarios/{id}` | Atualizar usuário |
| `DELETE` | `/usuarios/{id}` | Excluir usuário (lógico) |
| `PUT` | `/usuarios/reativar/{id}` | Reativar usuário |

### Artistas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/artistas` | Criar artista |
| `GET` | `/artistas` | Listar artistas |
| `GET` | `/artistas/{id}` | Buscar artista por ID |
| `PUT` | `/artistas/{id}` | Atualizar artista |
| `DELETE` | `/artistas/{id}` | Excluir artista (lógico) |
| `PUT` | `/artistas/reativar/{id}` | Reativar artista |

### Álbuns

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/albuns` | Criar álbum |
| `GET` | `/albuns` | Listar álbuns |
| `GET` | `/albuns/{id}` | Buscar álbum por ID |
| `PUT` | `/albuns/{id}` | Atualizar álbum |
| `DELETE` | `/albuns/{id}` | Excluir álbum (lógico) |
| `PUT` | `/albuns/reativar/{id}` | Reativar álbum |

### Músicas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/musicas` | Criar música |
| `GET` | `/musicas` | Listar músicas |
| `GET` | `/musicas/{id}` | Buscar música por ID |
| `PUT` | `/musicas/{id}` | Atualizar música |
| `DELETE` | `/musicas/{id}` | Excluir música (lógico) |
| `PUT` | `/musicas/reativar/{id}` | Reativar música |

### Playlists

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/playlists` | Criar playlist |
| `GET` | `/playlists` | Listar playlists |
| `GET` | `/playlists/{id}` | Buscar playlist por ID |
| `PUT` | `/playlists/{id}` | Atualizar playlist |
| `DELETE` | `/playlists/{id}` | Excluir playlist (lógico) |
| `PUT` | `/playlists/reativar/{id}` | Reativar playlist |

### Estatísticas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/usuarios/{usuarioId}/estatisticas` | Criar estatísticas |
| `GET` | `/usuarios/{usuarioId}/estatisticas` | Buscar estatísticas |
| `PUT` | `/usuarios/{usuarioId}/estatisticas` | Atualizar estatísticas |
| `DELETE` | `/usuarios/{usuarioId}/estatisticas` | Excluir estatísticas |

---

## 🚀 Regras de Negócio Implementadas

### 1. Reproduzir Música

```http
POST /musicas/{id}/reproduzir
X-USER-ID: {idUsuario}
```

**Regras:**
- Incrementa o campo `totalReproducoes`
- Exige o usuário via header `X-USER-ID`
- Bloqueia reprodução se o usuário estiver inativo
- Atualiza automaticamente as estatísticas do usuário

### 2. Adicionar Música à Playlist

```http
POST /playlists/{playlistId}/musicas/{musicaId}
X-USER-ID: {idUsuario}
```

**Regras:**
- Apenas o dono da playlist pode adicionar músicas
- Não permite adicionar a mesma música duas vezes
- A playlist precisa existir e estar ativa
- A música precisa existir e estar ativa

### 3. Top 10 Músicas Mais Reproduzidas

```http
GET /relatorios/top-musicas
```

Retorna as 10 músicas com maior número de reproduções, em ordem decrescente, contendo:

- Título da música
- Nome do artista
- Total de reproduções

---

## ❌ Exclusão Lógica

Todas as entidades principais utilizam exclusão lógica. Ao invés de remover o objeto do sistema, o campo `ativo` é alterado para `false`.

Com isso:

- Registros excluídos não aparecem nas listagens
- Registros inativos não podem ser utilizados em operações importantes
- É possível reativar registros através de endpoints específicos (`/reativar/{id}`)

---

## ✅ Validações Implementadas

- Campos obrigatórios não podem ser nulos ou vazios
- Não é permitido cadastrar usuários com e-mail duplicado
- Não é permitido cadastrar músicas ou playlists com dados inválidos
- Usuários inativos não podem reproduzir músicas
- Usuários inativos não podem criar playlists
- Artistas e álbuns precisam existir para serem associados a músicas
- Não é permitido adicionar música repetida na mesma playlist

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- IDE Java (ex: IntelliJ IDEA)

### Passos

1. Clone o repositório:

```bash
git clone <https://github.com/GabrielRosa1/mini-spotify-api.git>
```

2. Entre na pasta do projeto:

```bash
cd mini-spotify-api
```

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

> Ou rode a classe principal `MiniSpotifyApiApplication.java` pela IDE.

## 🧪 Exemplos de Requisições

### Criar usuário

```http
POST /usuarios
Content-Type: application/json
```

```json
{
  "nome": "Gabriel",
  "email": "gabriel@email.com",
  "tipoPlano": "PREMIUM"
}
```

### Criar artista

```http
POST /artistas
Content-Type: application/json
```

```json
{
  "nome": "The Weeknd",
  "generoMusical": "Pop",
  "paisOrigem": "Canadá"
}
```

### Criar álbum

```http
POST /albuns
Content-Type: application/json
```

```json
{
  "titulo": "After Hours",
  "artista": {
    "id": "UUID_DO_ARTISTA"
  }
}
```

### Criar música

```http
POST /musicas
Content-Type: application/json
```

```json
{
  "titulo": "Blinding Lights",
  "duracaoSegundos": 200,
  "numeroFaixa": 1,
  "album": {
    "id": "UUID_DO_ALBUM"
  },
  "artista": {
    "id": "UUID_DO_ARTISTA"
  }
}
```

### Criar playlist

```http
POST /playlists
Content-Type: application/json
```

```json
{
  "nome": "Favoritas",
  "publica": true,
  "usuario": {
    "id": "UUID_DO_USUARIO"
  },
  "musicas": []
}
```

### Reproduzir música

```http
POST /musicas/{id}/reproduzir
X-USER-ID: UUID_DO_USUARIO
```

### Adicionar música à playlist

```http
POST /playlists/{playlistId}/musicas/{musicaId}
X-USER-ID: UUID_DO_USUARIO
```

---

## 📮 Coleção Postman

O projeto acompanha uma coleção Postman para facilitar os testes dos endpoints.

**Sugestão de fluxo para testar:**

1. Criar usuário
2. Criar artista
3. Criar álbum
4. Criar música
5. Criar playlist
6. Reproduzir música
7. Adicionar música à playlist
8. Consultar top músicas
9. Consultar estatísticas do usuário

---

## 📌 Observações sobre a Implementação

- O projeto utiliza **armazenamento em memória** com `HashMap`, sem banco de dados
- Os IDs são gerados com **UUID**
- As respostas de erro são tratadas com `try/catch` nos controllers
- O foco do projeto é demonstrar modelagem, regras de negócio e organização em camadas

---

## 📊 Critérios Atendidos

- [x] Modelagem das entidades
- [x] CRUD completo das entidades principais
- [x] Regras de negócio solicitadas
- [x] Estrutura em camadas (Controller e Service)
- [x] Exclusão lógica
- [x] Organização e clareza do código

---

## 👨‍💻 Autor

Projeto desenvolvido para a disciplina de **Arquitetura de Objetos e Times Ágeis**.

# Music Java Spring

Aplicação de gerenciamento de músicas e artistas integrada com Spring Boot e PostgreSQL, com suporte para consultas via OpenAI.

## Configuração

### Arquivo `secret.properties`

Crie um arquivo `src/main/resources/secret.properties` com as seguintes propriedades:

```properties
# Database credentials
spring.datasource.url=jdbc:postgresql://localhost:5432/screensound_db
spring.datasource.username=username
spring.datasource.password=password

# OpenAI API Key
openai.api.key=sk-proj-api_key
```

### Variáveis obrigatórias:
- `spring.datasource.url` - URL de conexão PostgreSQL
- `spring.datasource.username` - Usuário do PostgreSQL
- `spring.datasource.password` - Senha do PostgreSQL
- `openai.api.key` - Chave de API da OpenAI (obtenha em https://platform.openai.com/api-keys)

**Nota:** O arquivo `secret.properties` está no `.gitignore` para não versionar dados sensíveis.

## Como Rodar

Execute a aplicação com Maven:

```bash
mvn spring-boot:run
```

### Output esperado:

```
*** Screen Sound Musicas ***

1 - Cadastrar artistas
2 - Cadastrar músicas
3 - Listar músicas
4 - Buscar músicas por artistas
5 - Pesquisar dados sobre um artista
0 - Sair

opcao:
```

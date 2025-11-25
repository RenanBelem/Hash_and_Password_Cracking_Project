## Projeto de Hash e Quebra de Senha

Este projeto, desenvolvido em Java, implementa um sistema básico de cadastro e autenticação de contas usando a função de *hashing* **MD5** e inclui uma ferramenta separada para demonstrar a quebra de *hashes* por meio de um ataque de força bruta (Brute Force Crack).

-----

## 📁 Estrutura dos Arquivos

| Arquivo | Descrição |
| :--- | :--- |
| `Main.java` | Contém o ponto de entrada (`main`) do sistema de cadastro/autenticação. Interage com o usuário e salva as credenciais. |
| `Hash.java` | Classe responsável por inicializar e executar o algoritmo de *hashing* **MD5** (Singleton Pattern). |
| `BC.java` | Classe que implementa o ataque de **força bruta** (Brute Force Crack) para quebrar um *hash* MD5. Inclui um método `main` para demonstração. |
| `contas.txt` | Arquivo de persistência usado por `Main.java` para armazenar o login e o *hash* MD5 da senha cadastrada. |
| `Tde-Hash.iml` | Arquivo de configuração do módulo IntelliJ IDEA. |

-----

## 🚀 `Main.java` (Sistema de Cadastro e Autenticação)

Este arquivo é o sistema principal para gerenciar contas.

### Funcionalidades

1.  **Exibição de Hash de Exemplo:** Inicialmente, calcula e exibe o *hash* da *string* "Bom dia flor do dia".
2.  **Menu de Opções:**
      * **Opção 1 - Cadastrar conta:**
          * Solicita um **login** de 4 caracteres.
          * Solicita uma **senha** de 4 caracteres.
          * Calcula o *hash* MD5 da senha usando a classe `Hash`.
          * Salva o login e o *hash* da senha no arquivo `contas.txt` no formato: `login | hash_md5`.
      * **Opção 2 - Autenticar conta (Incompleto):**
          * O bloco de código para autenticação existe, mas está **incompleto** e **incorreto**. Ele tenta ler o arquivo `contas.txt` e fechar o *buffer* dentro do *loop* de leitura, além de usar `line.split("|")` de forma ineficaz. A funcionalidade de comparação e autenticação não está implementada.

### Formato do Arquivo `contas.txt`

O arquivo armazena as credenciais como: `login | hash_da_senha`

*Exemplo:*

```
asdf | b02cce6739ce51ef5032d9c7f7ba85c3
```

-----

## 🔒 `Hash.java` (Implementação do MD5)

A classe `Hash` implementa a geração de *hashes* MD5.

  * **Padrão Singleton:** Utiliza o padrão Singleton (`getInstance()`) para garantir que apenas uma instância do `MessageDigest` para MD5 seja criada.
  * **Método `toString(String entrada)`:** Recebe uma *string*, calcula o *hash* MD5 e retorna o resultado como uma *string* hexadecimal de 32 caracteres.
      * *Nota:* A implementação converte o *array* de *bytes* do *hash* para a representação hexadecimal, garantindo o formato correto.

-----

## 🔨 `BC.java` (Brute Force Crack)

A classe `BC` (Brute Force Crack) demonstra um ataque de força bruta contra um *hash* MD5, tentando adivinhar a senha original.

### Detalhes do Ataque

  * **Conjunto de Caracteres:** Tenta senhas utilizando caracteres ASCII imprimíveis, definidos pelo intervalo:
      * `min_char_value` = 32 (Espaço)
      * `max_char_value` = 126 (Tilda `~`)
  * **Tamanho Máximo:** O ataque tenta senhas de comprimento 0 até **10** caracteres (`max_num_chars = 10`).
  * **Processo:**
    1.  Começa com senhas de comprimento 0 e aumenta iterativamente.
    2.  Para cada comprimento, itera sobre todas as combinações possíveis de caracteres no intervalo definido.
    3.  Para cada tentativa (`guess`), calcula o *hash* MD5 e o compara com o *hash* alvo.
  * **Método `main` de Demonstração:**
      * Executa a quebra para o *hash* alvo: `"74b87337454200d4d33f80c4663dc5e5"`.
      * Mede e exibe o tempo de processamento em segundos.

### Funções Auxiliares

  * `canIncrementGuess()`: Verifica se há mais combinações para tentar com o comprimento atual.
  * `incrementGuess()`: Move para a próxima combinação de caracteres.
  * `hashToString(byte[] hash)`: Converte o *array* de *bytes* do *hash* em uma *string* hexadecimal (similar a `Hash.java`, mas com uma implementação ligeiramente diferente).

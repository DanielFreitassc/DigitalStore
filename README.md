# API de Loja Online

Base URL: `http://localhost:8080`

---

## **Cadastro de Produto**

**Endpoint:** `POST /products`  
**Status de Resposta:** `201 CREATED`  

### Formato de Requisição (form-data):

| Campo | Tipo   | Descrição                    |
|-------|--------|------------------------------|
| name  | string | Nome do produto              |
| image | file   | Foto do produto (foto.png)   |
| price | number | Preço do produto (ex: 20.0)  |

### Resposta:

```json
{
    "id": "b9c60ee8-18cb-4ded-b697-4c758af84c18",
    "name": "Nome do produto",
    "imageId": "b2f4317e-9187-43d7-b2c7-7ec05ff3f680",
    "price": 20.00,
    "createdAt": "2025-03-10T00:32:09.473+00:00"
}
```

---

## **Atualizar Produto**

**Endpoint:** `PUT /products/{prod_id}`  
**Status de Resposta:** `200 OK`  

### Formato de Requisição (form-data):

| Campo | Tipo   | Descrição                    |
|-------|--------|------------------------------|
| name  | string | Nome do produto atualizado   |
| image | file   | Foto do produto (foto.png)   |
| price | number | Preço do produto (ex: 20.0)  |

### Resposta:

```json
{
    "id": "efe0b419-68dc-4459-9db8-036fa2eca7d8",
    "name": "nome do produto atualizado",
    "imageId": "d8350930-3f96-4018-8383-6d5d5c0856db",
    "price": 2000.2,
    "createdAt": "2025-03-10T00:50:02.997+00:00"
}
```

---

## **Buscar Produtos**

**Endpoint:** `GET /products`  
**Status de Resposta:** `200 OK`  

### Resposta:

```json
{
    "content": [
        {
            "id": "efe0b419-68dc-4459-9db8-036fa2eca7d8",
            "name": "nome do produto atualizado",
            "imageId": "d8350930-3f96-4018-8383-6d5d5c0856db",
            "price": 2000.20,
            "createdAt": "2025-03-10T00:50:02.997+00:00"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 20,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

---

## **Buscar Produto por ID**

**Endpoint:** `GET /products/{prod_id}`  
**Status de Resposta:** `200 OK`  

### Resposta:

```json
{
    "id": "efe0b419-68dc-4459-9db8-036fa2eca7d8",
    "name": "nome do produto atualizado",
    "imageId": "d8350930-3f96-4018-8383-6d5d5c0856db",
    "price": 2000.20,
    "createdAt": "2025-03-10T00:50:02.997+00:00"
}
```

---

## **Remover Produto por ID**

**Endpoint:** `DELETE /products/{prod_id}`  
**Status de Resposta:** `200 OK`  

### Resposta:

```json
{
    "id": "efe0b419-68dc-4459-9db8-036fa2eca7d8",
    "name": "nome do produto atualizado",
    "imageId": "d8350930-3f96-4018-8383-6d5d5c0856db",
    "price": 2000.20,
    "createdAt": "2025-03-10T00:50:02.997+00:00"
}
```

---

# API de Chat

**Base URL:** `ws://localhost:8080/ws`  

## **Inscrição no Tópico**

**Tópico para inscrição:** `/topics/messages`  

---

## **Enviar Mensagem**

**Endpoint:** `/app/new-message`  

### Corpo da Requisição:

```json
{
    "username": "Fulano",
    "message": "Olá"
}
```

### Resposta:

```json
{
    "id": "ff399cc7-1af3-43ac-979c-4140e9b4a581",
    "message": "Olá",
    "username": "Fulano",
    "createdAt": "2025-03-09T22:00:43.732381"
}
```

---

## **Atualizar Mensagem**

**Endpoint:** `/app/update-message`  

### Corpo da Requisição:

```json
{
    "id": "efe0b419-68dc-4459-9db8-036fa2eca7d8",
    "username": "Fulano",
    "message": "Olá tudo bem?"
}
```

### Resposta:

```json
[
  {
    "id": "99da3830-325a-4f80-b472-c9d33660ff7b",
    "message": "Olá",
    "username": "Fulano tudo bem?",
    "createdAt": "2025-03-09T22:00:19.196698"
  }
]
```

---

## **Apagar Mensagem**

**Endpoint:** `/app/update-message`  

### Corpo da Requisição:

```json
{
    "id": "efe0b419-68dc-4459-9db8-036fa2eca7d8"
}
```

### Resposta:

```json
{
   "message": "Mensagem apagada"
}
```

---

## **Histórico de Mensagens**

### **Listar Todo o Histórico de Chat**

**Endpoint:** `GET /history-chats`  
**Status de Resposta:** `200 OK`

### Resposta:

```json
[
  {
    "id": "99da3830-325a-4f80-b472-c9d33660ff7b",
    "message": "Olá",
    "username": "Fulano",
    "createdAt": "2025-03-09T22:00:19.196698"
  }
]
```

---

### **Buscar Uma Mensagem Única por ID**

**Endpoint:** `GET /history-chats/{message_id}`  
**Status de Resposta:** `200 OK`

### Resposta:

```json
{
  "id": "99da3830-325a-4f80-b472-c9d33660ff7b",
  "message": "Olá",
  "username": "Fulano",
  "createdAt": "2025-03-09T22:00:19.196698"
}
```


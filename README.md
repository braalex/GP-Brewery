# GP-Brewery
## Overview

Приложение для организации работы пивоварни и приёма заказов на произведённое пиво.

## Сущности
Ниже перечислены сущности в предметной области проекта и их поля.

### Director (Директор)
Директор пивоварни.

Поля:

- Email
- Имя
- Фамилия
- Дата рождения

Связи:

- Возможность просмотра и добавления новых варок пива ("Brew")
- Просмотр совершённых заказов ("Order")

### Brewer (Пивовар)
Сотрудник пивоварни.

Поля:

- Email
- Имя
- Фамилия
- Дата рождения

Связи:

- Варки пива, закреплённые за пивоваром ("Brew")

### Customer (Покупатель)
Организация, закупающая пиво.

Поля:

- Email
- Категория
- Название

Связи:

- Совершённые заказы ("Order")

### Order (Заказ)
Заказ партии пива, совершённый покупателем.

Поля:

- Покупатель
- Сорт
- Количество
- Дата заказа

Связи:

- Данные покупателя ("Customer")
- Сорт заказанного пива ("Beer")

### Beer (Сорт пива)
Сорт пива, производимый на пивоварне.

Поля:

- Тип
- Название
- Крепость
- Плотность
- Описание
- Список ингредиентов
- Цена за литр

Связи:

- Директор добавляет новые сорта в ассортимент продукции ("Director")
- Ингредиенты, необходимые для производства данного сорта ("Ingredient")

### Ingredient (Ингредиент)
Ингредиент для производства пива.

Поля:

- Тип
- Название

### Brew (Варка)
Процесс производства конкретного сорта пива.

Поля:

- Пивовар
- Сорт
- Дата начала
- Дата окончания

Связи:

- Директор планирует новые варки ("Director")
- Ответственный пивовар ("Brewer")
- Сорт производимого пива ("Beer")
- Ингредиенты для производства ("Ingredient")

## User Stories

### GPB-1 Как "Покупатель" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь
Request:

`POST /brewery-app/customers/sign-up`

```
{
  "email" : "craft-bar@email.com",
  "password" : "qwerty",
  "category" : "bar",
  "companyName" : "Craft Bar"
}
```

Response: `201 CREATED`

```
{
  "id" : 1,
  "email" : "craft-bar@email.com",
  "category" : "bar",
  "companyName" : "Craft Bar"
}
```

### GPB-2 Как "Покупатель", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /brewery-app/customers/sign-in`

```
{
  "email" : "craft-bar@email.com",
  "password" : "qwerty"
}
```

Response: `200 OK`

```
{
  "id" : 1,
  "email" : "craft-bar@email.com"
}
```

### GPB-3 Как "Покупатель" я хочу получить список доступных для заказа сортов пива, чтобы выбрать позиции для заказа

Request:

`GET /brewery-app/beers`

Response: `200 OK`

```
[
  {
    "id" : 1,
    "type" : "Stout",
    "beerName" : "Espresso Stout",
    "abv" : 6.1,
    "originalGravity" : 14.0,
    "description" : "Coffee stout",
    "ingredients" : [
              {"type" : "HOPS", "name" : "Magnum"},
              {"type" : "MALT", "name" : "Brown Malt"},
              {"type" : "YEAST", "name" : "Ale Yeast"}
    ],
    "price" : 4.2
  },
  {
    "id" : 2,
    "type" : "IPA",
    "beerName" : "Madness",
    "abv" : 6.6,
    "originalGravity" : 13.0,
    "description" : "American style IPA",
    "ingredients" : [
              {"type" : "HOPS", "name" : "Cascade"},
              {"type" : "MALT", "name" : "Rye Malt"},
              {"type" : "YEAST", "name" : "Yeast"}
    ],
    "price" : 5.3
  }
]
```

### GPB-4 Как "Покупатель" я хочу совершить заказ на 100 литров пива с id 1, чтобы пополнить свой ассортимент

Request:

`POST /brewery-app/customers/1/orders`

```
{
  "customerId" : 1,
  "beerId" : 1,
  "quantity" : 100,
  "orderDate" : "2020-02-06"
}
```

Response: `201 CREATED`

```
{
  "id" : 15,
  "customerId" : 1,
  "beerId" : 1,
  "quantity" : 100,
  "orderDate" : "2020-02-06"
}
```

### GPB-5 Как "Покупатель" я хочу получить список своих заказов, чтобы оценить заказанный ассортимент и объёмы

Request:

`GET /brewery-app/customers/1/orders`

Response: `200 OK`

```
[
  {
    "id" : 15,
    "customerId" : 1,
    "beerId" : 1,
    "quantity" : 100,
    "orderDate" : "2020-02-06"
  }
]
```

### GPB-6 Как "Пивовар" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /brewery-app/brewers/sign-up`

```
{
  "email" : "ivanov123@email.com",
  "password" : "ilovebeer",
  "firstName" : "Sergey",
  "lastName" : "Ivanov",
  "dateOfBirth" : "1982-06-11" 
}
```

Response: `201 CREATED`

```
{
  "id" : 5,
  "email" : "ivanov123@email.com",
  "firstName" : "Sergey",
  "lastName" : "Ivanov",
  "dateOfBirth" : "1982-06-11"
}
```

### GPB-7 Как "Пивовар", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /brewery-app/brewers/sign-in`

```
{
  "email" : "ivanov123@email.com",
  "password" : "ilovebeer"
}
```

Response: `200 OK`

```
{
  "id" : 5,
  "email" : "ivanov123@email.com"
}
```

### GPB-8 Как "Пивовар" я хочу получить список варок, в которых я участвую, чтобы знать свою рабочую загрузку

Request:

`GET /brewery-app/brewers/5/brews`

Response: `200 OK`

```
[
  {
    "id" : 1,
    "brewerId" : 5,
    "beerId" : "2,
    "startDate" : "2020-02-10",
    "endDate" : "2020-03-25"
  }
]
```

### GPB-9 Как "Директор", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /brewery-app/director/sign-in`

```
{
  "email" : "bigboss@email.com",
  "password" : "secretpass"
}
```

Response: `200 OK`

```
{
  "id" : 1,
  "email" : "bigboss@email.com"
}
```

### GPB-10 Как "Директор" я хочу получить список всех совершённых заказов, чтобы оценить объём продаж и запланировать новые варки

Request:

`GET /brewery-app/director/orders`

Response: `200 OK`

```
[
  {
    "id" : 15,
    "customerId" : 1,
    "beerId" : 1,
    "quantity" : 100,
    "orderDate" : "2020-02-06"
  },
  {
    "id" : 16,
    "customerId" : 4,
    "beerId" : 2,
    "quantity" : 150,
    "orderDate" : "2020-02-07"
  }
]
```

### GPB-11 Как "Директор" я хочу получить список всех варок, чтобы узнать загрузку своих пивоваров

Request:

`GET /brewery-app/director/brews`

Response: `200 OK`

```
[
  {
    "id" : 1,
    "brewerId" : 5,
    "beerId" : 2,
    "startDate" : "2020-02-10",
    "endDate" : "2020-03-25"
  },
  {
    "id" : 2,
    "brewerId" : 3,
    "beerId" : 1,
    "startDate" : "2020-02-20",
    "endDate" : "2020-04-20"
  }
]
```

### GPB-12 Как "Директор" я хочу добавить в перечень новый сорт пива, чтобы расширить ассортимент

Request:

`POST /brewery-app/director/beers`

```
{
  "type" : "Wheat",
  "beerName" : "Summer",
  "abv" : 4.5,
  "originalGravity" : 9.0,
  "description" : "Belgian style wheat beer",
  "ingredients" : [
            {"type" : "HOPS", "name" : "Zatec"},
            {"type" : "MALT", "name" : "Wheat Malt"},
            {"type" : "YEAST", "name" : "Yeast"},
  ],
  "price" : 3.5
}
```

Response: `201 CREATED`

```
{
  "id" : 3,
  "type" : "Wheat",
  "beerName" : "Summer",
  "abv" : 4.5,
  "originalGravity" : 9.0,
  "description" : "Belgian style wheat beer",
  "ingredients" : [
            {"type" : "HOPS", "name" : "Zatec"},
            {"type" : "MALT", "name" : "Wheat Malt"},
            {"type" : "YEAST", "name" : "Yeast"},
  ],
  "price" : 3.5
}
```

### GPB-13 Как "Директор" я хочу добавить в план новую варку, чтобы удовлетворить спрос покупателей

Request:

`POST /brewery-app/director/brews`

```  
{
  "brewerId" : 5,
  "beerId" : 3,
  "startDate" : "2020-03-05",
  "endDate" : "2020-05-12"
}
```

Response: `201 CREATED`

```
{
  "id" : 3,
  "brewerId" : 5,
  "beerId" : 3,
  "startDate" : "2020-03-05",
  "endDate" : "2020-05-12"
}
```

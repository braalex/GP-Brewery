# GP-Brewery
## Overview

Приложение для организации работы пивоварни и приёма заказов на произведённое пиво.

## Сущности
Ниже перечислены сущности в предметной области проекта и их поля.

### Director (Директор)
Директор пивоварни.

Поля:

- Email
- ФИО
- Должность
- Дата рождения

Связи:

- Возможность просмотра и добавления новых варок пива ("Brew")
- Просмотр совершённых заказов ("Order")

### Brewer (Пивовар)
Сотрудник пивоварни.

Поля:

- Email
- ФИО
- Должность
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
- Цена за литр
- Доступное количество

Связи:

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
- Количество
- Ингредиенты
- Дата начала
- Дата окончания

Связи:

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
  "customerId" : 1
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
  "customerId" : 1
}
```

### GPB-3 Как "Покупатель" я хочу получить список доступных для заказа сортов пива, чтобы выбрать позиции для заказа

Request:

`GET /brewery-app/beers`

Response: `200 OK`

```
[
  {
    "beerId" : 1,
    "type" : "Stout",
    "beerName" : "Espresso Stout",
    "ABV" : 6.1,
    "originalGravity" : 14.0,
    "description" : "Кофейный стаут",
    "price" : 4.2,
    "stockLevel" : 350
  },
  {
    "beerId" : 2,
    "type" : "IPA",
    "beerName" : "Madness",
    "ABV" : 6.6,
    "originalGravity" : 13.0,
    "description" : "IPA в американском стиле",
    "price" : 5.3,
    "stockLevel" : 200
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
  "orderDate" : "06.02.2020"
}
```

Response: `201 CREATED`

```
{
  "orderId" : 15
}
```

### GPB-5 Как "Покупатель" я хочу получить список своих заказов, чтобы оценить заказанный ассортимент и объёмы

Request:

`GET /brewery-app/customers/1/orders`

Response: `200 OK`

```
{
  "orderId" : 15,
  "customerId" : 1,
  "beerId" : 1,
  "quantity" : 100,
  "orderDate" : "06.02.2020"
}
```

### GPB-6 Как "Пивовар" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь

Request:

`POST /brewery-app/brewers/sign-up`

```
{
  "email" : "ivanov123@email.com",
  "password" : "ilovebeer",
  "position" : "brewer",
  "dateOfBirth" : "11.06.1982" 
}
```

Response: `201 CREATED`

```
{
  "brewerId" : 5
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
  "brewerId" : 5
}
```

### GPB-8 Как "Пивовар" я хочу получить список варок, в которых я участвую, чтобы знать свою рабочую загрузку

Request:

`GET /brewery-app/brewers/5/brews`

Response: `200 OK`

```
{
    "brewId" : 1,
    "brewerId" : 5,
    "beerName" : "Hoppy Lager",
    "ingredients" : [
          {"type" : "water", "name" : "Water"},
          {"type" : "hops", "name" : "Cascade"},
          {"type" : "malt", "name" : "Rye Malt"},
          {"type" : "yeast", "name" : "Yeast"},
    ],
    "startDate" : "10.02.2020",
    "endDate" : "25.03.2020"
}
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
  "id" : 1
}
```

### GPB-10 Как "Директор" я хочу получить список всех совершённых заказов, чтобы оценить объём закупок и запланировать новые варки

Request:

`GET /brewery-app/director/orders`

Response: `200 OK`

```
[
  {
  "orderId" : 15,
  "customerId" : 1,
  "beerId" : 1,
  "quantity" : 100,
  "orderDate" : "06.02.2020"
},
  {
  "orderId" : 16,
  "customerId" : 4,
  "beerId" : 2,
  "quantity" : 150,
  "orderDate" : "07.02.2020"
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
    "brewId" : 1,
    "brewerId" : 5,
    "beerName" : "Hoppy Lager",
    "ingredients" : [
          {"type" : "water", "name" : "Water"},
          {"type" : "hops", "name" : "Cascade"},
          {"type" : "malt", "name" : "Rye Malt"},
          {"type" : "yeast", "name" : "Yeast"},
    ],
    "startDate" : "10.02.2020",
    "endDate" : "25.03.2020"
  },
  {
    "brewId" : 2,
    "brewerId" : 3,
    "beerName" : "Wheat",
    "ingredients" : [
          {"type" : "water", "name" : "Water"},
          {"type" : "hops", "name" : "Zatec"},
          {"type" : "malt", "name" : "Wheat Malt"},
          {"type" : "yeast", "name" : "Yeast"},
    ],
    "startDate" : "20.02.2020",
    "endDate" : "20.04.2020"
  }
]
```

### GPB-12 Как "Директор" я хочу добавить в план новую варку, чтобы удовлетворить спрос покупателей

Request:

`POST /brewery-app/director/brews`

```
{
    "brewerId" : 5,
    "beerName" : "Baltic Porter",
    "ingredients" : [
          {"type" : "water", "name" : "Water"},
          {"type" : "hops", "name" : "Magnum"},
          {"type" : "malt", "name" : "Brown Malt"},
          {"type" : "yeast", "name" : "Ale Yeast"},
    ],
    "startDate" : "05.03.2020",
    "endDate" : "12.05.2020"
  }
```

Response: `201 CREATED`

```
{
  "brewId" : 3
}
```

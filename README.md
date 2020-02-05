# GP-Brewery
## Overview

Приложение для организации работы пивоварни и приёма заказов на произведённое пиво.

## Сущности
Ниже перечислены сущности в предметной области проекта и их поля.

### Employee (Сотрудник)
Сотрудник пивоварни (директор и пивовары).

Поля:

- ФИО
- Должность
- Дата рождения

### Customer (Покупатель)
Организация, закупающая пиво.

Поля:

- Email
- Категория
- Название
- Скидка

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

- Ответственный пивовар ("Employee")
- Сорт производимого пива ("Beer")
- Ингредиенты для производства ("Ingredient")

## User Stories

### GPB-1 Как "Покупатель" я хочу зарегистрироваться в системе, и, если такого пользователя не найдено, регистрируюсь
Request:

`POST /brewery-app/customer/sign-up`

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
  "id" : 1
}
```

### GPB-2 Как "Покупатель", будучи зарегистрированным пользователем, я хочу войти в систему, и, если такой пользователь существует и пароль совпадает, войти в систему

Request:

`POST /brewery-app/customer/sign-in`

```
{
  "email" : "craft-bar@email.com",
  "password" : "qwerty"
}
```

Response: `200 OK`

```
{
  "id" : 1
}
```

### GPB-3 Как "Покупатель" я хочу получить список доступных для заказа сортов пива, и в результате получаю его

Request:

`GET /brewery-app/beer/list`

Response: `200 OK`

```
[
  {
    "id" : 1,
    "type" : "Stout",
    "beerName" : "Espresso Stout",
    "ABV" : 6.1,
    "originalGravity" : 14.0,
    "description" : "Кофейный стаут",
    "price" : 4.2,
    "stockLevel" : 350
  },
  {
    "id" : 2,
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

### GPB-4 Как "Покупатель" я хочу заказать 100 литров пива с id 1, и, если такое количество есть на складе, в результате заказываю его

Request:

`GET /brewery-app/beer/1/order/100`

`Headers: customerId=1`

Response: `200 OK`

### GPB-5 Как "Сотрудник" я хочу получить список запланированных варок, и в результате получаю его

Request:

`GET /brewery-app/brew/list`

Response: `200 OK`

```
[
  {
    "id" : 1,
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
    "id" : 2,
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

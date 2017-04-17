# microblog-api

FIXME

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server
## API

### Micro blogs
#### GET `api/microblogs`
Returns a list of blogs

#### POST `api/microblogs`
Creates a microblog from a JSON object, assigns it an id and returns it

#### GET `api/microblogs/:id`
Returns an object containing the blog with id `id`

```json
{
  "id": "640a81fb-cad8-4962-96cc-cbeb59795b13",
  "message": "message",
  "date": "2017/03/03 12:00",
  "user": "userID"
}
```
## License

Copyright © 2017 FIXME

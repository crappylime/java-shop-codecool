CREATE TABLE IF NOT EXISTS categories
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR NOT NULL,
  description TEXT,
  department  VARCHAR NOT NULL
)

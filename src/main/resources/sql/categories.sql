CREATE TABLE IF NOT EXISTS categories
(
  id          INTEGER PRIMARY KEY AUTOINCREMENT,
  name        VARCHAR NOT NULL,
  description TEXT,
  department  VARCHAR NOT NULL
)
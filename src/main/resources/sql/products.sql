CREATE TABLE IF NOT EXISTS products
(
  id            INTEGER PRIMARY KEY AUTOINCREMENT,
  name          VARCHAR NOT NULL,
  description   TEXT,
  default_price FLOAT   NOT NULL,
  currency      VARCHAR NOT NULL,
  category_id   INTEGER NOT NULL,
  supplier_id   INTEGER NOT NULL
)
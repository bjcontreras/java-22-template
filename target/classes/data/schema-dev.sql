-- Opcional: crear esquema y usarlo
CREATE SCHEMA IF NOT EXISTS prueba_template;
SET search_path = prueba_template, public;

-- Tabla
CREATE TABLE IF NOT EXISTS "user" (
  identification TEXT PRIMARY KEY,
  identification_type TEXT NOT NULL,
  name TEXT,
  last_name TEXT,
  telephone_number TEXT,
  email TEXT,
  created_at TIMESTAMPTZ,
  updated_at TIMESTAMPTZ DEFAULT now()
);

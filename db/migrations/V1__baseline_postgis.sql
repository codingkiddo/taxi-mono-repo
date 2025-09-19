-- Enable PostGIS
CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS pgcrypto;
-- Basic PII demo table
CREATE TABLE IF NOT EXISTS rider (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name text NOT NULL,
  phone_e164 text UNIQUE NOT NULL,
  email text,
  created_at timestamptz DEFAULT now()
);
CREATE TABLE IF NOT EXISTS driver (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name text NOT NULL,
  phone_e164 text UNIQUE NOT NULL,
  status text NOT NULL CHECK (status IN ('ONLINE','OFFLINE','BUSY')),
  last_known_point geography(Point, 4326),
  last_ping_at timestamptz
);
CREATE INDEX IF NOT EXISTS idx_driver_geo ON driver USING GIST (last_known_point);
CREATE TABLE IF NOT EXISTS trip (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  rider_id uuid,
  driver_id uuid,
  status text NOT NULL,
  start_time timestamptz,
  end_time timestamptz,
  distance_km numeric(10,2),
  duration_min numeric(10,2),
  fare_amount numeric(10,2)
);

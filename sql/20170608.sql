ALTER TABLE ta_tarifas ADD COLUMN snventas character varying(1);
ALTER TABLE ta_tarifas ADD COLUMN nmorden numeric(2,0);
UPDATE ta_tarifas SET nmorden = 99;

ALTER TABLE ta_excesos ADD COLUMN snventas character varying(1);
ALTER TABLE ta_excesos ADD COLUMN nmorden numeric(2,0);
UPDATE ta_excesos SET nmorden = 99;